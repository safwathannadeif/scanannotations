package com.scan.annotate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.common.SingletonRef;


public class PkgNameResourcesContent {
private PkgNameResourcesContentBase2 pkgNameResourcesContentBase2 = new PkgNameResourcesContentBase2();
private URL url ;
private String strFile ;
private  Map<PkgNameResourcesContentBase2,List<PkgNameResourcesContent>> filterTracking = null ;

public class PkgNameResourcesContentBase2 {
	private String pkgName;
	private URI uri;

	public PkgNameResourcesContentBase2() {
	}

	public String getPkgName() {
		return pkgName;
	}

	public URI getUri() {
		return uri;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
	@Override
	public int hashCode()
	{
	    return uri.hashCode();
	}
	@Override
	public boolean equals(Object o)
	{
	    return this.uri.equals(o);
	}
}
//
public void setContentByURL(URL uRLi) throws URISyntaxException, UnsupportedEncodingException
{
	setUrl(uRLi);
	pkgNameResourcesContentBase2.uri = uRLi.toURI() ; 
	strFile = URLDecoder.decode(uRLi.getFile(), "UTF-8");
	if ( null == strFile || strFile.isEmpty())
	Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Incorrect Input  URL=[" + uRLi + "]" ) ;

}
public String getPkgName() {
	return pkgNameResourcesContentBase2.pkgName;
}
public URI getUri() {
	return pkgNameResourcesContentBase2.uri;
}
public URL getUrl() {
	return url;
}
public String getStrFile() {
	return strFile;
}
public void setPkgName(String pkgName) {
	this.pkgNameResourcesContentBase2.pkgName = pkgName;
}
public void setUri(URI uri) {
	this.pkgNameResourcesContentBase2.uri = uri;
}
public void setUrl(URL url) {
	this.url = url;
}
public void setStrFile(String strFile) {
	this.strFile = strFile;
}

public Boolean  filterIt2(PkgNameResourcesContent toUseStrFileAsStartWith) {
	Boolean retBoolean = false ;
	if (getStrFile().trim().startsWith(toUseStrFileAsStartWith.getStrFile().trim()) && !getStrFile().trim().equals(toUseStrFileAsStartWith.getStrFile().trim()))  retBoolean = true ;
	if (retBoolean) {
		List<PkgNameResourcesContent> wList= getFilterTracking().get(toUseStrFileAsStartWith.getPkgNameResourcesContentBase2()) ;
		if ( null ==  wList){
			//List<PkgNameResourcesContent> lisOfPkgRes = new ArrayList<PkgNameResourcesContent>() ;
			wList = new ArrayList<PkgNameResourcesContent>() ;
			wList.add(this) ;
			getFilterTracking().put(toUseStrFileAsStartWith.getPkgNameResourcesContentBase2(), wList) ;
		}
		else
		{
			wList.add(this) ; 
		}
	}
	return retBoolean ;
}
@Override
public String toString() {
	return "PkgNameResourcesContent [pkgName=" + pkgNameResourcesContentBase2.pkgName + ", uri=" + pkgNameResourcesContentBase2.uri + ", url=" + url + ", strFile=" + strFile + "]";
}
public String priPkgNameResourcesContent()
{
	return "[ [pkgName=[" + pkgNameResourcesContentBase2.pkgName + "][, uri=[" + pkgNameResourcesContentBase2.uri + "] ]" ;
}
public Map<PkgNameResourcesContentBase2, List<PkgNameResourcesContent>> getFilterTracking() {
	return filterTracking;
}
public void setFilterTracking(Map<PkgNameResourcesContentBase2, List<PkgNameResourcesContent>> filterTracking2) {
	this.filterTracking = filterTracking2;
}
public void priFilterTracking()
{
	StringBuilder sb = new StringBuilder()  ; //if multi-threaded  tringBuffer
	filterTracking.entrySet().forEach( entry -> {
	    sb.append("\n["+ entry.getKey().getPkgName()+"] [" +  entry.getKey().getUri() + "] Includes =>") ;
	    entry.getValue().forEach(item -> {
	    	sb.append("\n\t\t["+item.getPkgName() +"] [" +  item.getUri() + "]") ;
	    });
	});
	SingletonRef.ONLYONEINS.getDispLogger().info(sb.toString()) ;

}
public PkgNameResourcesContentBase2 getPkgNameResourcesContentBase2() {
	return pkgNameResourcesContentBase2;
}
}
