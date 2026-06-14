package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class configManager {
// write a program to read the properties file from src/test/resources/config/config.properties
	
	private static Properties prop = new Properties();
	private static String path ="config/config.properties";
	private static String env;
	
	private configManager() {
		//Private constructor
	}
	
	static{
		//Operation of loading the property file in the memory!
		//Because it is a static block it will be executed once during Class loading time	
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		System.out.println("Running Tests in Env"+env);
		
		switch(env) {
		case "dev"-> path = "config/config.dev.properties";
		
		case "qa" -> path = "config/config.qa.properties";
		
		case "uat"-> path = "config/config.uat.properties";
			
		default -> path= "config/config.qa.properties";
		}
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/config.properties");
		
		if(input==null) {
			throw new RuntimeException("Cannot find the file at the Path " + path);
		}
		
		try {
			prop.load(input);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
