package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.geRest.baseTest.BaseTest;

public class ReqResTest extends BaseTest{
	
	
			
			@Test
			public void getCircuitTest() {
				rc.get(REQRES_ENDPOINT+"?project_id=52086",false, true)
										.then().log().all()
										.statusCode(200);
										
				System.out.println("Total three users available");
			}

}
