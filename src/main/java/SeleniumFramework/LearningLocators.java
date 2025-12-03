package SeleniumFramework;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LearningLocators {

    // -----//a[@data-ref-tag='nav_em_1_1_1_7']/child::childTag
    //-----//a[@data-ref-tag='nav_em_1_1_1_7']/parent::parentTag
    // //section[@aria-labelledby='Digital Content and Devices']//preceding-sibling::div
    // //section[@aria-labelledby='Digital Content and Devices']//following-sibling::section

    // cssSelector Syntax

    //  tagname[attribute='value']
    //  # - For ID
    //  . - For Class
    //  tagname.value


    @Test
    public static void testingXpath() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");

        //String attributeName = driver.findElement(By.cssSelector("div[id='hmenu-customer-profile']:nth-child(1)")).getText();
        driver.findElement(By.cssSelector("#nav-hamburger-menu")).click();
        // similarly there exist first-child which we can use //div[id='hmenu-customer-profile']:first-child
        Thread.sleep(2000);
        WebElement element1= driver.findElement(By.xpath("//a[@data-ref-tag='nav_em_1_1_1_7']"));
        WebElement element2 = driver.findElement(with(By.tagName("a")).below(element1));
        System.out.println(driver.findElement(with(By.tagName("a")).below(element2)).getText());
        //above, below, toLeftOf, toRightOf, near
        WebElement element3 = driver.findElement(By.xpath("(//li[@class='nav_first']//following-sibling::a)[1]")) ;
        //driver.findElement(By.tagName("a")).toLeftOf(element3));
        driver.quit();


    }
    @Test
    public static void testingxpath2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        WebElement ele = driver.findElement(By.id(("twotabsearchtextbox")));
        ele.sendKeys("iPhone");
        driver.findElement(with(By.tagName("input")).straightLeftOf(ele)).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
