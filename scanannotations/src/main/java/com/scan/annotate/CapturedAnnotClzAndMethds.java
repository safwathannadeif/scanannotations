package com.scan.annotate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.scan.annotate.query.ClzAndAssociatedMethdsQR;


public class CapturedAnnotClzAndMethds {
	private Path path = null ;
	private Class<?> anoClz ;
	
	private List<AnnotationDetailsFounded> clzAnnotDetailsFoundedLis = null ; 
	private List<AnnotedListForEachMthd> mthodsAnnotedListForClz = new ArrayList<AnnotedListForEachMthd>();
		
	//Class for Antedated Methods
	//public class AnnotedListForOneMthd implements AnnotaionTypeLis<Method> {
	public class AnnotedListForEachMthd  {
	//private List<Class<? extends Annotation>> annotedListForMthd = new ArrayList<Class<? extends Annotation>>(); 
	private Method method; 								
	private List<AnnotationDetailsFounded> methodAnnotDetailsFoundedLis = null ;
			public AnnotedListForEachMthd( )  {
		
			}
			@Override
			public String toString() {
				return "AnnotedListForOneMthd [method=" + method + ", methodAnnotDetailsFoundedLis="
						+ methodAnnotDetailsFoundedLis + "]";
			}
			public AnnotedListForEachMthd(Method m )  {
				setMthd(m);
			}
			public Method getMthd() {
				return method;
			}
			//@Override
			public void setMthd(Method mthd) {
				this.method = mthd;
			}
			
//			public void addAnnotedItemForMthod (Class<? extends Annotation> anoclzForMthod )
//			{
//				annotedListForMthd.add(anoclzForMthod) ;
//			}
	// 
			public List<AnnotationDetailsFounded> getMethodAnnotDetailsFoundedLis() {
				return methodAnnotDetailsFoundedLis;
			}
			public void setMethodAnnotDetailsFoundedLis(List<AnnotationDetailsFounded> methodAnnotDetailsFoundedLis) {
				this.methodAnnotDetailsFoundedLis = methodAnnotDetailsFoundedLis;
			}
			
	}
	public AnnotedListForEachMthd makeNewAnnotedListForOneMthd(Method m)
	{
		return new AnnotedListForEachMthd(m) ;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Class<?> getAnoClz() {
		return anoClz;
	}
	public void setAnoClz(Class<?> anoClz) {
		this.anoClz = anoClz;
	}
	
	public ClzAndAssociatedMethdsQR  queryFoundAnnotedClz ( Class<? extends Annotation> inpQAnnotationclz,Class<? extends Annotation> inpQAnnotationMthodclz)
	{
		ClzAndAssociatedMethdsQR clzAndAssociatedMethdsQR = new ClzAndAssociatedMethdsQR() ;
		clzAnnotDetailsFoundedLis.forEach(adfLis ->{ 						// adfLis == AnnotationDetailsFounded adfLis Class 
			
			if ( adfLis.getAnnInpToFind().getCanonicalName().trim().equals(inpQAnnotationclz.getCanonicalName().trim()) )
			{
				
				clzAndAssociatedMethdsQR.setClz(getAnoClz());
				
			}
		}) ; //all Annotated items for one class 
		if ( null == clzAndAssociatedMethdsQR.getClz() ) return clzAndAssociatedMethdsQR ;
		if (null == inpQAnnotationMthodclz) return clzAndAssociatedMethdsQR ; //Where annotation required  only for classes 
		
		getMthodsAnnotedListForClz().forEach( alfm -> {     					//alfm  == AnnotedListForEachMthd
			
			alfm.getMethodAnnotDetailsFoundedLis().forEach(mdflis ->{   		// mdflis  == MethodAnnotDetailsFoundedLis  method
				
				if (mdflis.getAnnInpToFind().getCanonicalName().trim().equals(inpQAnnotationMthodclz.getCanonicalName().trim()))	
				{
					clzAndAssociatedMethdsQR.getMethodLis().add(alfm.getMthd()) ;	
				}
				
			}) ;	
			

				
	}) ;
	return	clzAndAssociatedMethdsQR ;
	
}	
public Boolean isAnnotedClz ( Class<? extends Annotation> annotationclz)
{
	Boolean bRet  = false ;
	Annotation[] anAry = anoClz.getAnnotationsByType(annotationclz) ;
	if ( anAry.length > 0 ) bRet = true ; 
	return bRet ;
	
}
//
volatile Boolean  bRet  = false ;
public Boolean isAnnotedMthdClz ( Class<? extends Annotation> inpQAnnotationMthodclz) 
{
	 //mthodsAnnotedListForClz
	mthodsAnnotedListForClz.stream().forEach(item1 ->  {						//itemAnnotedListForOneMthd[item1]
		item1.getMethodAnnotDetailsFoundedLis().forEach(item2 -> {				//List<AnnotationDetailsFounded> item AnnotationDetailsFounded[item2]
		if (item2.getAnnInpToFind().getClass().getName().trim().equalsIgnoreCase(inpQAnnotationMthodclz.getClass().getName().trim()))	
				{
						if (!bRet) {
							bRet=true ;
							
						}
				}
		});   
		
	}
	);
	
	
return 	bRet ;	
}
//


	public void addAnnotedMethodToListForClzMethods(AnnotedListForEachMthd am) 
	{
		mthodsAnnotedListForClz.add(am) ;
	}
	public List<AnnotationDetailsFounded> getClzAnnotDetailsFoundedLis() {
		return clzAnnotDetailsFoundedLis;
	}

	public void setClzAnnotDetailsFoundedLis(List<AnnotationDetailsFounded> clzAnnotDetailsFoundedLis) {
		this.clzAnnotDetailsFoundedLis = clzAnnotDetailsFoundedLis;
	}
	
	
	@Override
	public String toString() {
		return "CapturedAnnotClzAndMethds [path=" + path + ", anoClz=" + anoClz + ", clazAnnotedList=" + clzAnnotDetailsFoundedLis
				+ ", mthodsAnnotedListForClz=" + mthodsAnnotedListForClz.toString() + "]";
	}

	public List<AnnotedListForEachMthd> getMthodsAnnotedListForClz() {
		return mthodsAnnotedListForClz;
	}



}
