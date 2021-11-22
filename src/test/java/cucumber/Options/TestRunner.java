package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/featurefiles",
                 glue= {"stepdefinitions"},
                 tags= {"@DeletePlace"}
               
                
                 )
public class TestRunner {

	
}
