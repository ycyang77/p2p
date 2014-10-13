package com.p2p.global;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoggingFilter extends OncePerRequestFilter 
{
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String REQUEST_PREFIX = "Request: ";
    private static final String RESPONSE_PREFIX = "Response: ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, final FilterChain filterChain) 
    		throws ServletException, IOException 
    {
        String requestinfo = getRequestInfo(request);
        
        try 
        {
            logRequest(requestinfo);
            filterChain.doFilter(request, response);
        }
        finally 
        {
            logResponse(requestinfo, getResponseInfo(response));
        }
    }
    
    private String getRequestInfo(final HttpServletRequest request) 
    {
        StringBuilder msg = new StringBuilder();
        msg.append(REQUEST_PREFIX);
        HttpSession session = request.getSession(false);
        if (session != null) 
        {
            msg.append("session id=").append(session.getId()).append("; ");
        }
        if(request.getContentType() != null) 
        {
            msg.append("content type=").append(request.getContentType()).append("; ");
        }
        msg.append("uri=").append(request.getRequestURI());
        if(request.getQueryString() != null) 
        {
            msg.append('?').append(request.getQueryString());
        }

        return msg.toString();
    }
    
    private void logRequest(String reqestInfo)
    {
    	logger.info(reqestInfo);    
    }

    private void logResponse(String reqestInfo, String responseInfo)
    {
    	logger.info(reqestInfo + "; " + responseInfo);
    }
    
    private String getResponseInfo(final HttpServletResponse response) 
    {
        StringBuilder msg = new StringBuilder();
        msg.append(RESPONSE_PREFIX);
        msg.append("status=").append(response.getStatus());

        return msg.toString();
    }

}
