package com.stepdefination;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.address.AddressPayload;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Output_Pojo;
import com.pojo.address.GetUserAddres_Output_Pojo;
import com.pojo.address.UpdateUserAddress_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {

	Response response;

	@Given("User add headers and bearer authorization for accessing Address endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingAddressEndpoints() {

		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

	}

	@When("User add request body for new address {string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForNewAddressAnd(String first_name, String last_name, String mobile, String apartment,
			String country, String zipcode, String address, String address_type) {
		int parseInt = Integer.parseInt(country);

		addBodyObject(AddressPayload.getAddAddressPayload(first_name, last_name, mobile, apartment,
				TC1_LoginStep.globalDatas.getStateIdNum(), TC1_LoginStep.globalDatas.getCityId(), parseInt, zipcode,
				address, address_type));
	}

	@When("User send {string} request for add User Address endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.ADDADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actStatusCode);

	}

	@Then("User verify the add User Address response message matches {string}")
	public void userVerifyTheAddUserAddressResponseMessageMatches(String expAddressMsg) {

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actMessage = addUserAddress_Output_Pojo.getMessage();

		int addressIdNum = addUserAddress_Output_Pojo.getAddress_id();
		String address_id = String.valueOf(addressIdNum);
		TC1_LoginStep.globalDatas.setAddress_id(address_id);

		Assert.assertEquals("Verify the Address added successfully", expAddressMsg, actMessage);

	}

	@Given("User add headers and bearer authorization for accessing Update Address endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingUpdateAddressEndpoints() {

		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

	}

	@When("User add request body for Update address {string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userAddRequestBodyForUpdateAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String country, String zipcode, String address, String address_type) {

		int parseInt = Integer.parseInt(country);

		addBodyObject(AddressPayload.getUpdateAddress(TC1_LoginStep.globalDatas.getAddress_id(), first_name, last_name,
				mobile, apartment, TC1_LoginStep.globalDatas.getStateIdNum(), TC1_LoginStep.globalDatas.getCityId(),
				parseInt, zipcode, address, address_type));

	}

	@When("User send {string} request for Update User Address endpoint")
	public void userSendRequestForUpdateUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.UPDATEADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the Update User Address response message matches {string}")
	public void userVerifyTheUpdateUserAddressResponseMessageMatches(String expUpdateAddressMsg) {

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMessage = updateUserAddress_Output_Pojo.getMessage();

		Assert.assertEquals("Verify the Address added successfully", expUpdateAddressMsg, actMessage);

	}

	@Given("User add headers and bearer authorization for accessing Get User Address endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingGetUserAddressEndpoints() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());

		listHeaders.add(h1);
		listHeaders.add(h2);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

	}

	@When("User send {string} request for User Address endpoint")
	public void userSendRequestForUserAddressEndpoint(String type) {

		response = requestType(type, Endpoints.GETADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(actStatusCode);
	}

	@Then("User verify the Get User Address response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expUserAddressMessage) {

		GetUserAddres_Output_Pojo getUserAddres_Output_Pojo = response.as(GetUserAddres_Output_Pojo.class);
		String actMessage = getUserAddres_Output_Pojo.getMessage();
		Assert.assertEquals("Verifyied", expUserAddressMessage, actMessage);
	}

	@Given("User add headers and bearer authorization for accessing Get User Delete endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingGetUserDeleteEndpoints() {

		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for User Delete Address")
	public void userAddRequestBodyForUserDeleteAddress() {

		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(
				TC1_LoginStep.globalDatas.getAddress_id());
		addBodyObject(deleteUserAddress_Input_Pojo);

	}

	@When("User send {string} request for Get User Delete endpoint")
	public void userSendRequestForGetUserDeleteEndpoint(String type) {

		response = requestType(type, Endpoints.DELETEADDRESS);
	}

	@Then("User verify the Get User delete Address response message matches {string}")
	public void userVerifyTheGetUserDeleteAddressResponseMessageMatches(String expDeleteMsg) {

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMessage = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Address deleted successfully", expDeleteMsg, actMessage);

	}

}
