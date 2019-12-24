package com.scan.annotate;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.annotaion.ClassAnnote1;
import com.annotaion.ClassAnnoteX;
import com.annotaion.MethodAnnotate1;

import com.annotaion.RepeatAnnoationsMethods.OrderAnnotion;
import com.scan.annotate.AnnottedClazsFrmPkgs;

//import com.annotaion.RepeatAnnoations.OrderAnnotion;

public class MainTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException  {
		AnnottedClazsFrmPkgs anntClazFrmInResAndPkg = new AnnottedClazsFrmPkgs() ;
		anntClazFrmInResAndPkg.lisClassPath() ;
		List<String> inputPKGs = Arrays.asList("com") ; //, "org.glassfish.tyrus","org.glassfish" ) ;
		//List<String> inputPKGs = Arrays.asList("com.shd.common1.json","com","com.xx","com.xxx.yyy","com.shd", "com.google.gson","com.trytest", "com.google", "javax.websocket") ; //, "org.glassfish.tyrus","org.glassfish" ) ;
		
		List<Class<? extends Annotation>>  annotedClassLis = new ArrayList<Class<? extends Annotation>> (); 
		List<Class<? extends Annotation>> annotedClz4MethodLis = new ArrayList<Class<? extends Annotation>> ();
		annotedClassLis.add(ClassAnnote1.class) ;
		annotedClassLis.add(ClassAnnoteX.class) ;
		annotedClz4MethodLis.add(MethodAnnotate1.class) ;
		//annotedClz4MethodLis.add(ManyOrders.class) ; //ManyOrders.class it doest accept it
		annotedClz4MethodLis.add(OrderAnnotion.class) ;  
		anntClazFrmInResAndPkg.doScanAndCapture(inputPKGs,annotedClassLis, annotedClz4MethodLis ) ;
		
	}

}
