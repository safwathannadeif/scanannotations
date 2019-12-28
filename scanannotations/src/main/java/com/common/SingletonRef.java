package com.common;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.scan.annotate.logging.LoggerRef;
import com.scan.annotate.query.ClzAndAssociatedMethdsQR;

import com.scan.annotate.CapturedAnnotClzAndMethds;
import com.scan.annotate.OutPutLisOfAnnotClzsAndMethds;


// Singleton Ref 
public enum SingletonRef {
	ONLYONEINS ;
	
	private OutPutLisOfAnnotClzsAndMethds  outPutLisOfAnnotClzsAndMethds= null ;
	private   Logger displog = null ;

	@FunctionalInterface
	interface Query4AnnotaionGeneric<P1,P2,P3,R> {          //Generic 
		R applyIt(P1 p1, P2 p2, P3 p3) ;
	}
//
	@FunctionalInterface
	interface Query4Annotaion				// it should be non-generic  
	{
		ClzAndAssociatedMethdsQR applyIt(CapturedAnnotClzAndMethds p1, Class<? extends Annotation> p2, Class<? extends Annotation> p3) ;
	}
	
	public Query4Annotaion query4Annotaioni = CapturedAnnotClzAndMethds::queryFoundAnnotedClz ;
    
	@FunctionalInterface
	public 	interface DoQuery {         
		List<ClzAndAssociatedMethdsQR> doIt(Class<? extends Annotation> clzAnnot, Class<? extends Annotation> mthdAnnot) ;
	}
	public DoQuery doQueryi = ( (clzAnnot,mthdAnnot) -> {
		List<ClzAndAssociatedMethdsQR> qList = 
				getOutPutLisOfAnnotClzsAndMethds().getLisOfCap().stream()     //capItem == CapturedAnnotClzAndMethds  queryFoundAnnotedClz
				.map(capItem->  query4Annotaioni.applyIt(capItem,clzAnnot,mthdAnnot) )
				.filter(capItem -> {
					Boolean br = true ;
					if (capItem.getClz() == null )
					{
						capItem = null ;
						br = false ;
					}
					return br ; 
				})
				
				.collect(Collectors.toList());
		
		return qList ;
		
	});
public DoQuery getDoQuery()
{
	return doQueryi ;
}
 // SingletonRef.getDispLogger()
   public  Logger getDispLogger() {
		if ( displog == null ) displog = LoggerRef.makeLogRef() ;
		 Objects.requireNonNull(displog, "NULL!! for displog" ) ;
		//assert displog != null : "here is null >>" ;

		return displog;
   }

public OutPutLisOfAnnotClzsAndMethds getOutPutLisOfAnnotClzsAndMethds() {
	return outPutLisOfAnnotClzsAndMethds;
}

public void setOutPutLisOfAnnotClzsAndMethds(OutPutLisOfAnnotClzsAndMethds outPutLisOfAnnotClzsAndMethds) {
	this.outPutLisOfAnnotClzsAndMethds = outPutLisOfAnnotClzsAndMethds;
}
//
public String printResult(List<CapturedAnnotClzAndMethds> lisOfCap)
{
	StringBuilder sb = new StringBuilder () ; 
	lisOfCap.forEach(cap-> {
		sb.append("Class:").append(cap.getAnoClz().getName()).append("\n");
		sb.append("\t\tAnnotaionFoundInClass::\n") ;
		cap.getClzAnnotDetailsFoundedLis().forEach(foundInLis-> {
			sb.append("\t\t\t" +foundInLis.getAnnInpToFind().toString());
			foundInLis.getAnnOutFounded().forEach(annOutFounded ->{
				sb.append("\n\t\t\t\t" +annOutFounded.toString()) ; 
			}) ;
			sb.append("\n") ;
		}); //
		sb.append( "---------------------------------------------------------------------------\n") ;
		sb.append("\t\tMethods::\n") ;
		cap.getMthodsAnnotedListForClz().forEach( annotListForOneMthd->
		{
			sb.append("\t\tMethod:" + annotListForOneMthd.getMthd().getName()).append("\n"); 
			sb.append("\t\t\tAnnotaionsForMethod::").append("\n") ; 
			annotListForOneMthd.getMethodAnnotDetailsFoundedLis()             
			.forEach( anFoundMthdLis -> {
				sb.append("\t\t\t" +anFoundMthdLis.getAnnInpToFind().toString());
				//sb.append("\t\t\\t" + anMthd.getAnnotations().toString() ) ; //Array of Array of Annotations
				////////sb.append("\t\t\t").append(anFoundMthdLis.toString()).append("\n") ;
				anFoundMthdLis.getAnnOutFounded().forEach(annOutFounded ->{
					sb.append("\n\t\t\t\t" +annOutFounded.toString()) ; //+"\n");	
				}) ;	
				sb.append("\n") ;
			}) ;


		}) ;
		sb.append( "-----------------------------------------------------------------------------------------------------------------------------------------------------\n") ;

	});
	return sb.toString();
}
}