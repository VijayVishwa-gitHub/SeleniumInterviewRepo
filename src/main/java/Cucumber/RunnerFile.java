package Cucumber;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/Cucumber/FeatureFiles/Feature.feature",
        glue     = "Cucumber.StepDefinitionPackage",
        tags     = "@Regression",
        dryRun   = false,
        plugin   = {"pretty", "json:target/cucumber-reports.json", "html:target/cucumber-reports.html"}

)
public class RunnerFile {


}
