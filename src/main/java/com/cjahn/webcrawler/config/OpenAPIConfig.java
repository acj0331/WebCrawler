package com.cjahn.webcrawler.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties()
public class OpenAPIConfig {
	private Map<String, Object> openapis;

	public Map<String, Object> getOpenapis() {
		return openapis;
	}

	public void setOpenapis(Map<String, Object> openapis) {
		this.openapis = openapis;
	}
	
}
