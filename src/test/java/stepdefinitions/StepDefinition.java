package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.serilization.pojo.AddAPI;
import com.serilization.pojo.Location;
import com.utilities.APIResources;
import com.utilities.TestDataBuild;
import com.utilities.Utilities;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;

public class StepDefinition extends Utilities {
	RequestSpecification req;
	ResponseSpecification responseSpecification;
	RequestSpecification specreq;
	Response response;
	static String place_id;
	TestDataBuild testData=new TestDataBuild();
	
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with(String name, String language, String address) throws IOException {
		  specreq=given().spec(requestSpecification()).body(testData.addPlacebody(name,language,address));
	   
	}
	
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourceName, String method) {
		APIResources resourceAPI=APIResources.valueOf(resourceName);
		if(method.equalsIgnoreCase("post"))
		{
	  response=specreq. when().post(resourceAPI.getResource());
	   
	}
	else if(method.equalsIgnoreCase("get"))
	{
		  response=specreq.when().get(resourceAPI.getResource());
		   
		}
}


	

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		//Assert.assertEquals(, actual);
		// responseSpecification=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
   
    assertEquals(response.getStatusCode(),200);
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(),expectedValue);
	   
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String responseValidation, String resourceName) throws IOException {
		
		place_id=getJsonpath(response, "place_id");
		specreq=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resourceName,"get");
		assertTrue(getJsonpath(response,"name").equalsIgnoreCase(responseValidation));
		
		 
	}

	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	   
		specreq=given().spec(requestSpecification()).body(testData.deleteapibody(place_id));
		
	  }


}
