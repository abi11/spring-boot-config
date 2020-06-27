package com.abhi.springbootconfig;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class GreetingController {
	
	Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private DbSettings dbSettings;
	@Value("${my.greeting: default value}")
	private String greetingMessage;
	
	@Value("Some static message")
	private String someStaticMessage;
	
	@Value("${mylist.values}")
	private List<String> listValues;
	
	@Value("#{${dbValues}}")
	private Map<String,String> dbValues;
	public GreetingController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public   Environment env;

	@GetMapping("/greeting")
	public String greeting() {
		logger.debug("In Greeting");
		return greetingMessage + someStaticMessage + listValues+dbValues +dbSettings.getPort()+dbSettings.getHost();
	}
	
	@GetMapping("/envDetails")
	public String envDetails() {
		return env.toString() + env.getProperty("connection");
	}
}
