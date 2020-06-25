package com.jpetstore.order.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

	@Bean
	public RestTemplate getCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(5000);
		httpRequestFactory.setReadTimeout(5000);
		return new RestTemplate(httpRequestFactory);
	}
}