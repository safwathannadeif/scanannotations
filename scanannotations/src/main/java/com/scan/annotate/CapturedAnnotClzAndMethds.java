package com.scan.annotate;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
//import com.scan.annotate.OutPutLisOfAnnotClzsAndMethds.AnnotaionTypeLis;


public class CapturedAnnotClzAndMethds {
	private Path path = null ;
	private Class<?> anoClz ;
	
	private List<AnnotationDetailsFounded> clzAnnotDetailsFoundedLis = null ; 
	private List<AnnotedListForOneMthd> mthodsAnnotedListForClz = new ArrayList<AnnotedListForOneMthd>();
		
	//Class for Antedated Methods
	//public class AnnotedListForOneMthd implements AnnotaionTypeLis<Method> {
	public class AnnotedListForOneMthd  {
	//private List<Class<? extends Annotation>> annotedListForMthd = new ArrayList<Class<? extends Annotation>>(); 
	private Method method; 								//// Might be Class or Method now Only for Method .. OK
	private List<AnnotationDetailsFounded> methodAnnotDetailsFoundedLis = null ;
			public AnnotedListForOneMthd( )  {
		
			}
			public AnnotedListForOneMthd(Method m )  {
				setTypeClz(m);
			}
			public Method getTypeClz() {
				return method;
			}
			//@Override
			public void setTypeClz(Method t) {
				this.method = t;
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
	public AnnotedListForOneMthd makeNewAnnotedListForOneMthd(Method m)
	{
		return new AnnotedListForOneMthd(m) ;
	}
	
	//
	/* Silly Mistake 
	 * private AnnotedListForMthd annotedListForMthdi = null ;
	 * 
	 * public void makeNewAnnotedListForMthd(Method m) { annotedListForMthdi = new
	 * AnnotedListForMthd(m) ; System.out.println
	 * ("YYYYYYY  annotedListForMthdi Made from makeNewAnnotedListForMthd" +
	 * annotedListForMthdi.toString()) ; }
	 */
	
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
//	public List<Class<? extends Annotation>> getClazAnnotedList() {
//		return annotedListForClz;
//	}
//	public void setClazAnnotedList(List<Class<? extends Annotation>> clazAnnotedList) {
//		this.annotedListForClz = clazAnnotedList;
//	}
//	public void  addAnnotedToListForClass(Class<? extends Annotation> e) {
//		annotedListForClz.add(e);
//	}
	public void addAnnotedMethodToListForClzMethods(AnnotedListForOneMthd am) 
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

	public List<AnnotedListForOneMthd> getMthodsAnnotedListForClz() {
		return mthodsAnnotedListForClz;
	}

//	public void setMthodsAnnotedListForClz(List<AnnotedListForOneMthd> mthodsAnnotedListForClz) {
//		this.mthodsAnnotedListForClz = mthodsAnnotedListForClz;
//	}
	
	

}
