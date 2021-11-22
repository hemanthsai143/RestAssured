package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {
	
	public static RequestSpecification req;
	ResponseSpecification responseSpecification;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream stream=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalproperty("baseUrl")).addFilter(RequestLoggingFilter.logRequestTo(stream))
				 .addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).addQueryParam("key","qaclick123").build();
		return req;
	}
		else
		{
			return req;
		}
	}

	 public static String getGlobalproperty(String Key) throws IOException
	 {
		 Properties prop=new Properties();
		 FileInputStream file=new FileInputStream("E:\\APITESTINGWorkspace\\RestAssured\\src\\test\\java\\com\\utilities\\global.properties");
		 prop.load(file);
		 String value=prop.getProperty(Key);
		 
		 return value;
		 
		 
	 }
	 
	 public String getJsonpath(Response res,String Key)
	 {
		 
		 String resp=res.asString();
			JsonPath js=new JsonPath(resp);
		   return js.get(Key).toString();
		
	 }
	
	
}
