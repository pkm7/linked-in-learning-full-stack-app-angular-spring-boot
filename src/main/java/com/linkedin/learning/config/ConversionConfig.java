package com.linkedin.learning.config;

import java.util.Set;
import java.util.HashSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;


@Configuration
public class ConversionConfig {

	private Set<Converter> getConverters(){
		Set<Converter> converters = new HashSet<>();
		
		return converters;
	}
	
	private ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		
		return bean.getObject();
	}
}
