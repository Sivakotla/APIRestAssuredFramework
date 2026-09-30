package com.qa.gorest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.geRest.baseTest.BaseTest;
import com.qa.goRest.client.RestClient;
import com.qa.goRest.constants.APIHTTPStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest {
	
	private String accessToken;
	
	@Parameters({"baseURI", "grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightAPISetup(String baseURI, String grantType, String clientId, String clientSecret) {
		rc = new RestClient(prop, baseURI);
		accessToken = rc.getAccessToken(AMADEUSTOKEN_ENDPOINT, grantType, clientId, clientSecret);
	}
	
	
	@Test
	public void getFlightInfoTest() {
		
		RestClient rcFlight = new RestClient(prop, baseURI);
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);
		
		Map<String, String> headersMap = new HashMap<>();
		headersMap.put("Authorization", "Bearer" + accessToken);
		
		Response flightDataResponse = rcFlight.get(AMADEUS_ENDPOINT, headersMap, headersMap, false, true)
												.then().log().all()
												.assertThat()
												.statusCode(APIHTTPStatus.OK_200.getCode())
												.and()
												.extract()
												.response();
				
				
												
				
		
//		Response flightDataResponse = given()
//										.header(headersMap)
//										.queryParam(queryParams)
//										.queryParam(queryParams)
//										.when()
//										.get()
//										.then()
//										.assertThat()
//										.statusCode(200)
//										.and()
//										.extract()
//										.response();
		
		
		JsonPath js  = flightDataResponse.jsonPath();
		
		String type = js.get("data[0].type");
		System.out.println(type);
	}

}
