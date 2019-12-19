package com.scan.annotate;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CapturedAnnotClzAndMethds {
	Path path = null ;
	Class<?> anoClz ;
	List<Method> mthodLis  = new ArrayList<Method>();
	public void addMthod(Method mthd)
	{
		mthodLis.add(mthd) ;
	}
	public Class<?> getAnoClz() {
		
		return anoClz;
	}
	public void setAnoClz(Class<?> anoClz) {
		this.anoClz = anoClz;
	}
	public List<Method> getMthodLis() {
		return mthodLis;
	}
	public void setMthodLis(List<Method> mthodLis) {
		this.mthodLis = mthodLis;
	}
	
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "CapturedAnnotClzAndMethds [path=" + path + ", anoClz=" + anoClz + ", mthodLis=" + mthodLis + "]";
	}

}
