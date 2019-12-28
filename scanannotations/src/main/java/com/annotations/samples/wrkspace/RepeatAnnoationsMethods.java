package com.annotations.samples.wrkspace ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
//Annotation Repeated for Testing Scan Annotation
public class RepeatAnnoationsMethods
{
@Repeatable(ManyOrders.class)
@Target(ElementType.METHOD)
public @interface OrderAnnotion {
  String orderItem() default "itemx";
  String orderType() default "typey";
  int  orderQuantity() default 20   ; 
}

@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyOrders {
	   OrderAnnotion[] value() ;
	}
}

