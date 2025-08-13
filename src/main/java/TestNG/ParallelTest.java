package TestNG;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ParallelTest {

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        DriverFactory.initDriver(browser);  //end of this step setting happens
    }

    @Test
    public void testMethod() {
        WebDriver driver = DriverManager.getDriver();  //getting happens after setting in the previous step
        driver.get("https://www.youtube.com/");
        System.out.println("Thread ID: " + Thread.currentThread().getId() + " | Browser: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getDriver().quit();  //once again getting and quitting the browser
        DriverManager.unload();
    }//chnage
}

