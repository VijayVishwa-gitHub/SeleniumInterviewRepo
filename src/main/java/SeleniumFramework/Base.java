package SeleniumFramework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Base {

    public static WebDriver driver;


    public String takingScreenshot(WebDriver driver) throws IOException {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String destination = "./target/generated-sources/failed.png";
    File destFile = new File(destination);
    Files.copy(src.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    return destination;
    }
}
