package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	RequestSpecification reqSpec;
	Response response;

	public void addHeader(String key, String value) {

		reqSpec = RestAssured.given().header(key, value);

	}

	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);

	}

	public void addBasicAuth(String username, String password) {

		reqSpec = reqSpec.auth().preemptive().basic(username, password);
	}

	public void addPathParam(String key, String value) {

		reqSpec = reqSpec.pathParam(key, value);

	}

	public void addQueryParam(String key, String value) {

		reqSpec = reqSpec.queryParam(key, value);

	}

	public void addBody(String body) {

		reqSpec = reqSpec.body(body);

	}

	public void addBodyObject(Object body) {

		reqSpec = reqSpec.body(body);
		

	}

	public Response requestType(String type, String endpoint) {

		switch (type) {
		case "GET":

			response = reqSpec.log().all().get(endpoint);

			break;

		case "POST":

			response = reqSpec.log().all().post(endpoint);

			break;

		case "PUT":

			response = reqSpec.log().all().put(endpoint);

			break;

		case "DELETE":

			response = reqSpec.log().all().delete(endpoint);

			break;

		default:
			break;
		}

		return response;

	}

	public int getStatusCode(Response response) {

		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public String getResBodyAsString(Response response) {

		String asString = response.asString();
		return asString;

	}

	public String getResBodyPrettyString(Response response) {

		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}

	public static String getProjectValue() {

		String property = System.getProperty("user.dir");
		return property;

	}

	public static String getPropertieFileValue(String key) throws FileNotFoundException, IOException {

		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectValue() + "\\Confiq\\confiq.properties"));
		Object object = properties.get(key);
		String string = (String) object;
		return (String) properties.get(key);

	}

}
