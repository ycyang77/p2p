package com.p2p.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExceptionInterceptor extends HandlerInterceptorAdapter 
{
	protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);
	
    @Override
    public void afterCompletion(HttpServletRequest request,
            					HttpServletResponse response, 
            					Object handler, 
            					Exception ex) throws Exception 
    {
        if (ex != null) 
        {
        	logger.error("", ex);
        }
    }
}