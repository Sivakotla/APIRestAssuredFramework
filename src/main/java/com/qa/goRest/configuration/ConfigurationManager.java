package com.qa.goRest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.goRest.frameWorkException.APIFrameWorkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties intiProp() {

		prop = new Properties();
		// maven : comndline argument
		// mvn clean install -Denv = "qa"

		String envName = System.getProperty("env");
		System.out.println("Running tests on : " + envName);
		
		
		try {
		if (envName == null) {
			System.out.println("no env is given.. hence running tests on QA env... ");
			ip = new FileInputStream(".src/test/resources/config/qa.config.properties");
		} else {
			System.out.println("Running tests on: " + envName);
			
				switch (envName.toLowerCase()) {

				case "qa":
					ip = new FileInputStream(".src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(".src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream(".src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right env name..." + envName);
					throw new APIFrameWorkException("NO ENV IS GIVEN");
					
				}

			} 
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

//		prop = new Properties();
//		try {
//			ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return prop;
	}
}
