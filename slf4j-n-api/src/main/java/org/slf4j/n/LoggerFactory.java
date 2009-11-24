package org.slf4j.n;

import org.slf4j.n.helper.LoggerWrapper;

public class LoggerFactory
{
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
		return new LoggerWrapper(loggerName);
	}
}
