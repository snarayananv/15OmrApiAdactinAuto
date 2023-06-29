package com.stepdefination;

import org.junit.Assert;

import io.cucumber.java.en.Then;

public class CommonStep {

	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(int expStatusCode) {

		Assert.assertEquals("Verify the StatusCode", expStatusCode, TC1_LoginStep.globalDatas.getStatusCode());

	}

}
