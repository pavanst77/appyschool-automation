package com.appyschool.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * API Service Automation Test suite setup properties/Configurations loader
 * 
 * @author Hrishi
 *
 */
public class SuiteSetupProperties {
    private static final Logger LOG = LogManager.getLogger(SuiteSetupProperties.class);
    public static Properties config = loadProperties();
	
	private SuiteSetupProperties() {}
	
	private static Properties loadProperties() {
		if (config == null) {
			config = new Properties();
			try {
				config.load(new FileInputStream("./suiteSetup.properties"));
			} catch (IOException ioEx) {
				LOG.error("========> IOException occurred while loading the properties");
			}
		}
		return config;
	}

	/**
	 * Get the value of type String from the config properties
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return config.getProperty(key);
	}
	
	/**
	 * Get the value of type Integer from the config properties
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
        int value = 0;
        try {
            value = Integer.parseInt(config.getProperty(key));
        } catch (NumberFormatException nfe) {
            LOG.error("========> Error occurred while reading/parsing the key" + nfe);
        }
        return value;
    }
	
	/**
	 * Write to properties file
	 * 
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, Object value){
		config.setProperty(key, String.valueOf(value));
	}
}
