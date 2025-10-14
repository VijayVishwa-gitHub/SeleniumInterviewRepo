package SeleniumFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;

public class Base {

    public static WebDriver driver;


    public static void takingScreenshot(WebDriver driver) throws IOException {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String destination = "./target/generated-sources/failed.png";
    File destFile = new File(destination);
    Files.copy(src.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    TakesScreenshot ss = (TakesScreenshot) driver;
    File src2 = ss.getScreenshotAs(OutputType.FILE);
    Files.copy(src2.toPath(), new File("./target/generated-sources/failed.png").toPath());

    }

    public static void webdriverWaits(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void testingURLs(ArrayList<WebElement> listOfURLs) throws IOException {

        System.out.println(listOfURLs.size());

        for (WebElement x : listOfURLs) {

            String url = x.getAttribute("href");
            Assert.assertNotNull(url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode != 200){
                System.out.println(url);
            }

            System.out.println(responseCode);
        }
    }

}

