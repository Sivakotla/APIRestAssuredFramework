package com.qa.gorest.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.geRest.baseTest.BaseTest;
import com.qa.goRest.client.RestClient;
import com.qa.goRest.constants.APIHTTPStatus;
import com.qa.goRest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends BaseTest {
	
	@BeforeMethod
	public void circuitSetUp() {
		rc= new RestClient(prop, baseURI);
	}
	
	@Test
	public void getCircuitTest() {
		
		
		 Response circuitResponse = rc.get(CIRCUIT_ENDPOINT+"/2017/circuits",false, true);
		 
		 int statusCode = circuitResponse.statusCode();
		 
		 Assert.assertEquals(statusCode, APIHTTPStatus.OK_200.getCode());
		 
		 JsonPathValidator js = new JsonPathValidator();
		 
		 ArrayList<String> country = js.read(circuitResponse, "$..circuits..country");
		 
		 System.out.println("Total Countries: " + country);
		 
		 Assert.assertTrue(country.contains("Australia"));
		 
//								.then().log().all()
//								.statusCode(APIHTTPStatus.OK_200.getCode());
								
//		System.out.println("Total ten users available");
	}

}
