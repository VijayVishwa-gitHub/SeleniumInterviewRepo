package RestAssuredAutomation;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SampleClass {

    @Test
    public void firstTest() throws IOException {
        int count=testDataHandler.count;
        for (int i = 0; i < count; i++) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        File filePath = new File("./src/main/resources/Payload/demo.json");


            // 1. Get Place_id

            String response = given().queryParam("key", "qaclick123").header("ContentType", "application/json")
                    .body(filePath).when().post("maps/api/place/add/json")
                    .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

            JsonPath js = new JsonPath(response);
            System.out.println("The place_id is: " + js.getString("place_id"));
            String p_id = js.getString("place_id");
            testDataHandler.updateResponses("place_id", p_id, i);

            //2. Update Address

            given().queryParam("key", "qaclick123").queryParam("place_id", testDataHandler.getCellValue("place_id",i)).header("Content-Type", "application/json")
                    .body(testDataHandler.updatedPayload(i)).when().put("maps/api/place/update/json")
                    .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


            //3. Get the address

            String finalAddress = given().queryParam("key", "qaclick123").queryParam("place_id", testDataHandler.getCellValue("place_id",i)).header("Content-Type", "application/json")
                    .when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();

            JsonPath js2 = new JsonPath(finalAddress);
            testDataHandler.updateResponses("RESPONSEPAYLOAD", js2.getString("address"), i);


        }
    }
}