package com.p2p.global;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter 
{

    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(new ExceptionInterceptor());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    	converters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}