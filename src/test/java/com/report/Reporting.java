package com.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {
	
	public static void generateJvmReport(String jsonFile) {

		File file = new File(getProjectValue() + "\\target");

		Configuration configuration = new Configuration(file, "Adactin Automation");

		List<String> jsonfiles = new ArrayList<String>();
		jsonfiles.add(jsonFile);

		ReportBuilder builder = new ReportBuilder(jsonfiles, configuration);

		builder.generateReports();

	}


}
