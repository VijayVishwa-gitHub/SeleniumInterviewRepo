package RestAssuredAutomation;


import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.net.Proxy;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static RestAssuredAutomation.testDataHandler.logger;
import static RestAssuredAutomation.testDataHandler.validateAndSaveResponse;


public class httpClient {


    @Test
    public static void onlineTrigger(int count) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        for (int i = 0; i < count; i++) {
            logger.info("**************Running Testcase: "+testDataHandler.getCellValue("TestcaseID", i) +" *******************");

//            File filePath = new File("./src/main/resources/Payload/demo.json");                   //taking payload directly from .json file
//            String jsonBody = new String(Files.readAllBytes(Paths.get(filePath.getPath())));
              String jsonBody2 = testDataHandler.updatedPayload(i);                                    //here fetching the payload from datasheet


            String link = "https://rahulshettyacademy.com";
            URIBuilder builder = new URIBuilder(link);
            builder.setPath("/maps/api/place/add/json");
            builder.setParameter("key", "qaclick123");
            URI finalUri = builder.build();

            //create HttpPost request
            HttpPost request = new HttpPost(finalUri);
            request.setEntity(new StringEntity(jsonBody2));
            request.setHeader("Content-Type", "application/json");

            // execute the request and measure time
            long start = System.nanoTime();

            //execute the request
            logger.info("Going to Post the request");
            HttpResponse response = httpClient.execute(request);  //response object's metadata and does not contain json response body content
            logger.info("Status Code: " + response.getStatusLine().getStatusCode());

            long end = System.nanoTime();
            long responseTimeMs = (end - start) / 1_000_000;

            String responseMessage = EntityUtils.toString(response.getEntity());  // converts json response into a string and prints

            Map<String, String> outputRes = new HashMap<>();
            outputRes.put("response", responseMessage);

            testDataHandler.updateResponses("RESPONSEPAYLOAD", responseMessage, i);
            //validateAndSaveResponse(jsonBody, responseMessage, response, responseTimeMs, finalUri, request);

        }



            httpClient.close();

    }

    public static void main(String[] args) throws Exception {
    onlineTrigger(testDataHandler.count);

    }



}
