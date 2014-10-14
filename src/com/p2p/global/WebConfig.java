package com.p2p.global;

import java.nio.charset.Charset;
import java.util.List;

import org.hdiv.web.multipart.HdivCommonsMultipartResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter 
{
	@Autowired
	@Qualifier("hdivEditableValidator")
	private Validator hdivEditableValidator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(new ExceptionInterceptor());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) 
    {
    	converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    	converters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
    
    @Override
    public Validator getValidator()
    {
    	return hdivEditableValidator;
    }
    
	@Bean
	public MultipartResolver multipartResolver() {
		HdivCommonsMultipartResolver resolver = new HdivCommonsMultipartResolver();
		resolver.setMaxUploadSize(100000);
		return resolver;
	}
}