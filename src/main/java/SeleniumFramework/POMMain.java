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

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Listeners(TestNG.ListenerClass.class)  //Listener should be kept at class level

public class POMMain extends Base{

    PageObjectModel pageobject;

    @BeforeMethod
    public void initializebrowser(){;
        DriverFactory.initDriver("chrome");
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void urlTesting() throws IOException {

        driver.get("https://www.amazon.in/");
        List<WebElement> list1 = new ArrayList<>();
        list1 = driver.findElements(By.xpath("//li[@class='nav_first']//following-sibling::a"));
        testingURL((ArrayList<WebElement>) list1);
    }

   @Test(dataProvider = "Logindata", dataProviderClass = TestNG.dataProvider.class)
    public void loginPage(String username, String password) throws InterruptedException {
        try {
            pageobject = new PageObjectModel(driver);
            pageobject.LoginPage(username, password);


            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement result = driver.findElement(By.xpath("//h1[@class='post-title']"));
            wait.until(ExpectedConditions.visibilityOf(result));
            System.out.println(result.getText());
        }
        catch (Exception e) {
           System.out.println("Login Failed for: " + username + " | Reason: " + e.getMessage());
            Assert.fail("Login Failed " +username +" " +password);
       }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }

}
