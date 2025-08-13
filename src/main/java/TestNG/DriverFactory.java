package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriver driverInstance;

        if (browser.equalsIgnoreCase("chrome")) {
            driverInstance = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driverInstance = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driverInstance.manage().window().maximize();
        DriverManager.setDriver(driverInstance);  //setting happens after choosing browser
        return driverInstance;
    }
}
