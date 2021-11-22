package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class CucumberHooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinition m=new StepDefinition();
		if(m.place_id==null)
		{
		m.add_Place_payload_with("hemanth","telugu","Karnataka");
		m.user_calls_with_http_request("addPlaceAPI","POST");
		m.verify_place_Id_created_maps_to_using("hemanth","getPlaceAPI");
		}
	}

}
