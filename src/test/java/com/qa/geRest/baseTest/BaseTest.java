package com.qa.geRest.baseTest;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.goRest.client.RestClient;
import com.qa.goRest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	
	//Service urls
	
	public static final String GOREST_ENDPOINT = "/public/v2/users";
	public static final String REQRES_ENDPOINT = "/api/collections/products/records";
	public static final String CIRCUIT_ENDPOINT = "/api";
	public static final String AMADEUSTOKEN_ENDPOINT = "/v1/security/oauth2/token";
	public static final String AMADEUS_ENDPOINT = "/v1/shopping/flight-destinations";
	
	
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient rc;
	protected String baseURI;
	
	
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.intiProp();
		this.baseURI = baseURI;
		//String baseURI = prop.getProperty("baseURI");
		rc = new RestClient(prop, baseURI);
	}

}
