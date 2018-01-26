package com.inthub.eventmonitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class MonitoringEventApplication {

	private static final String CONTEXT_SELECTOR_KEY 	= "Log4jContextSelector";
	private static final String CONTEXT_SELECTOR_VALUE 	= "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector";

	static {
		System.setProperty(CONTEXT_SELECTOR_KEY,CONTEXT_SELECTOR_VALUE);
	}
	private static final Logger logger = LogManager.getLogger(MonitoringEventApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MonitoringEventApplication.class, args);
	}
}
