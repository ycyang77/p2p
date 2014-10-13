package com.p2p.global;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logger 
{
	protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
	
	public void error(String msg)
	{
		logger.error(msg);
	}

	public void error(Exception ex)
	{
		logger.error("",ex);
	}

	public void info(String msg)
	{
		logger.info(msg);
	}

	public void debug(String msg)
	{
		logger.debug(msg);
	}

}
