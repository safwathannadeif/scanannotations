package com.scan.annotate;

import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import com.common.SingletonRef;
import com.scan.annotate.CapturedAnnotClzAndMethds.AnnotedListForEachMthd;

public class OutPutLisOfAnnotClzsAndMethds {
	List<CapturedAnnotClzAndMethds> lisOfCap  = new ArrayList<CapturedAnnotClzAndMethds>();
	List<Class<? extends Annotation>> annInpClzLis = new ArrayList< Class<? extends Annotation> >();
	List< Class<? extends Annotation> > annInpMthdsLis  = new ArrayList< Class<? extends Annotation> >();
	List<AnnotationDetailsFounded> annFoundForClz = null ;
	public OutPutLisOfAnnotClzsAndMethds()
	{
		SingletonRef.ONLYONEINS.setOutPutLisOfAnnotClzsAndMethds(this);
	
	}
	public int addCap(CapturedAnnotClzAndMethds cpACMs)
	{
		lisOfCap.add(cpACMs) ;
		return (lisOfCap.size() -1) ;
	}
	public void rmCap(int inx)
	{
		lisOfCap.remove(inx) ;
	}
	//
//	public interface AnnotaionTypeLis<T_C_Or_M> { // T Might be Class or Method
//		List<Class<? extends Annotation>> getAnnotedList();
//		void setAnnotedList(List<Class<? extends Annotation>> annotedList);
//		T_C_Or_M getTypeClz();
//		void setTypeClz(T_C_Or_M tCOrM); // Might be Class or Method 
//	}
	//
//private volatile Boolean ret1 = false ;
//

public final Consumer<CapturedAnnotClzAndMethds>  peekTheAnnMthdsforClzFunci=  (capturedAnnotClzAndMethds) ->
{
	if ( null == annInpMthdsLis) return ;
	//List<AnnotationDetailsFounded> annFoundLisForMthd = new ArrayList<AnnotationDetailsFounded>() ;
	Arrays.stream(capturedAnnotClzAndMethds.getAnoClz().getMethods()).forEach( methodi -> 
	{
		AnnotedListForEachMthd annotedListForOneMthdi = capturedAnnotClzAndMethds.makeNewAnnotedListForOneMthd(methodi) ;
		List<AnnotationDetailsFounded> annFoundLisForMthd = new ArrayList<AnnotationDetailsFounded>() ;
		for ( Class<? extends Annotation> methodInpAnnot  : annInpMthdsLis) 
		{
			//Annotation[] anAry = methodi.getAnnotationsByType(methodInpAnnot) ;
			//List<Class<? extends Annotation>> lisOfTheAry = Arrays.asList(anAry) ;
			////
			 //lisOfTheAry = new ArrayList< Class<? extends Annotation> >();
			 List< ? extends Annotation> lisOfTheAry =  Arrays.asList(methodi.getAnnotationsByType(methodInpAnnot)); 
			Objects.requireNonNull(lisOfTheAry, "Return Array from getAnnotationsByType must bot be NULL") ;	//Over Protection--Just in Case of null 
			if ( lisOfTheAry.size() > 0  ) {
				AnnotationDetailsFounded annotationDetailsFounded = new AnnotationDetailsFounded() ;
				annotationDetailsFounded.setAnnInpToFind(methodInpAnnot);
				annotationDetailsFounded.setAnnOutFounded(lisOfTheAry);
				
				annFoundLisForMthd.add(annotationDetailsFounded); 
				annotedListForOneMthdi.setMethodAnnotDetailsFoundedLis(annFoundLisForMthd); // ??
			}
			lisOfTheAry = null ;	 
				
				
			}
		
	
		if ( annotedListForOneMthdi.getMethodAnnotDetailsFoundedLis() == null ) 
		{
			annFoundLisForMthd = null ;
			annotedListForOneMthdi = null ;	
		}
		else
		{
			capturedAnnotClzAndMethds.addAnnotedMethodToListForClzMethods(annotedListForOneMthdi); 
		}
			                            
} ) ;
};

//} ;
//takeWhile
//public final Consumer< Class<?> >  consumeClzAnnoteLisFunci = (clzz) ->
//{
//	if ( clzz == null ) return ;
//	
//	CapturedAnnotClzAndMethds capAnnotClzAndMethds = new CapturedAnnotClzAndMethds() ;
//	capAnnotClzAndMethds.setAnoClz(clzz);
//	int inexofCap = addCap(capAnnotClzAndMethds) ;
//	annInpClzLis.forEach(annInp -> {
//		if ( clzz.isAnnotationPresent(annInp) ) {
//			capAnnotClzAndMethds.addAnnotedToListFoClass(annInp);
//		}
//	});
//	if ( capAnnotClzAndMethds.getClazAnnotedList().size() == 0  )
//	{
//		rmCap(inexofCap) ;
//		capAnnotClzAndMethds = null ;
//		return ;
//	}
//	peekTheAnnMthdsforClzFunci.accept(capAnnotClzAndMethds)  ; 
//	return;
//} ;	
	public static UUID dataToUUID(String str ) {
       
        return UUID.nameUUIDFromBytes(str.getBytes(StandardCharsets.UTF_8));
    }
	
	

	public List<Class<? extends Annotation>> getAnnInpClzLis() {
		return annInpClzLis;
	}
	public void setAnnInpClzLis(List<Class<? extends Annotation>> annInpClzLis) {  
		this.annInpClzLis = annInpClzLis;
	}
	public List<Class<? extends Annotation>> getAnnInpMthds() {
		return annInpMthdsLis;
	}
	public void setAnnInpMthds(List<Class<? extends Annotation>> annInpMthds) {
		this.annInpMthdsLis = annInpMthds;
	}



	public List<CapturedAnnotClzAndMethds> getLisOfCap() {
		return lisOfCap;
	}



	public void setLisOfCap(List<CapturedAnnotClzAndMethds> lisOfCap) {
		this.lisOfCap = lisOfCap;
	}
}