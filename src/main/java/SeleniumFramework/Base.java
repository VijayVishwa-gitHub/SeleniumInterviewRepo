package SeleniumFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class Base {

    public static WebDriver driver;


    public static void takingScreenshot(WebDriver driver) throws IOException {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String destination = "./target/generated-sources/failed.png";
    File destFile = new File(destination);
    Files.copy(src.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    public static void webdriverWaits(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}

