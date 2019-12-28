package com.annotations.samples.wrkspace ;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAnnoteX {
	public String valueOfClassAnnoteX() default "";
}
