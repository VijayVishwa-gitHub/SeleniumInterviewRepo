package RestAssuredAutomation;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SampleClass {

    @Test
    public void firstTest() throws IOException {

        RequestSpecification reqSpecification = new RequestSpecBuilder()
                .addHeader("ContentType", "application/json")
                .addQueryParam("key","qaclick123")
                .setBaseUri("https://rahulshettyacademy.com")
                .setBasePath("maps/api/place/add")
                .build();

        int count = testDataHandler.count;
        for (int i = 0; i < count; i++) {
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            File filePath = new File("./src/main/resources/Payload/demo.json");


            // 1. Get Place_id

            String response = given().spec(reqSpecification).body(filePath).when().post("/json")
                    .then().assertThat().time(lessThan(2000L), TimeUnit.MILLISECONDS).statusCode(200).body("scope", equalTo("APP")).extract().response().asString();


            JsonPath js = new JsonPath(response);
            System.out.println("The place_id is: " + js.getString("place_id"));
            String p_id = js.getString("place_id");
            testDataHandler.updateResponses("place_id", p_id, i);

            //2. Update Address

            given().queryParam("key", "qaclick123").queryParam("place_id", testDataHandler.getCellValue("place_id", i)).header("Content-Type", "application/json")
                    .body(testDataHandler.updatedPayload(i)).when().put("/maps/api/place/update/json")
                    .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


            //3. Get the address

            String finalAddress = given().queryParam("key", "qaclick123").queryParam("place_id", testDataHandler.getCellValue("place_id", i)).header("Content-Type", "application/json")
                    .when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();

            JsonPath js2 = new JsonPath(finalAddress);
            testDataHandler.updateResponses("RESPONSEPAYLOAD", js2.getString("address"), i);


        }
    }

    @Test
    public void testingPOJOClass() {
        Pojo reqpay = new Pojo();

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        String response2 = given().body(reqpay).header("content-type", "application/json").when().post("/posts").then().assertThat().statusCode(201).extract().response().toString();

        JsonPath js = new JsonPath(response2);
        System.out.println(response2);

    }

    @Test
    public void testPOJO() {
        String json = "{\n" +
                "        \"orderId\": \"ORD123\",\n" +
                "            \"customer\": {\n" +
                "        \"id\": \"C101\",\n" +
                "                \"name\": \"Amit\"\n" +
                "    },\n" +
                "        \"items\": [\n" +
                "        {\n" +
                "            \"itemId\": \"I1\",\n" +
                "                \"qty\": 2,\n" +
                "                \"price\": 500\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemId\": \"I2\",\n" +
                "                \"qty\": 1,\n" +
                "                \"price\": 1200\n" +
                "        }\n" +
                "  ]}"
;

        try {
            ObjectMapper mapper = new ObjectMapper();
            Pojo order = mapper.readValue(json, Pojo.class);

            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Customer Name: " + order.getCustomerDetails().getName());
            order.getItems().forEach(item ->
                    System.out.println(item.getItemID() + " - Qty: " + item.getQty() + " - Price: " + item.getPrice())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}