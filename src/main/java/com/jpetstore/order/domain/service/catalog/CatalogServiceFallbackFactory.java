package com.jpetstore.order.domain.service.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class CatalogServiceFallbackFactory implements FallbackFactory<CatalogService> {
	
	Logger logger = LoggerFactory.getLogger(CatalogServiceFallbackFactory.class); 
	
	private final CatalogServiceFallback catalogServiceFallback;
	
	public CatalogServiceFallbackFactory() {
		this.catalogServiceFallback = new CatalogServiceFallback();
	}

	@Override
	public CatalogService create(Throwable cause) {
		logger.error(cause.getMessage(), cause);
		return catalogServiceFallback;
	}

}
