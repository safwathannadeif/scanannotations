package com.scan.annotate.query;
//Adding Query Annotation for the scanned result
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClzAndAssociatedMethdsQR {
	public Class<?> clz = null;
	public List<Method> methodLis = new ArrayList<Method>() ; 
	public ClzAndAssociatedMethdsQR() {
		
	}
	public Class<?> getClz() {
		return clz;
	}
	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
	public List<Method> getMethodLis() {
		return methodLis;
	}
	public void setMethodLis(List<Method> methodLis) {
		this.methodLis = methodLis;
	}
	@Override
	public String toString() {
		return "ClzAndAssociatedMethdsQR [clz=" + clz + ", methodLis=" + methodLis + "]";
	}
}