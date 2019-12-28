package com.scan.annotate;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.BiPredicate ;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.common.SingletonRef;
import com.scan.annotate.PkgNameResourcesContent.PkgNameResourcesContentBase2;


public class AnnottedClazsFrmPkgs {
	private final OutPutLisOfAnnotClzsAndMethds lisOfAnnotClzsAndMethds = new OutPutLisOfAnnotClzsAndMethds() ;
	private static Map<String, String> env = new HashMap<>(); ////see why note in env.put before Calling the collectAnnotedClassesPerPKgURI 
	private final Map<PkgNameResourcesContentBase2, List<PkgNameResourcesContent>> filterTracking 
	             = new HashMap<PkgNameResourcesContentBase2,List<PkgNameResourcesContent>>() ;
	private volatile Character fileCharSepr = '/';
	private final String extension = ".class";
	private  final  Predicate<Path> isJavaClassFile = (pf) -> {
		Boolean bRet = Files.isRegularFile(pf) && pf.getFileName().toString().trim().endsWith(extension);
		return bRet ;
	};
	@FunctionalInterface
	interface CaptureAnnotionsMethodFunc2 {
		void mthdCaptureAnnotaion2(Class<?> clzz, Method mthd , List<Class<? extends Annotation>> annotedClz4MethodLis) ;
				
	};
	//(Class<? extends Annotation> e CapturedAnnotClzAndMethds
	private  final BiFunction<Path, String, CapturedAnnotClzAndMethds> funMapPathToCaptureClzAndMthds = (pathx,pkgName) -> 
	{
		
		String path = pathx.toString().replace(fileCharSepr, '.');
		String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
		Class<?> clx;
		// List<Class<? extends Annotation>> clzAnnotedList = new ArrayList<Class<? extends Annotation>>();
		List<AnnotationDetailsFounded> annFoundLisForClz = new ArrayList<AnnotationDetailsFounded>() ;
		try {
			clx = Class.forName(name);
			//
			for ( Class<? extends Annotation> annInp : lisOfAnnotClzsAndMethds.getAnnInpClzLis()) 
			{
				//if ( clx.isAnnotationPresent(annInp) )  clzAnnotedList.add(annInp);
				
				List<? extends Annotation> lisOfTheAry = Arrays.asList(clx.getAnnotationsByType(annInp)); 
				Objects.requireNonNull(lisOfTheAry, "Return Array from getAnnotationsByType must ot be NULL") ;	//Over Protection--Just in Case of null 
				if ( lisOfTheAry.size() > 0  ) {
					
					//annFoundForClz = new ArrayList<AnnotationDetailsFounded>();
					AnnotationDetailsFounded annotationDetailsFounded = new AnnotationDetailsFounded() ;
					lisOfTheAry.forEach(item ->  {
						annotationDetailsFounded.setAnnInpToFind(annInp);
						annotationDetailsFounded.setAnnOutFounded(lisOfTheAry);
						
					}); 
					annFoundLisForClz.add(annotationDetailsFounded) ;	
				}
			}
			
//			lisOfAnnotClzsAndMethds.getAnnInpClzLis()
//			.forEach(annInp -> {
//				if ( clx.isAnnotationPresent(annInp) )  clzAnnotedList.add(annInp);   has to be final for clzAnnotedList
//				}) ;
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1); 
		} 	
	if (annFoundLisForClz.size() == 0) {
		annFoundLisForClz = null ;
		return null ;
	}
	CapturedAnnotClzAndMethds capAnnotClzAndMethds = new CapturedAnnotClzAndMethds() ;  //private List<AnnotationDetailsFounded> AnnotDetailsFoundedLis
	capAnnotClzAndMethds.setAnoClz(clx);
	capAnnotClzAndMethds.setClzAnnotDetailsFoundedLis(annFoundLisForClz); //All clz annoation has been Captured 
	lisOfAnnotClzsAndMethds.addCap(capAnnotClzAndMethds) ;
	
	return capAnnotClzAndMethds ;
	};

	BiFunction<URL,String,PkgNameResourcesContent> funMapPkgToResourcesContenti = (url1,PkgStrName) -> 
	{ 
		PkgNameResourcesContent pkgNameResourcesContenti = new PkgNameResourcesContent() ;
		try 
		{ 
			pkgNameResourcesContenti.setContentByURL(url1);
			pkgNameResourcesContenti.setFilterTracking(filterTracking);
			pkgNameResourcesContenti.setPkgName(PkgStrName);
		} 
		catch (URISyntaxException | UnsupportedEncodingException e1 ) 
		{ 
			e1.printStackTrace(); 
			throw new RuntimeException(e1); 
		}

		return pkgNameResourcesContenti ; 
	} ; 
	
	//
	/**
	 * @param inputPKGs
	 * @param annotedClassLis
	 * @param annotedClz4MethodLis
	 */
	public void doScanAndCapture(List<String> inputPKGs, List<Class<? extends Annotation>>  annotedClassLis, List<Class<? extends Annotation>> annotedClz4MethodLis )
	{
		//we need to add not null Annotations for classes
		//
/* TODO Check for not null annotation for Classes Pending */
	lisOfAnnotClzsAndMethds.setAnnInpClzLis(annotedClassLis) ;		
	lisOfAnnotClzsAndMethds.setAnnInpMthds(annotedClz4MethodLis);
	List<PkgNameResourcesContent> sortedLisByStrFile =sortPkgs(inputPKGs) ;
	List<PkgNameResourcesContent> normalizedPkgAndURIsLis = 	new ArrayList<PkgNameResourcesContent>();
	normalizedPkgAndURIsLis = removeDuplication(sortedLisByStrFile) ;
	SingletonRef.ONLYONEINS.getDispLogger().info("\t\tStrat priFilterTracking") ;
	if (normalizedPkgAndURIsLis.size() > 1)normalizedPkgAndURIsLis.get(0).priFilterTracking();
	SingletonRef.ONLYONEINS.getDispLogger().info("\t\tEnd priFilterTracking") ;
	
	SingletonRef.ONLYONEINS.getDispLogger().info("Start Scaning And Collect Annoted Classes " ) ;
	normalizedPkgAndURIsLis.forEach(pkgNameResourcesContent -> {
		SingletonRef.ONLYONEINS.getDispLogger().info("Scan PKG/URI =["+pkgNameResourcesContent.getPkgName() +"] [" +  pkgNameResourcesContent.getUri() + "]") ;
		try {
			collectAnnotedClassesPerPKgURI(pkgNameResourcesContent) ; 
		} catch (FileSystemNotFoundException | IOException | URISyntaxException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e); 
		} 		
	}
			);
	SingletonRef.ONLYONEINS.getDispLogger().info("End Scaning And Collect Annoted Classes " ) ;
	SingletonRef.ONLYONEINS.getDispLogger().info("Start Print Result") ;
	//SingletonRef.ONLYONEINS.getDispLogger().info("\n"+ lisOfAnnotClzsAndMethds.printResult(lisOfAnnotClzsAndMethds.getLisOfCap())) ;
	SingletonRef.ONLYONEINS.getDispLogger().info("\n"+ SingletonRef.ONLYONEINS.printResult(lisOfAnnotClzsAndMethds.getLisOfCap())) ;
	SingletonRef.ONLYONEINS.getDispLogger().info("End Print Result") ;
	
	}
	//
	@SuppressWarnings("unused")
	public void collectAnnotedClassesPerPKgURI(PkgNameResourcesContent pkgNameRCt) throws IllegalAccessException, IOException, FileSystemNotFoundException, URISyntaxException
	{ 	
		env.put("create", "true");  //Set the env to be create for reading the jar entries; otherwise null and fileSystem not Found exception!!!!!
		fileCharSepr = '/';
		URI uri = pkgNameRCt.getUri() ;
		//
		/* TODO Check for uri open or not  Pending */	
		//
		String pkgName = pkgNameRCt.getPkgName().trim() ;
		Path rootPathToWalk= null ;
		String uriToString = uri.toString().trim();
		String startWithJarOrFile  = uriToString.substring(0, uriToString.indexOf(':'));
		switch(startWithJarOrFile) 
		{ 
		/******************************************************************************************************************************
		 * Important Comment regards This line: " FileSystem zipfs = FileSystems.newFileSystem(uri, env); "
		 *  Avoid some fun from Java regards opening the jar
		 *  Most Properly we need to track the opened file before opening it??? 
         * Big Q here ????????????????????????????????????????
         * Also, the Jar inside jar needs more investigation and Testing 
		 *******************************************************************************************************************************/
         
		case "jar": 
			//
			// we need to check the open files for Mutiples jars?? Pending
			//
			FileSystem zipfs = FileSystems.newFileSystem(uri, env); //? Avoid some fun from Java regards opening the jar ? //
			rootPathToWalk = Paths.get(uri);
			break; 
		case "file": 
			rootPathToWalk = Paths.get(uri) ;
			fileCharSepr = File.separatorChar;
			break; 
		default: 
			SingletonRef.ONLYONEINS.getDispLogger().severe("uriToString Start with unkwon  match[" + startWithJarOrFile + "]");
			throw new RuntimeException("uriToString Start with unkwon  match[" + startWithJarOrFile + "]");
		} 
		@SuppressWarnings("resource")
		Stream<Path> allPaths = Files.walk(rootPathToWalk) ;
		allPaths
		
		.filter(isJavaClassFile)
		 	
		.map(path -> funMapPathToCaptureClzAndMthds.apply(path,pkgName))  //Now the CapturedAnnotClzAndMethds is ready if not null  capturedAnnClzAndMethds
		.filter(capturedAnnClzAndMethds ->   capturedAnnClzAndMethds != null )             
		
		
		.forEach(capturedAnnClzAndMethds ->  
		{
		lisOfAnnotClzsAndMethds.peekTheAnnMthdsforClzFunci.accept(capturedAnnClzAndMethds) ;
	
	});
		
	}
	public List<PkgNameResourcesContent> removeDuplication(List<PkgNameResourcesContent> sortedLis)
	{
		BiPredicate<PkgNameResourcesContent,PkgNameResourcesContent> biPredicateIns=PkgNameResourcesContent::filterIt2 ;
		List<PkgNameResourcesContent> normalPkgAndURIsLis = 	new ArrayList<PkgNameResourcesContent>();
		int loopNo=0;
		List<PkgNameResourcesContent> workingLisWithLoopNo = sortedLis ;
		while (workingLisWithLoopNo.size() > loopNo )
		{
			PkgNameResourcesContent toUseAsStartWithStrToCompare = sortedLis.get(loopNo) ;
			normalPkgAndURIsLis = workingLisWithLoopNo.stream()
					.filter(pkgNameResourcesContent-> !biPredicateIns.test(pkgNameResourcesContent, toUseAsStartWithStrToCompare)) 
					.collect(Collectors.toList());
			++loopNo ;
			workingLisWithLoopNo = normalPkgAndURIsLis ;
		}
		return normalPkgAndURIsLis ;
	}
//
	public List<PkgNameResourcesContent> sortPkgs (List<String> lisofInpPKGs )
	{
		List<PkgNameResourcesContent> allLisPkgNameResContent = new ArrayList<PkgNameResourcesContent>();

		lisofInpPKGs.stream().forEach( (pkgStr) -> {
			Enumeration<URL> enOfURLs = null;
			try {
				 
				  enOfURLs = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResources(pkgStr.replace('.', '/'))); //for each pkgStr returns list of URLs from enum
				  if (enOfURLs == null || !enOfURLs.hasMoreElements())
				  {
					  Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Incorrect Input  Pkg=[" + pkgStr + "] Warning !!!!!!!!" ) ;
				  }
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);

			} 
			List<PkgNameResourcesContent> lisOfPkgNameResourcesContents1 = 
					StreamSupport
					.stream(Spliterators.spliteratorUnknownSize(enOfURLs.asIterator(), Spliterator.ORDERED),false) //for the Enumerated URLs(many) per pkg, 
					.map(elm -> funMapPkgToResourcesContenti.apply(elm,pkgStr))                                                             //every URL return one PkgNameResourcesContent &
					.collect(Collectors.toList()) ;                                                                // Collect the PkgNameResourcesContent
                                                                                                                   // pkgName gives one or more PkgNameResourcesContent/URL(s)
			//lisOfPkgNameResourcesContents1.forEach((pkgresContent) -> {	pkgresContent.setPkgName(pkgStr); }) ;
			//allLisPkgNameResContent = Stream.concat(allLisPkgNameResContent.stream(), lisOfPkgNameResourcesContents1.stream()).collect(Collectors.toList());
			allLisPkgNameResContent.addAll(lisOfPkgNameResourcesContents1); //All PkgNameResourcesContents Collected in One List
		} );
		List<PkgNameResourcesContent> sortedLisBylength =
				allLisPkgNameResContent.stream().sorted((rpkgContent1, rpkgContent2) ->
				rpkgContent1.getStrFile().trim().length() - rpkgContent2.getStrFile().trim().length()).distinct()
				.collect(Collectors.toList());
		return sortedLisBylength ;
	} 
	//
	public void lisClassPath()
	{
		//
		String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);
		List<String> classpathEntriesLis = Arrays.asList(classpathEntries);
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,"<<<< Start Lis of ClassPath  [java.class.path]" ) ;
		classpathEntriesLis.forEach(pathElm -> { 
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "lisClassPth Output List: :[" + pathElm +"]") ; 
		}) ;
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,"End Lis of ClassPath  [java.class.path] >>>>" ) ;
	    
	}
	
}

