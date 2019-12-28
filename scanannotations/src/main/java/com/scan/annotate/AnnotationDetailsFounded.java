package com.scan.annotate;

import java.lang.annotation.Annotation;
import java.util.List;

//Annotation Details Founded    One to Many
public class AnnotationDetailsFounded {
	Class<? extends Annotation> annInpToFind = null ; // one input for class or method
	List<? extends Annotation> annOutFounded = null ; //new ArrayList<? extends Annotation>() ; //null ; // 1-many if annotation found or 0 length if not found
	
	public Class<? extends Annotation> getAnnInpToFind() {
		return annInpToFind;
	}
	public void setAnnInpToFind(Class<? extends Annotation> annInpToFind) {
		this.annInpToFind = annInpToFind;
	}
	public List<? extends Annotation> getAnnOutFounded() {
		return annOutFounded;
	}
	public void setAnnOutFounded(List<? extends Annotation> annOutFoundedi) {
		
		// Clone a list Yes Clone it     
		//Clone annOutFounded  = annOutFoundedi.stream().collect(Collectors.toList()); 
		annOutFounded=annOutFoundedi ;
		 
	}
	
}
//  isAnnotationPresent doesn't return the repeatable annotations while getAnnotationsByType does
//// getAnnotationsByType RETURN Array for Repeatable Annotations and non-Repeatable
////https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/AnnotatedElement.html
//// T[] annotationsByType = m.getAnnotationsByType(inpAnnotationClass); it returns Array 
///*
// * //*** Create the list since we cannot instantiate a generic array of a
// * specific type parameter since array is not an object** 
// * //*** We need to store  it in the Object so we have to the list from the Array
// ***//
///** List<? extends Annotation> lisOfTheAry = Arrays.asList(mi.getAnnotationsByType(inpAnnotedClzs));
//// *** We Cannot instantiate a generic array of a specific type parameter since array is not an object***/
//// the annotation Array or zero length Array.it serves Repeatable annotations and non-Repeatable as well
//*/