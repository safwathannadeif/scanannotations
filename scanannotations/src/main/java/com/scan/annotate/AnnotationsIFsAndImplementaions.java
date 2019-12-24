/********************************* No Need ****************************************/
//package com.scan.annotate;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//public class AnnotationsIFsAndImplementaions {
	
//public interface AnnotaionTypeLis<T_C_Or_M> { // T Might be Class or Method
//		List<Class<? extends Annotation>> getAnnotedList();
//		void setAnnotedList(List<Class<? extends Annotation>> annotedList);
//		T_C_Or_M getTypeClz();
//		void setTypeClz(T_C_Or_M tCOrM); // Might be Class or Method
//	}
//	
//	public class AnnotedListForMthd implements AnnotaionTypeLis<Method> {
//		private List<Class<? extends Annotation>> annotedList;
//		private Method method; //// Might be Class or Method
//		@Override
//		public List<Class<? extends Annotation>> getAnnotedList() {
//			return annotedList;
//		}
//		@Override
//		public void setAnnotedList(List<Class<? extends Annotation>> annotedList) {
//			this.annotedList = annotedList;
//		}
//		@Override
//		public Method getTypeClz() {
//			return method;
//		}
//		@Override
//		public void setTypeClz(Method t) {
//			this.method = t;
//		}
//	}
////	
//}
	
//@FunctionalInterface
//interface AnnoteLisFunction<T_M_OR_C, LisOfInpAnno, LisOfAnnotaionTypeLis> {
//	LisOfAnnotaionTypeLis applyIt(T_M_OR_C tmc, LisOfInpAnno la);
//}
//AnnoteLisFunction<Class<?>, List<Class<? extends Annotation>>, AnnotedListForClz> AnnotClzLisFuncni = (clz,annInpClzLis) -> {
//	AnnotedListForClz annotedListForClz = new AnnotedListForClz() ;
//	annotedListForClz.setTypeClz(clz) ;
//	annInpClzLis.forEach(annInp -> {
//		if ( clz.isAnnotationPresent(annInp) ) {
//			//annotedListForClz.getAnnotedList().add(annInp)	;
//		}
//	});
//	if ( annotedListForClz.getAnnotedList().size() == 0 ) annotedListForClz= null ;
//	return annotedListForClz;
//};
//AnnoteLisFunction<Method, List<Class<? extends Annotation>>, AnnotedListForMthd> AnnotMthdLisFuncni = (clz,
//		annInpClzLis) -> {
//	return null;
//};

//public class AnnotedListForClz implements AnnotaionTypeLis<Class<?>> {
//	private List<Class<? extends Annotation>> annotedList;
//	private Class<?> clz; //// Might be Class or Method
//	public AnnotedListForClz() {
//		annotedList = new ArrayList<Class<? extends Annotation>> ();
//	}
//	@Override
//	public List<Class<? extends Annotation>> getAnnotedList() {
//		return annotedList;
//	}
//	@Override
//	public void setAnnotedList(List<Class<? extends Annotation>> annotedList) {
//		this.annotedList = annotedList;
//	}
//	@Override
//	public Class<?> getTypeClz() {
//		return clz;
//	}
//	@Override
//	public void setTypeClz(Class<?> clz) {
//		this.clz = clz;
//	}
//}
//
/********************************* No Need ****************************************/

