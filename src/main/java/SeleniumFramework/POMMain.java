package SeleniumFramework;

import TestNG.ListenerClass;
import TestNG.dataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;



@Listeners(TestNG.ListenerClass.class)  //Listener should be kept at class level
public class POMMain extends Base{

    PageObjectModel pageobject;

    @BeforeMethod
    public void initializebrowser(){;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

    }


   @Test(dataProvider = "Logindata", dataProviderClass = TestNG.dataProvider.class)

    public void loginPage(String username, String password) throws InterruptedException {
        try {
            pageobject = new PageObjectModel(driver);
            pageobject.LoginPage(username, password);
            //pageobject.LoginPage("student", "password123");
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement result = driver.findElement(By.xpath("//h1[@class='post-title']"));
            wait.until(ExpectedConditions.visibilityOf(result));
            System.out.println(result.getText());
        }
        catch (Exception e) {
           System.out.println("Login Failed for: " + username + " | Reason: " + e.getMessage());
           // you can log screenshot here as well
       }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }

}
