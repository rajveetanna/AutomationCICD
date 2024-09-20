package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Cucumber depends on TestNg or JUnit to run 

@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinitions", tags= "@Regression",
monochrome=true,plugin={"html:target/cucumber.html"})
// monochrome gives the results in readable format

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
}