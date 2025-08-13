package Cucumber.StepDefinitionPackage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class StepDef1 {
    @Given("user clicks on the login link")
    public void user_clicks_on_the_login_link() {
        System.out.println("Clicked on Login Button");
    }
    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password(DataTable datatable) {
        List<Map<String, String>> loginCredentials = datatable.asMaps(String.class, String.class);
        for(Map<String, String> user : loginCredentials){
            String userName = user.get("username");
            String password = user.get("password");
            System.out.println("Entered "+userName+" and " +password);
        }

    }
    @Then("user logs into the application")
    public void user_logs_into_the_application() {
        System.out.println("Successfully logged in");
    }
}
