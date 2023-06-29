package com.stepdefination;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.StatList_Output_Pojo;
import com.pojo.address.StateList;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class TC2_GetStateIdStep extends BaseClass {

	
	Response response;

	@Given("User add header for StateList")
	public void userAddHeaderForStateList() {

		addHeader("accept", "application/json");
	}

	@When("User send {string} request for StateList endpoint")
	public void userSendRequestForStateListEndpoint(String type) {

		response = requestType(type, Endpoints.STATELIST);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actStatusCode);

	}

	@Then("User verify the StateList response message matches {string} and save StateId")
	public void userVerifyTheStateListResponseMessageMatchesAndSaveStateId(String expStateName) {

		StatList_Output_Pojo statList_Output_Pojo = response.as(StatList_Output_Pojo.class);
		ArrayList<StateList> listStateList = statList_Output_Pojo.getData();

		for (StateList eachStateList : listStateList) {
			String actStateName = eachStateList.getName();

			if (actStateName.equals("Tamil Nadu")) {
				int stateId = eachStateList.getId();
				TC1_LoginStep.globalDatas.setStateIdNum(stateId);
				String state_id = String.valueOf(stateId);
				System.out.println("sssssssss" + state_id);
				TC1_LoginStep.globalDatas.setState_id(state_id);
				Assert.assertEquals("Verify", expStateName, actStateName);
				System.out.println("statsss===="+actStateName);
				break;

			}

		}

	}

}
