package SeleniumFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    public static void visibilityOfElementWait(WebDriver driver, By Selector, int time, String waitName) {

        WebDriverWait wait;

        if (waitName.equalsIgnoreCase("visibilityOfElements")) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(Selector));
        }
        else if(waitName.equalsIgnoreCase("visibilityOfList")){
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(Selector, 0));
        }
    }

    public WebElement elementToBeClickableWait (WebDriver driver, By locator, int time, String waitName){

            WebDriverWait wait;
            if (waitName.equalsIgnoreCase("elementToBeClickable")) {
                wait = new WebDriverWait(driver, Duration.ofSeconds(time));
                return wait.until(ExpectedConditions.elementToBeClickable(locator));
                
            } return null;
    }

    public static void testingURL (ArrayList< WebElement > listOfURLs){

            System.out.println(listOfURLs.size());

            for (WebElement x : listOfURLs) {
                String url = x.getAttribute("href");
                Assert.assertNotNull(url);

                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    if (responseCode != 200) {
                        System.out.println(url);
                    }
                    System.out.println(responseCode);
                } catch (Exception e) {
                    System.out.println(url);

                   e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }

    }

    public static void settingUpDriver (WebDriver driver, String url){
            driver.manage().window().maximize();
            driver.get(url);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='80%';");
        }
    }

