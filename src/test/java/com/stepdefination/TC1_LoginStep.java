package com.stepdefination;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.login.Login_Output_Pojo;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TC1_LoginStep extends BaseClass {

	static GlobalDatas globalDatas = new GlobalDatas();
	Response response;

	@Given("User add header")
	public void userAddHeader() {

		addHeader("accept", "application/json");

	}

	@When("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {

		addBasicAuth(getPropertieFileValue("username"), getPropertieFileValue("password"));

	}

	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String type) {

		response = requestType(type, Endpoints.POSTMANBASICAUTH);
		int actStatusCode = getStatusCode(response);
		globalDatas.setStatusCode(actStatusCode);

	}

	@Then("User verify the login response body firstname present as {string} and get the logtoken saved")
	public void userVerifyTheLoginResponseBodyFirstnamePresentAsAndGetTheLogtokenSaved(String expFirstName) {

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String actFirstname = login_Output_Pojo.getData().getFirst_name();
		Assert.assertEquals("Verify FirstName", expFirstName, actFirstname);
		System.out.println(actFirstname);

		String logtoken = login_Output_Pojo.getData().getLogtoken();
		globalDatas.setLogtoken(logtoken);

	}

}
