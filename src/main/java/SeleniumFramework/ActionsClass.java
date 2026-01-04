package SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class ActionsClass {
    public static WebDriver driver;
@Test
    public static void actionDemo() throws InterruptedException {
    driver = new ChromeDriver();
    driver.navigate().to("https://www.amazon.in/");
    driver.manage().window().maximize();

    SoftAssert check = new SoftAssert();
    //check.assertTrue(driver.findElement(By.cssSelector(".a-button-text")).isDisplayed());

        Actions testingNormal = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));


        testingNormal.moveToElement(searchBox).click().sendKeys("Phone");
//       testingNormal.moveToElement(searchBox).sendKeys("PHONE");
        testingNormal.moveToElement(searchButton).click();

        testingNormal.build().perform();

        WebElement BestSellers = driver.findElement(By.xpath("//a[normalize-space()='Bestsellers']"));
        //testingNormal.moveToElement(driver.findElement(By.xpath("//a[@aria-label='Apply the filter Get It Today to narrow results']//i[@class='a-icon a-icon-checkbox']"))).doubleClick();
        testingNormal.contextClick(BestSellers).build().perform();


}
}
