package ar.fiuba.tecnicas.slf4jadapter;

import org.slf4j.Logger;
import org.slf4j.Marker;

import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Level;

public final class LoggerAdapter implements Logger
{
	String nombreLogger;
	
	private String objArrayToString(Object...arg1)
	{
		String string = "";
		for (int i = 0; i < arg1.length; ++i)
		{
			if (i > 0) string += "-";
			string += arg1[i].toString();
		}	
		return string;
	}
	
	public LoggerAdapter(String arg0) 
	{
		nombreLogger=arg0;
	}

	@Override
	public void debug(String arg0) 
	{
		Log.log(Level.debug, arg0, nombreLogger);
	}

	@Override
	public void debug(String arg0, Object arg1) 
	{
		Log.log(Level.debug, arg0+","+arg1.toString(), nombreLogger);	
	}

	@Override
	public void debug(String arg0, Object... arg1) 
	{
		Log.log(Level.debug, objArrayToString(arg1), nombreLogger);	
	}

	@Override
	public void debug(String arg0, Throwable arg1)
	{
		Log.log(Level.debug, arg1,nombreLogger);
	}

	@Override
	public void debug(Marker arg0, String arg1) 
	{
		Log.log(Level.debug, arg0.getName()+":"+arg1, nombreLogger);		
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) 
	{
		debug(arg0, new Object[]{arg1,arg2});		
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) 
	{
		Log.log(Level.debug, arg0.getName()+":"+arg1+":"+arg2, nombreLogger);	
	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2)
	{
		Log.log(Level.debug, arg0.getName()+":"+arg1+":"+objArrayToString(arg2)
				, nombreLogger);			
	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) 
	{
		Log.log(Level.debug, arg0.getName()+":"+arg1, arg2, nombreLogger);		
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3)
	{
		debug(arg0, arg1, new Object[]{arg1,arg2});	
	}

	@Override
	public void error(String arg0) 
	{
		Log.log(Level.error, arg0, nombreLogger);
	}

	@Override
	public void error(String arg0, Object arg1) 
	{
		Log.log(Level.error, arg0+","+arg1.toString(), nombreLogger);	
	}

	@Override
	public void error(String arg0, Object... arg1)
	{
		Log.log(Level.error, objArrayToString(arg1), nombreLogger);		
	}

	@Override
	public void error(String arg0, Throwable arg1) 
	{
		Log.log(Level.error, arg1,nombreLogger);		
	}

	@Override
	public void error(Marker arg0, String arg1)
	{
		Log.log(Level.error, arg0.getName()+":"+arg1, nombreLogger);
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) 
	{
		error(arg0, new Object[]{arg1,arg2});		
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2)
	{
		Log.log(Level.error, arg0.getName()+":"+arg1+":"+arg2, nombreLogger);	
	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) 
	{
		Log.log(Level.error, arg0.getName()+":"+arg1+":"+objArrayToString(arg2)
				, nombreLogger);
	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		Log.log(Level.error, arg0.getName()+":"+arg1, arg2, nombreLogger);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) 
	{
		error(arg0, arg1, new Object[]{arg1,arg2});			
	}

	@Override
	public String getName() 
	{
		return nombreLogger;
	}

	@Override
	public void info(String arg0) 
	{
		Log.log(Level.info, arg0, nombreLogger);
	}

	@Override
	public void info(String arg0, Object arg1)
	{
		Log.log(Level.info, arg0+","+arg1.toString(), nombreLogger);	
	}

	@Override
	public void info(String arg0, Object... arg1) 
	{
		Log.log(Level.info, objArrayToString(arg1), nombreLogger);		
	}

	@Override
	public void info(String arg0, Throwable arg1)
	{
		Log.log(Level.info, arg1,nombreLogger);		
	}

	@Override
	public void info(Marker arg0, String arg1)
	{
		Log.log(Level.info, arg0.getName()+":"+arg1, nombreLogger);
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) 
	{
		info(arg0, new Object[]{arg1,arg2});
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) 
	{
		Log.log(Level.info, arg0.getName()+":"+arg1+":"+arg2, nombreLogger);			
	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) 
	{
		Log.log(Level.info, arg0.getName()+":"+arg1+":"+objArrayToString(arg2)
				, nombreLogger);
	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		Log.log(Level.info, arg0.getName()+":"+arg1, arg2, nombreLogger);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3)
	{
		info(arg0, arg1, new Object[]{arg1,arg2});			
	}

	@Override
	public boolean isDebugEnabled() 
	{
		return (Log.getNivelLogger(nombreLogger).compareTo(Level.debug) <= 0);
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) 
	{
		return isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled()
	{
		return (Log.getNivelLogger(nombreLogger).compareTo(Level.error) <= 0);
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) 
	{
		return isErrorEnabled();
	}

	@Override
	public boolean isInfoEnabled() 
	{
		return (Log.getNivelLogger(nombreLogger).compareTo(Level.info) <= 0);
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) 
	{
		return isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() 
	{
		return (Log.getNivelLogger(nombreLogger).compareTo(Level.trace) <= 0);
	}

	@Override
	public boolean isTraceEnabled(Marker arg0)
	{
		return isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled()
	{
		return (Log.getNivelLogger(nombreLogger).compareTo(Level.warning) <= 0);
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) 
	{
		return isWarnEnabled();
	}

	@Override
	public void trace(String arg0)
	{
		Log.log(Level.trace, arg0, nombreLogger);
	}

	@Override
	public void trace(String arg0, Object arg1)
	{
		Log.log(Level.trace, arg0+","+arg1.toString(), nombreLogger);		
	}

	@Override
	public void trace(String arg0, Object... arg1) 
	{
		Log.log(Level.trace, objArrayToString(arg1), nombreLogger);
	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Marker arg0, String arg1)
	{
		Log.log(Level.trace, arg0.getName()+":"+arg1, nombreLogger);
	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2)
	{
		trace(arg0, new Object[]{arg1,arg2});		
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {
		Log.log(Level.trace, arg0.getName()+":"+arg1+":"+arg2, nombreLogger);		
	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2)
	{
		Log.log(Level.trace, arg0.getName()+":"+arg1+":"+objArrayToString(arg2)
				, nombreLogger);
	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) 
	{
		Log.log(Level.trace, arg0.getName()+":"+arg1, arg2, nombreLogger);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3)
	{
		trace(arg0, arg1, new Object[]{arg1,arg2});			
	}

	@Override
	public void warn(String arg0) 
	{
		Log.log(Level.warning, arg0, nombreLogger);
	}

	@Override
	public void warn(String arg0, Object arg1) 
	{
		Log.log(Level.warning, arg0+","+arg1.toString(), nombreLogger);	
	}

	@Override
	public void warn(String arg0, Object... arg1)
	{
		Log.log(Level.warning, objArrayToString(arg1), nombreLogger);
	}

	@Override
	public void warn(String arg0, Throwable arg1)
	{
		Log.log(Level.warning, arg1,nombreLogger);		
	}

	@Override
	public void warn(Marker arg0, String arg1) 
	{
		Log.log(Level.warning, arg0.getName()+":"+arg1, nombreLogger);
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) 
	{
		warn(arg0, new Object[]{arg1,arg2});		
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2)
	{
		Log.log(Level.warning, arg0.getName()+":"+arg1+":"+arg2, nombreLogger);	
	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2)
	{
		Log.log(Level.warning, arg0.getName()+":"+arg1+":"+objArrayToString(arg2)
				, nombreLogger);		
	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2)
	{
		Log.log(Level.warning, arg0.getName()+":"+arg1, arg2, nombreLogger);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3)
	{
		warn(arg0, arg1, new Object[]{arg1,arg2});	
	}
	
}
