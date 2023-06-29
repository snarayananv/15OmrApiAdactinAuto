package com.stepdefination;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.address.AddressPayload;
import com.pojo.address.CityList;
import com.pojo.address.CityList_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_GetCityIdStep extends BaseClass {

	Response response;

	@Given("User add headers for CityList")
	public void userAddHeadersForCityList() {

		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for CityList state_id")
	public void userAddRequestBodyForCityListStateId() {

		addBodyObject(AddressPayload.getCityPayload(TC1_LoginStep.globalDatas.getState_id()));

	}

	@When("User send {string} request for CityList endpoint")
	public void userSendRequestForCityListEndpoint(String type) {
		response = requestType(type, Endpoints.CITYLIST);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the CityList response message matches {string} and save CityId")
	public void userVerifyTheCityListResponseMessageMatchesAndSaveCityId(String expCityName) {

		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> cityList = cityList_Output_Pojo.getData();
		for (CityList eachCityList : cityList) {

			String actCityName = eachCityList.getName();
			if (actCityName.equals("Yercaud")) {
				int cityId = eachCityList.getId();
				TC1_LoginStep.globalDatas.setCityId(cityId);

				System.out.println(cityId);
				Assert.assertEquals("Verified", expCityName, actCityName);
				break;
			}
		}

	}


}
