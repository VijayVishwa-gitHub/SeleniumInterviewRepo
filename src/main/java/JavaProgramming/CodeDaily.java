package JavaProgramming;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.Inet4Address;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CodeDaily {

    @Test
    public void wings() {
        String captcha = "5 - 4";
        String[] arr = captcha.split(" ");
        if (arr[1].equalsIgnoreCase("-")) {
            int y = Integer.parseInt(arr[0]);
            int z = Integer.parseInt(arr[2]);
            System.out.println(Integer.toString(y - z));
        }
    }
    @Test
    public void ErrorCodesTxnPostings() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://developers.pismo.io/pismo-docs/reference/corporate-v2-post-payments");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@class='wscrOk2']")).click();
        driver.findElement(By.xpath("(//span[@class='APIResponse-menu-status'])[1]")).click();
        driver.findElement(By.xpath("//i[@class='icon-chevron-down APIResponse-action-icon']")).click();
        List<WebElement> list1 = driver.findElements(By.xpath("//div[@tabindex='0']"));
        System.out.println(list1.size());
        while (list1.size() == 66) {
            for (int i = 4; i < list1.size() - 5; i++) {
                //driver.findElement(By.xpath("(//div[@data-testid='dropdown-container'])[13]"));
                Actions ac = new Actions(driver);
                WebDriverWait bc = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement list3 = driver.findElement(By.xpath("(//div[@class='APIResponse-menu-status'])[" + i + "]"));
                bc.until(ExpectedConditions.elementToBeClickable(list3));
                ac.moveToElement(list3).perform();
                bc.until(ExpectedConditions.elementToBeClickable(list3));
                //driver.findElement(By.xpath("(//div[@data-testid='dropdown-container'])[13]")).click();
                ac.moveToElement(list3).perform();
                list3.click();
                //Transaction Posting
                String errorCode = driver.findElement(By.xpath("(//span[@class='cm-string'])[9]")).getText();
                String errorMessage = driver.findElement(By.xpath("(//span[@class='cm-string'])[10]")).getText();
                System.out.println(errorCode + " " + errorMessage);
                driver.findElement(By.xpath("//div[@class='APIResponse-menu-status']")).click();


            }

            driver.quit();
        }
    }

    @Test
    public void ErrorCodesMultileg() throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new", "--disable-gpu");
        WebDriver driver = new EdgeDriver(options);

        driver.get("https://developers.pismo.io/pismo-docs/reference/corporate-v2-post-multileg-payments");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@class='wscrOk2']")).click();
        driver.findElement(By.xpath("(//span[@class='APIResponse-menu-status'])[1]")).click();
        driver.findElement(By.xpath("//i[@class='icon-chevron-down APIResponse-action-icon']")).click();
        List<WebElement> list1 = driver.findElements(By.xpath("//div[@tabindex='0']"));
        System.out.println(list1.size());
        for (int i = 3; i < list1.size() - 4; i++) {
            Actions ac = new Actions(driver);
            WebElement list3 = driver.findElement(By.xpath("(//div[@class='APIResponse-menu-status'])[" + i + "]"));
            WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(3));
            wc.until(ExpectedConditions.elementToBeClickable(list3));
            ac.moveToElement(list3).perform();
            wc.until(ExpectedConditions.elementToBeClickable(list3));
            list3.click();
            if (i < 9) {
                Thread.sleep(2000);
                String errorCode = driver.findElement(By.xpath("(//span[@class='cm-string'])[3]")).getText();
                String errorMessage = driver.findElement(By.xpath("(//span[@class='cm-string'])[4]")).getText();
                System.out.println(errorCode + " " + errorMessage);
                driver.findElement(By.xpath("//div[@class='APIResponse-menu-status']")).click();
            } else {
                try {
                    if (i == 45) {
                        String errorCode = driver.findElement(By.xpath("//span[@class='cm-string' and contains(text(), 'EC')]")).getText();
                        String errorMessage = driver.findElement(By.xpath("(//span[@class='cm-property' and contains(text(), 'message')]/following-sibling::*)")).getText();
                        System.out.println(errorCode + " " + errorMessage);
                        driver.quit();
                    }
                    String errorCode = driver.findElement(By.xpath("(//span[@class='cm-string' and contains(text(), 'WM')])[2]")).getText();
                    String errorMessage = driver.findElement(By.xpath("(//span[@class='cm-property' and contains(text(), 'message')]/following-sibling::*)[2]")).getText();
                    System.out.println(errorCode + " " + errorMessage);
                } catch (NoSuchElementException e) {
                    System.out.println("This item is skipped");
                } finally {
                    driver.findElement(By.xpath("//div[@class='APIResponse-menu-status']")).click();
                    continue;
                }
            }


        }
        driver.quit();
    }

    }





