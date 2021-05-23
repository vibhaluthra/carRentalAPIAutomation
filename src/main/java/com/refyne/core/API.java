package com.refyne.core;

import java.util.HashMap;
import java.util.Map;

import javax.management.DescriptorKey;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API {
	
	static {
		RestAssured.baseURI="https://refyne-car-rental.herokuapp.com/api";
	}
	private RequestSpecification requestSpec;
	protected Map<String, String> queryParamters = new HashMap<String, String>();
	protected Map<String, String> headers = new HashMap<String, String>();
	protected String body;
	protected String basePath;
	protected Response response;
	protected MethodType methodType;
	
	protected enum MethodType {
		
		GET,POST,PUT,DELETE;
	}
	private void build() {
		
		requestSpec = new RequestSpecBuilder()
				.setBasePath(basePath)
				.setContentType("application/json")
				.addHeaders(headers)
				.addParams(queryParamters)
				.setBody(body)
				.build();
		
		
	}
	
	public Response execute()
	{
		build();
		RequestSpecification requestSpecification = RestAssured.given()
				.spec(requestSpec)
				.log().all()
				.when();
		
		switch(methodType) {
		
		case GET :
			response = requestSpecification.get();
			break;
		case PUT :
			response = requestSpecification.put();
			break;
		default:
			response = requestSpecification.post();
			break;
		
		}
		
		System.out.println("Response : "+response.prettyPrint());
		return response;
	}
			
	
	

}
