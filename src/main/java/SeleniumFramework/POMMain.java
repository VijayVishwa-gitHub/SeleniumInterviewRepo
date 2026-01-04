package SeleniumFramework;

import TestNG.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


//@Listeners(TestNG.ListenerClass.class)  //Listener should be kept at class level

/* Here we have used DriverManager.java and DriverFactory.java class for parallel execution using ThreadLocal*/

public class POMMain extends Base{

    PageObjectModel pageobject;

    @BeforeMethod
    public void initializebrowser() {

        WebDriver driver = DriverFactory.createWebDriver("chrome");
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();

        System.out.println(STR."SETUP | Thread: \{Thread.currentThread().getId()} | Driver: \{DriverManager.getDriver().hashCode()}");
    }

    @Test
    public void urlTesting() throws IOException {

        System.out.println(STR."TEARDOWN | Thread: \{Thread.currentThread().getId()} | Driver: \{DriverManager.getDriver().hashCode()}");

        DriverManager.getDriver().get("https://www.amazon.in/");
        List<WebElement> list1 = new ArrayList<>();
        list1 = DriverManager.getDriver().findElements(By.xpath("//li[@class='nav_first']//following-sibling::a"));
        testingURL((ArrayList<WebElement>) list1);
    }

    @Test(dataProvider = "Logindata", dataProviderClass = TestNG.dataProvider.class)
    public void loginPage(String username, String password) {

        System.out.println(STR."TEST2 | Thread: \{Thread.currentThread().getId()} | Driver: \{DriverManager.getDriver().hashCode()}");
        SoftAssert softAssert = new SoftAssert();
        DriverManager.getDriver()
                .get("https://practicetestautomation.com/practice-test-login");

        try {
            pageobject = new PageObjectModel(DriverManager.getDriver());
            pageobject.LoginPage(username, password);

            WebDriverWait wait =
                    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

            WebElement result = DriverManager.getDriver()
                    .findElement(By.xpath("//h1[@class='post-title']"));

            wait.until(ExpectedConditions.visibilityOf(result));
            System.out.println(result.getText());

        } catch (Exception e) {
           softAssert.fail("Login Failed for: " + username);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {

        System.out.println(STR."TEARDOWN | Thread: \{Thread.currentThread().getId()} | Driver: \{DriverManager.getDriver().hashCode()}");

        DriverManager.getDriver().quit();
        DriverManager.unload();
    }

}
