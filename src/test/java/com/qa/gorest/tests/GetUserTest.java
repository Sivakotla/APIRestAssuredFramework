package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.geRest.baseTest.BaseTest;
import com.qa.goRest.client.RestClient;
import com.qa.goRest.constants.APIHTTPStatus;



public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup() {
		rc = new RestClient(prop, baseURI);
	}
	
	@Test(enabled = false, priority=3, description = "This test is in progress...")
	public void getAllUsersTest() {
		
		
		 rc.get(GOREST_ENDPOINT,false, true)
								.then().log().all()
								.statusCode(APIHTTPStatus.OK_200.getCode())
								.assertThat()
								.body("$", hasSize(10));
								
		System.out.println("Total ten users available");
	}
	
	@Test(priority=2)
	public void getUserTest() {
		
		
		rc.get(GOREST_ENDPOINT+"/8626788",true, true)
			.then().log().all()
			.statusCode(APIHTTPStatus.OK_200.getCode())
			.assertThat()
			.body("id", equalTo(8626788));
			
	}
	
	@Test(priority=1)
	public void getUserWithQueryParamTest(){
		
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("name", "naveen");
		queryParams.put("status", "active");
		
				rc.get(GOREST_ENDPOINT+"/", queryParams, null,false, true)
					.then().log().all()
					.assertThat()
					.statusCode(APIHTTPStatus.OK_200.getCode());
	}

}
