package SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class ActionsClass {
    public static WebDriver driver;
@Test
    public static void actionDemo() throws InterruptedException {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.amazon.in/");
        driver.manage().window().maximize();
        Actions testingNormal = new Actions(driver);
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));


        testingNormal.moveToElement(searchBox).click().sendKeys("Phone");
//       testingNormal.moveToElement(searchBox).sendKeys("PHONE");
        testingNormal.moveToElement(searchButton).click();

        Thread.sleep(3000);
        testingNormal.build().perform();
    WebElement BestSellers = driver.findElement(By.xpath("//a[normalize-space()='Bestsellers']"));
    testingNormal.moveToElement(driver.findElement(By.xpath("//a[@aria-label='Apply the filter Get It Today to narrow results']//i[@class='a-icon a-icon-checkbox']"))).doubleClick();
    testingNormal.contextClick(BestSellers).build().perform();

    }
}
