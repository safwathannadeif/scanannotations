package com.scan.annotate.logging;



import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;



public class LoggerRef {

	private final static String nameId="disply" ;
	private static  Logger displog = null ;

	public static synchronized void  makeLogRef()
	{

		Logger loggerToUse = Logger.getLogger(nameId); 
		assert loggerToUse != null ;
		loggerToUse.setUseParentHandlers(false) ;//No Console
		Handler[] handlers = loggerToUse.getHandlers();
		for(Handler handler : handlers) {
			loggerToUse.removeHandler(handler);
		}


		Handler  consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new LogFormat());
		//consoleHandler.setLevel(Level.ALL);
		loggerToUse.addHandler(consoleHandler);

		displog = loggerToUse ;


	}

	public static Logger getDispLogger() {
		if ( displog == null ) makeLogRef() ;
		assert displog != null : "here is null >>" ;

		return displog;
	}

}