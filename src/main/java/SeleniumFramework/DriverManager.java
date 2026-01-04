package SeleniumFramework;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver Webdriver) {
        driver.set(Webdriver);
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void unload(){
        driver.remove();
    }
}
