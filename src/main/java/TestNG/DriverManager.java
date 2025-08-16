package TestNG;


import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();



    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
       driver.set(driverInstance);      //this will work because threadlocal is of object type
        //driver = driverInstance;   //this will work when private WebDriver driver; ---like this declared
    }

    public static void unload() {
        driver.remove();
    }
}

