package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.base.BaseClass;
import com.report.Reporting;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(tags = ("@Login"), monochrome = false, snippets = SnippetType.CAMELCASE, dryRun = false, publish = false, plugin = {
		"pretty",
		"json:target\\output.json" }, features = "src\\test\\resources\\Features", glue = "com.stepdefination")

public class TestRunnerClass extends BaseClass {

	@AfterClass

	public static void afterClass() throws FileNotFoundException, IOException {

		Reporting.generateJvmReport(getProjectValue() + getPropertieFileValue("jsonPath"));

	}

}
