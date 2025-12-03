package RestAssuredAutomation;


import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class httpClient {


    @Test
    public static void onlineTrigger() throws Exception {


        CloseableHttpClient httpClient = HttpClients.createDefault();
        File filePath = new File("./src/main/resources/Payload/demo.json");
        String jsonBody = new String(Files.readAllBytes(Paths.get(filePath.getPath())));

        //build the URI
        String link = "https://rahulshettyacademy.com";
        URIBuilder builder = new URIBuilder(link);
        builder.setPath("/maps/api/place/add/json");
        builder.setParameter("key", "qaclick123");
        URI finalUri = builder.build();

        //create HttpPost request
        HttpUriRequest request = new HttpPost(finalUri);
        ((HttpPost) request).setEntity(new StringEntity(jsonBody));
        request.setHeader("Content-Type", "application/json");


        //execute the request
        HttpResponse response = httpClient.execute(request);
        String responseMessage = EntityUtils.toString(response.getEntity());

        Map<String, String> outputRes = new HashMap<>();
        outputRes.put("response", responseMessage);

        for(Map.Entry<String, String> entry : outputRes.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }




    }

    public static void main(String[] args) throws Exception {
    onlineTrigger();

    }



}
