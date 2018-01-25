package com.inthub.eventmonitor;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class MonitoringEventApplication {

	public static void main(String[] args) {

		SpringApplication.run(MonitoringEventApplication.class, args);

	}
}
