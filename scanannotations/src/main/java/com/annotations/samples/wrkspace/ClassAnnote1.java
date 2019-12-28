package com.annotations.samples.wrkspace ;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAnnote1 {
	public String valueOfClassAnnote1() default "";
}
