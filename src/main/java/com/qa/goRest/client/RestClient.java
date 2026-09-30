package com.qa.goRest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;

import com.qa.goRest.constants.APIHTTPStatus;
import com.qa.goRest.frameWorkException.APIFrameWorkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "003ee06db6f48e0ca8e1d5070a6e0903465041f2128333504164ffbafae52f72";
	private static RequestSpecBuilder specBuilder;

//	static {
//		specBuilder = new RequestSpecBuilder();
//	}
	
	private Properties prop;
	private String baseURI;
	
	private boolean isAuthorizationHeaderAdded = false;
	
	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	public void addAuthorizationHeader() {
		if(!isAuthorizationHeaderAdded) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
		isAuthorizationHeaderAdded = true;
		}
	}

	private void setRequestConteType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;

		default:
			System.out.println("please pass the right contentType..");
			throw new APIFrameWorkException("INVALIDCONTENTTYPE");
		}
	}

	private RequestSpecification createRequestSpec(boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
		}
		
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap,boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> queryParams, Map<String, String> headersMap,boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParams != null) {
			specBuilder.addQueryParams(queryParams);
			
		}

		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
		}
		setRequestConteType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap,boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		setRequestConteType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

	// Create httpUtil Methods

	public Response get(String serviceUrl,boolean includeAuth, boolean log) {

		if (log) {

			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().get(serviceUrl);

		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}

	public Response get(String serviceUrl, Map<String, String> headersMap,boolean includeAuth, boolean log) {

		if (log) {

			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all().when().get(serviceUrl);

		}
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> queryParams, Map<String, String> headersMap,boolean includeAuth,boolean log) {

		if (log) {

			return RestAssured.given(createRequestSpec(queryParams,headersMap,includeAuth)).log().all().when().get(serviceUrl);

		}
		return RestAssured.given(createRequestSpec(queryParams,headersMap,includeAuth)).when().get(serviceUrl);
	}
	
	//Post Call
	
	public Response post(String serviceUrl, String contentType, Object requestBody,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
						.when()
						.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when()
		.post(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
						.when()
						.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when()
		.post(serviceUrl);
	}
	
	// put Call
	
	public Response put(String serviceUrl, String contentType, Object requestBody,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
						.when()
						.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when()
		.put(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
						.when()
						.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when()
		.put(serviceUrl);
	}
	
	//patchCall
	
	public Response patch(String serviceUrl, String contentType, Object requestBody,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
						.when()
						.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when()
		.patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
						.when()
						.patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when()
		.patch(serviceUrl);
	}
	
	// Delete Call
	
	public Response patch(String serviceUrl, boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all()
						.when()
						.delete();
		}
		
		return RestAssured.given(createRequestSpec(includeAuth))
		.when()
		.delete();
	}
	
	
	public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret) {
		//postCall = get the access token
		
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		String accessToken =	given()
						//.header("Content-Type", "application/x-www-form-urlencoded")
						.contentType(ContentType.URLENC)
						.formParam("grant_type", grantType) //
						.formParam("client_id", clientId)//
						.formParam("client_secret", clientSecret)//"VjjgfcjilNAZcSJw"
						.when()
						.post(serviceURL) //"/v1/Security/oauth2/token"
						.then()
						.assertThat()
						.statusCode(APIHTTPStatus.OK_200.getCode())
						.extract()
						.path("access_token");
		
		System.out.println("accessToken: " + accessToken);
		
		return accessToken;
						
	}
	
	
	
	
	
}
