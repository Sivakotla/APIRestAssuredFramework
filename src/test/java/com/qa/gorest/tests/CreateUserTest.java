package com.qa.gorest.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.geRest.baseTest.BaseTest;
import com.qa.goRest.POJO.User;
import com.qa.goRest.client.RestClient;
import com.qa.goRest.constants.APIConstants;
import com.qa.goRest.constants.APIHTTPStatus;
import com.qa.goRest.utils.ExcelUtil;
import com.qa.goRest.utils.StringUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserTest extends BaseTest {
	
	APIConstants ap = new APIConstants();
	
	@BeforeMethod
	public void createUserSetUp() {
		rc = new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][]{
				{"Subhodh", "male", "active"},
				{"Seema", "female", "inactive"},
				{"Madhuri", "female", "active"}
		};
	}
	
	@DataProvider
	public Object[][] getUserTestSheetData() {
		return ExcelUtil.getTestData(APIConstants.GOREST_USER_SHEET_NAME);
	}
	
	@Test (dataProvider = "getUserTestData")
	public void createSingleUserTest(String name, String gender, String status) {
		RestClient rc = new RestClient(prop, baseURI);
		User use = new User(name, StringUtils.randomEmail() ,gender, status);
		 
		Integer id  = rc.post(GOREST_ENDPOINT, "json", use,true, true)
							.then().log().all()
							.assertThat()
							.statusCode(APIHTTPStatus.CREATED_201.getCode())
							.extract()
							.path("id");
		
		System.out.println("created Id is  :" + id);
		
		//.verify whether the user created or not with Get Call(with current id).
		
		rc.get(GOREST_ENDPOINT+"/"+id,true, true)
			.then().log().all()
			.assertThat()
			.statusCode(APIHTTPStatus.OK_200.getCode());
			
	}
	
	@Test 
	public void createAPISchemaTest() {
		RestClient rc = new RestClient(prop, baseURI);
		User use = new User("Jake", StringUtils.randomEmail() ,"male", "active");
		 
		rc.post(GOREST_ENDPOINT, "json", use,true, true)
							.then().log().all()
							.assertThat()
							.statusCode(APIHTTPStatus.CREATED_201.getCode())
							.body(matchesJsonSchemaInClasspath("createuserschema.json"));
		
		//System.out.println("created Id is:" + id);
		System.out.println("End Test");
	}

}
