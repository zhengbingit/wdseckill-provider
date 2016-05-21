package com.wd.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	private String str; 
	public PropertiesLoader(String pro) {
		this.str = pro;
	}
	public String getProperty(String str) throws IOException {
		Properties properties = new Properties();
		InputStream in = Object.class.getResourceAsStream("str");
		properties.load(in);
		System.out.println("ip"+properties.getProperty(str));
		return properties.getProperty(str);
	}
}
