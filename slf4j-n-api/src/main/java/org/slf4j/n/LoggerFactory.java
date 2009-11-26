package org.slf4j.n;

import org.slf4j.n.helpers.NewLoggerWrappingOld;

public class LoggerFactory
{
  /*
  This class must handle the following use-cases:
  1.) The binding is an slf4j-binding.
      There is no implementation of n.ILoggerFactory available
      In this case, this class must return a wrapped slf4j.Logger
  2.) The binding is an slf4j-n-binding.
      In this case, this class returns a native implementation of an slf4j.n.Logger.
      org.slf4j.impl.StaticLoggerBinder.getLoggerFactory() would return an implementation that
      would call org.slf4j.n.LoggerFactory.getLogger("name").getClassicLogger();

   */
	public static Logger getLogger(Class clazz)
	{
		return getLogger(clazz.getName());
	}
	
	public static Logger getLogger(String loggerName)
	{
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(loggerName);
		if(logger instanceof Logger)
		{
			return (Logger) logger;
		}
		return new NewLoggerWrappingOld(logger);
    // TODO this is not sufficient.
	}
}
