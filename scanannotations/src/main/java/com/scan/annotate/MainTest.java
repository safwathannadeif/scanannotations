package com.scan.annotate;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.annotations.samples.wrkspace.ClassAnnote1;
import com.annotations.samples.wrkspace.MethodAnnotate1;
import com.annotations.samples.wrkspace.ClassAnnoteX;
import com.annotations.samples.wrkspace.RepeatAnnoationsMethods.OrderAnnotion;

import com.common.SingletonRef;
import com.scan.annotate.query.ClzAndAssociatedMethdsQR;


//import com.annotaion.RepeatAnnoations.OrderAnnotion;

public class MainTest {
	//@FunctionalInterface
			//interface AnnoteLisFunction<T_M_OR_C, LisOfInpAnno, LisOfAnnotaionTypeLis> {
//				LisOfAnnotaionTypeLis applyIt(T_M_OR_C tmc, LisOfInpAnno la);
			//}
			
	
	@FunctionalInterface
	interface Query4Annotaion<P1,P2,P3,R> {
		R applyIt(P1 p1, P2 p2, P3 p3) ;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException  {
		//****How To Scan: -1 Start with new Instance for AnnottedClazsFrmPkgs   
		AnnottedClazsFrmPkgs anntClazFrmInResAndPkg = new AnnottedClazsFrmPkgs() ;
		anntClazFrmInResAndPkg.lisClassPath() ;
		//List<String> inputPKGs = Arrays.asList("com") ; //, "org.glassfish.tyrus","org.glassfish" ) ;
		//****How To Scan: -2 Pass the inputPKGs List 
		List<String> inputPKGs = Arrays.asList("com.shd.common1.json","com","com.xx","com.xxx.yyy","com.shd", "com.google.gson","com.trytest", "com.google", "javax.websocket") ; //, "org.glassfish.tyrus","org.glassfish" ) ;
		
		List<Class<? extends Annotation>>  annotedClassLis = new ArrayList<Class<? extends Annotation>> (); 
		List<Class<? extends Annotation>> annotedClz4MethodLis = new ArrayList<Class<? extends Annotation>> ();
		//****How To Scan: -3 Add Annotations for Classes  
		annotedClassLis.add(ClassAnnote1.class) ;
		annotedClassLis.add(ClassAnnoteX.class) ;
		//****How To Scan: -4 Add Annotations for Methods within the Classes given in step -2  
		annotedClz4MethodLis.add(MethodAnnotate1.class) ;
		annotedClz4MethodLis.add(OrderAnnotion.class) ;
		//annotedClz4MethodLis.add(ManyOrders.class) ; //ManyOrders.class it doest accept it
		//annotedClz4MethodLis.add(OrderAnnotion.class) ;  
		//****How To Scan: -5 Invoke doScanAndCapture   
		anntClazFrmInResAndPkg.doScanAndCapture(inputPKGs,annotedClassLis, annotedClz4MethodLis ) ;
		//****How To Scan: -6 the Scan output "OutPutLisOfAnnotClzsAndMethds" instance Captured in SingletonRef for Further Processing during the run life time 
		//****  Further Processing like  Annotation Query will use this instance/"OutPutLisOfAnnotClzsAndMethds"   
		//
		MainTest mainTest = new MainTest () ;
		mainTest.makeQuery();
		
	}
	public void makeQuery()
	{
		//List<ClzAndAssociatedMethdsQR> qList = SingletonRef.ONLYONEINS.getDoQuery().doIt(ClassAnnote1.class,null) ;  				//allow Query for Classes only
		//****How To Query: -1 Use SingletonRef to get the getDoQuery Function
		//****How To Query: -2 Pass the Annotations for Class [Mandatory] and option the annotation for method within the annotated classes 
		//****How To Query: -3 the  List<ClzAndAssociatedMethdsQR> return the result of the Query
		List<ClzAndAssociatedMethdsQR> qList = SingletonRef.ONLYONEINS.getDoQuery().doIt(ClassAnnote1.class,OrderAnnotion.class) ;  // class and Method within the class
		
		SingletonRef.ONLYONEINS.getDispLogger().info("Start Query Disply .....") ;
		SingletonRef.ONLYONEINS.getDispLogger().info(qList.toString()) ;
		SingletonRef.ONLYONEINS.getDispLogger().info("End Query Disply .....") ;
		//****How To Query: -4 Process the result from the output list with getAnnotationsByType Java API as per the next snapshot lines  
		qList.forEach(antCls -> {														//clamqr  == ClzAndAssociatedMethdsQR
			ClassAnnote1[]   classAnnote1  = 	antCls.getClz().getAnnotationsByType(ClassAnnote1.class) ; 
			
			System.out.println("For Class[" + antCls.getClz().getName() + "]  and Annoation [" + ClassAnnote1.class.getName() + "]  Values:: ==>") ;
			if (classAnnote1.length == 0) System.out.println("") ;
			for ( ClassAnnote1 a:classAnnote1)
			{
				System.out.println("Value = [" + a.valueOfClassAnnote1() + "]" ) ;
			}
			//
			
			antCls.getMethodLis().forEach(m -> {
				MethodAnnotate1[]   methodAnnotate1  = 	m.getAnnotationsByType(MethodAnnotate1.class) ; 
				System.out.println("For Class[" + antCls.getClz().getName() + "]  and Method [" + m.getName() +"]  Annoation [" + MethodAnnotate1.class.getName() + "]  Values:: ==>") ;
				if (methodAnnotate1.length == 0) System.out.println("") ;
				for ( MethodAnnotate1 ma:methodAnnotate1)
				{
					System.out.println("Value = [" + ma.valueOfMethodAnnotate1()  + "]" ) ;
				}
				   methodAnnotate1  = 	null ;
				   OrderAnnotion[]   orderAnnotion  = 	m.getAnnotationsByType(OrderAnnotion.class) ; 
					System.out.println("For Class[" + antCls.getClz().getName() + "]  and Method [" + m.getName() +"]  Annoation [" + OrderAnnotion.class.getName() + "]  Values:: ==>") ;
					if (orderAnnotion.length == 0) System.out.println("");
					for ( OrderAnnotion oa:orderAnnotion)
					{
						System.out.println("Value = [orderItem=" + oa.orderItem()  + "] orderQuantity=[" +oa.orderQuantity() +"]") ;
					}
			});
			System.out.println("For Class[" + antCls.getClz().getName() + "]  and Annoation [" + ClassAnnoteX.class.getName() + "]  Values:: ==>") ;
			ClassAnnoteX[]   classAnnoteX  = 	antCls.getClz().getAnnotationsByType(ClassAnnoteX.class) ;
			if (classAnnoteX.length == 0) System.out.println("") ;
			for ( ClassAnnoteX b:classAnnoteX)
			{
				System.out.println("Value = [" + b.valueOfClassAnnoteX() + "]" ) ;
			}

			//		
		} ) ;
	
	}
}
