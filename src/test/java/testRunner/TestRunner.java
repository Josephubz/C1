package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = {".//Features/"},
                glue = "stepDefinitions",
               //dryRun =true,
               // monochrome = true,
                plugin = {"pretty",
                        "html:test-output.html"},
                tags = "@sanity"

)

public class TestRunner {

}
