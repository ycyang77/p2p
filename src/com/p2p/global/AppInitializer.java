package com.p2p.global;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.hdiv.filter.ValidatorFilter;
import org.hdiv.listener.InitListener;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;

public class AppInitializer implements WebApplicationInitializer 
{

    @Override
    public void onStartup(ServletContext container) 
    {
    	System.setProperty("file.encoding", "utf-8");
    	
        SLF4JBridgeHandler.removeHandlersForRootLogger(); 
        SLF4JBridgeHandler.install();
        
    	container.setInitParameter("logbackConfigLocation", "/WEB-INF/logback.xml");
        container.setInitParameter("contextConfigLocation", "/WEB-INF/dispatcher-servlet.xml, /WEB-INF/hdiv.xml");

        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        
        container.getServletRegistration("default").addMapping("*.html", "*.css", "*.js", "*.ico", "*.png", "*.jpg", "*.svg", "*.gif");
        
        container.addListener(new LogbackConfigListener());
        container.addListener(new ContextLoaderListener());
        container.addListener(new InitListener());

        container.addFilter("loggingFilter", new LoggingFilter()).addMappingForUrlPatterns(null, true, "/*");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        container.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, true, "/*");
        
        container.addFilter("hdivValidatorFilter", new ValidatorFilter()).addMappingForServletNames(null, true, "dispatcher");
        
        container.addFilter("sitemesh", new ConfigurableSiteMeshFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
}