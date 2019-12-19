package com.scan.annotate;

import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class OutPutLisOfAnnotClzsAndMethds {
	List<CapturedAnnotClzAndMethds> lisOfCap  = new ArrayList<CapturedAnnotClzAndMethds>();
	List<Class<? extends Annotation>> annInpClzLis = new ArrayList< Class<? extends Annotation> >();
	List< Class<? extends Annotation> > annInpMthds  = new ArrayList< Class<? extends Annotation> >();
	public void addClz(CapturedAnnotClzAndMethds cpACMs)
	{
		lisOfCap.add(cpACMs) ;
	}
	//
	
	//
//private volatile Boolean ret1 = false ;
//
public final Consumer<CapturedAnnotClzAndMethds>  peekTheAnnMthdsforClzFunci=  (capturedAnnotClzAndMethds) ->
{
	if ( null == annInpMthds) return ;
	Arrays.stream(capturedAnnotClzAndMethds.getAnoClz().getMethods()).forEach(m -> 
	{
		annInpMthds.forEach(clzInMthdAnnotaion -> {
			if ( m.isAnnotationPresent(clzInMthdAnnotaion)) {
				capturedAnnotClzAndMethds.addMthod(m);
				return ;
			}
		}		
		) ;
	} ) ;
} ;
//takeWhile

public final Consumer< Class<?> >  consumeClzAnnoteLisFunci = (clzz) ->
{
	if ( clzz == null ) return ;
	//ret1 = false ;
	annInpClzLis.forEach(annInp -> {
		if ( clzz.isAnnotationPresent(annInp) ) {
			CapturedAnnotClzAndMethds capAnnotClzAndMethds = new CapturedAnnotClzAndMethds() ;
			capAnnotClzAndMethds.setAnoClz(clzz);
			addClz(capAnnotClzAndMethds) ;
			peekTheAnnMthdsforClzFunci.accept(capAnnotClzAndMethds)  ; 
			return;
		}
	});
} ;	
	public static UUID dataToUUID(String str ) {
       
        return UUID.nameUUIDFromBytes(str.getBytes(StandardCharsets.UTF_8));
    }
	
	public String printResult()
	{
		StringBuilder sb = new StringBuilder () ;
		//sb.append("Result for ClassAnnotedWith:").append(annClz.getName()).append("\t").append("MethodAnnotedWith:").append(annMthds.toString()).append("::\n"); 
		lisOfCap.forEach(cap->{
			sb.append("\tClass:").append(cap.getAnoClz().getName()).append("\n");
			Arrays.stream(cap.getAnoClz().getAnnotations()).forEach(anncls->
			sb.append(anncls.toString()).append("\n"));
			sb.append("\tMethod:").append(cap.getAnoClz().getName()).append("\n");                                      
			cap.getMthodLis().stream().forEach(mthd -> {
				sb.append("\tMethod:").append(mthd.getName()).append("\n") ;			
				Arrays.stream(mthd.getAnnotations()).forEach ( anMthd ->{
					sb.append("\t\t\tValueOfAnnotaion:").append(anMthd.toString()).append("\n");    
				});
			}) ;

		}) ;


		return sb.toString();
}
	public List<Class<? extends Annotation>> getAnnInpClzLis() {
		return annInpClzLis;
	}
	public void setAnnInpClzLis(List<Class<? extends Annotation>> annInpClzLis) {  
		this.annInpClzLis = annInpClzLis;
	}
	public List<Class<? extends Annotation>> getAnnInpMthds() {
		return annInpMthds;
	}
	public void setAnnInpMthds(List<Class<? extends Annotation>> annInpMthds) {
		this.annInpMthds = annInpMthds;
	}



	public List<CapturedAnnotClzAndMethds> getLisOfCap() {
		return lisOfCap;
	}



	public void setLisOfCap(List<CapturedAnnotClzAndMethds> lisOfCap) {
		this.lisOfCap = lisOfCap;
	}
}