package JavaProgramming;

import SeleniumFramework.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static SeleniumFramework.Main.driver;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class CodeDaily extends Base {

 
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
        //options.addArguments("--headless=new", "--disable-gpu");


        WebDriver driver = new ChromeDriver(options);
        driver.get("https://developers.pismo.io/pismo-docs/reference/corporate-v2-post-payments");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("document.body.style.zoom='50%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@class='wscrOk2']")).click();
        driver.findElement(By.xpath("(//span[@class='APIResponse-menu-status'])[1]")).click();
        driver.findElement(By.xpath("//button[@class='Button Button_xs APIResponse-action-button3OHrWUSymAt1 APIResponse-action-button3OHrWUSymAt1 Dropdown-toggle Button_minimum_ghost Button_minimum']//span[@class='IconWrapper Icon-wrapper2z2wVIeGsiUy']//*[name()='svg']")).click();
        List<WebElement> list1 = driver.findElements(By.xpath("//div[@tabindex='0']"));
        System.out.println(list1.size());

        //(from 66 to 71)
        while (list1.size() == 71) {
            for (int i = 4; i < list1.size() - 4; i++) {
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
        WebDriver driver = new ChromeDriver();
        //options.addArguments("--headless=new", "--disable-gpu");
        //WebDriver driver = new EdgeDriver(options);

        driver.navigate().to("https://developers.pismo.io/pismo-docs/reference/corporate-v3-post-multileg-payments");
        //driver.manage().window().maximize();
        driver.navigate().refresh();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom='50%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//a[@class='wscrOk2']")).click();
        //driver.findElement(By.xpath("//*[@id=\"ReferencePlayground\"]/section[3]/section/header/div[2]/button/span[1]")).click();
        driver.findElement(By.xpath("//div/button[@class='Button Button_xs APIResponse-action-button3OHrWUSymAt1 APIResponse-action-button3OHrWUSymAt1 Dropdown-toggle Button_minimum_ghost Button_minimum']")).click();
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

    @Test
    public void ErrorCodesTxnDormancy() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://developers.pismo.io/pismo-docs/reference/post-dormancy-config-v2");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@class='wscrOk2']")).click();
        driver.findElement(By.xpath("(//span[@class='APIResponse-menu-status'])[1]")).click();
        driver.findElement(By.xpath("//i[@class='icon-chevron-down APIResponse-action-icon']")).click();
        List<WebElement> list1 = driver.findElements(By.xpath("//div[@tabindex='0']"));

        System.out.println(list1.size());

            for (int i = 3; i < 28; i++) {
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

    @Test
    public void sampleTest() throws InterruptedException, IOException {
        
            By popup1 = By.cssSelector(".tp-dt-header-icon:last-child");
            By popup2 = By.cssSelector(".commonModal__close:first-child");
            By headerIcons = By.xpath("//span/a/span[2]");
            By fromCityInput = By.cssSelector("input[placeholder='From']");
            By fromCityOptions = By.xpath("//p[@class='searchedResult font14 blackText appendBottom5']//span/span");
            By airportCode = By.xpath("//span[@class='sr_iata font14 lightGreyText latoBold']");

            try {
                driver = new ChromeDriver();
                settingUpDriver(driver, "https://www.makemytrip.com/");

                elementToBeClickableWait(driver, popup1, 5, "elementToBeClickable").click();
                elementToBeClickableWait(driver, popup2, 5, "elementToBeClickable").click();
                driver.findElements(headerIcons).forEach(e->System.out.println(e.getText()));

                Actions actions = new Actions(driver);
                actions.moveByOffset(10, 10).click().perform();

                driver.findElement(By.cssSelector("label[for='fromCity']")).click();
                driver.findElement(fromCityInput).sendKeys("Che");

                visibilityOfElementWait(driver, fromCityOptions, 5, "visibilityOfList");

                for (WebElement element2 : driver.findElements(fromCityOptions)) {
                    System.out.println(element2.getText() +" "+driver.findElement(airportCode).getText());}

                driver.quit();

            }
            catch (WebDriverException e) {
                System.out.println(e.getMessage());

                TakesScreenshot ss = (TakesScreenshot) driver;
                File src2 = ss.getScreenshotAs(OutputType.FILE);
                Files.copy(src2.toPath(), new File("./target/generated-sources/failed.png").toPath());
            }
    }


    @Test
    public static void stringMani(){

        String input = "Java is a Great Programming Language";

        input = input.replaceAll("an|the|and|but|or|for|nor|so|a|A", "");

        String[] modifiedInput =  input.trim().split(" ");


        StringBuilder sb = new StringBuilder();
        int i =0;
        char y;
        for(String x : modifiedInput) {

            if (x != " " && x.length()>0) {
                char c = x.charAt(0);
                if(i%2==0){
                    y = Character.toUpperCase(c);
                    sb.append(y);
                    i++;
                }
                else{
                    y = Character.toLowerCase(c);
                    sb.append(y);
                    i++;
                }
            }
            else{
                i++;
            }
        }
        System.out.println(sb);
}
}









