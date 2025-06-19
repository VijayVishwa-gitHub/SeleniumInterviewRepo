package SeleniumFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        public static WebDriver handlingDates(WebDriver driver, String day, String month, String year)
                throws InterruptedException {
            try {
                driver.navigate().to("https://www.makemytrip.com/");

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.body.style.zoom='75%'");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                Thread.sleep(5000);
                WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement close = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
                w.until(ExpectedConditions.elementToBeClickable(close));
                close.click();
                System.out.println("Login Popup closed");
                Thread.sleep(6000);
                driver.findElement(By.xpath("(//span[@class='lbl_input appendBottom10'])[3]")).click();
                Thread.sleep(6000);
                String mo = month + " " + year;
                System.out.println(mo);

                while (!mo.equalsIgnoreCase(
                        driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText())) {
                    driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
                }
                driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).click();

                // System.out.println(driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText());
                System.out.println("Month Selected");
                // System.out.println(driver.findElement(By.xpath("(//div[@class='dateInnerCell']//p[text()
                // = '"+day+"'])[1]")));
                Thread.sleep(2000);
                String amount = driver
                        .findElement(By.xpath(
                                "(//div[@class='dateInnerCell']//p[text() = '" + day + "'])[1]/following-sibling::p"))
                        .getText();

                System.out.println(amount);
                System.out.println(driver.findElement(By.xpath("(//p[text() = '" + day + "'])[1]")).getText() + " "
                        + driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText()
                        + " Date Selected");
                driver.findElement(By.xpath("(//p[text() = '" + day + "'])[1]")).click();

            } catch (ElementNotInteractableException e) {
                System.out.println("Not able to click the element");
            }
            return driver;

        }

        public static WebDriver fileUpload(WebDriver driver, String fileLoc) throws InterruptedException {
            driver.get("https://practice.expandtesting.com/upload");
            driver.findElement(By.cssSelector("input#fileInput")).sendKeys(fileLoc);
            Thread.sleep(2000);
            return driver;
        }

        public static WebDriver handlingAlerts(WebDriver driver) throws InterruptedException {
            driver.get("https://testpages.herokuapp.com/styled/alerts/alert-test.html");
            driver.findElement(By.cssSelector("#alertexamples")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
            driver.findElement(By.cssSelector("#confirmexample")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().dismiss();
            String cancel = driver.findElement(By.xpath("//p[@id = 'confirmreturn']")).getText();
            if (cancel.equalsIgnoreCase("true")) {
                System.out.println("You clicked on OK");
            } else {
                System.out.println("You clicked Cancel");
            }

            driver.findElement(By.cssSelector("#promptexample")).click();
            Thread.sleep(2000);
            Alert al = driver.switchTo().alert();
            al.sendKeys("Vijay");
            Thread.sleep(2000);
            al.accept();
            if (driver.findElement(By.xpath("//p[@id = 'promptreturn']")).getText().contains("Vijay")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAILED");
            }

            return driver;

        }

        public static WebDriver dynamicCalender(WebDriver driver, int year, String month, String dat) {
            driver.navigate().to("https://testautomationpractice.blogspot.com/");
            WebElement DatePicker = driver.findElement(By.cssSelector("#datepicker"));
            DatePicker.click();

            String yr = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
            int actYear = Integer.parseInt(yr);

            // Selecting Year
            while (actYear != year) {
                if (actYear < year) {
                    driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
                    actYear = Integer
                            .parseInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());

                }
                if (actYear > year) {
                    driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
                    actYear = Integer
                            .parseInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
                }
            }

            // Selecting Month

            String actMonth = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
            Map<String, Integer> monthMap = new HashMap<>();

            monthMap.put("January", 1);
            monthMap.put("February", 2);
            monthMap.put("March", 3);
            monthMap.put("April", 4);
            monthMap.put("May", 5);
            monthMap.put("June", 6);
            monthMap.put("July", 7);
            monthMap.put("August", 8);
            monthMap.put("September", 9);
            monthMap.put("October", 10);
            monthMap.put("November", 11);
            monthMap.put("December", 12);

            while (!actMonth.equalsIgnoreCase(month)) {
                if (monthMap.getOrDefault(actMonth, -1) > monthMap.getOrDefault(month, -1)) {
                    driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
                    actMonth = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
                }
                if (monthMap.getOrDefault(actMonth, -1) < monthMap.getOrDefault(month, -1)) {
                    driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
                    actMonth = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
                }
                if (actMonth.equalsIgnoreCase(month)) {
                    break;
                }
            }

            // Choosing Date

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd"); // Example format: 06/12/2025
            String formattedDate = dateFormat.format(date);

            if (formattedDate.equalsIgnoreCase(formattedDate)) {
                driver.findElement(By.xpath("//a[@data-date='" + dat + "']")).click();
                System.out.println(driver.findElement(By.xpath("//a[@data-date='" + dat + "']")).getText() + " " + actMonth
                        + " " + actYear);
            } else {
                List<WebElement> list1 = driver.findElements(By.xpath("//a[@class='ui-state-default']"));
                for (WebElement a : list1) {
                    String b = a.getText();
                    if (b.equalsIgnoreCase(formattedDate)) {
                        a.click();
                        System.out.println(
                                driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText()
                                        + " " + actMonth + " " + actYear);
                    }

                }
            }

            return driver;

        }

        public static void efficientCalendar(WebDriver driver, int year, String month, String date) {
            driver.navigate().to("https://testautomationpractice.blogspot.com/");
            driver.findElement(By.cssSelector("#datepicker")).click();

            // Selecting Year
            while (true) {
                int actYear = Integer
                        .parseInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
                if (actYear == year)
                    break;
                driver.findElement(By.cssSelector(
                                actYear < year ? ".ui-icon.ui-icon-circle-triangle-e" : ".ui-icon.ui-icon-circle-triangle-w"))
                        .click();
            }

            // Selecting Month
            Map<String, Integer> monthMap = getMonthMap();
            while (true) {
                String actMonth = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
                if (actMonth.equalsIgnoreCase(month)) break;
                driver.findElement(
                                By.cssSelector(monthMap.get(actMonth) < monthMap.get(month) ? ".ui-icon.ui-icon-circle-triangle-e"
                                        : ".ui-icon.ui-icon-circle-triangle-w"))
                        .click();
            }

            // Selecting Date
            driver.findElement(By.xpath("//a[@data-date='" + date + "']")).click();
            System.out.println("Selected Date: " + date + " " + month + " " + year);

        }

        private static Map<String, Integer> getMonthMap() {
            Map<String, Integer> monthMap = new HashMap<>();
            String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                    "October", "November", "December" };
            for (int i = 0; i < months.length; i++) {
                monthMap.put(months[i], i + 1);
            }
            return monthMap;
        }

        public static WebDriver dragAndDropping(WebDriver driver) {
            driver.navigate().to("https://testautomationpractice.blogspot.com/");
            WebElement draggable = driver.findElement(By.cssSelector("#draggable"));
            WebElement droppable = driver.findElement(By.cssSelector("#droppable"));
            Actions dragsDrops = new Actions(driver);
//		dragsDrops.clickAndHold(draggable)
//				.moveToElement(droppable)
//				.release()
//				.build()
//				.perform();

            dragsDrops.dragAndDrop(draggable, droppable).build().perform();
            System.out.println(droppable.getText());

            return driver;
        }

        public static WebDriver iframeTesting(WebDriver driver) throws Exception {

            driver.get("https://www.makemytrip.com/");
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='75%'");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
            Thread.sleep(5000);


//		List<WebElement> frames = driver.findElements(By.tagName("iframe")); // Get all frames
//		System.out.println("Total frames: " + frames.size());
//		for (int i = 0; i < frames.size(); i++) {
//		    System.out.println("Frame " + i + " ID: " + frames.get(i).getAttribute("id"));
//		    System.out.println("Frame " + i + " Name: " + frames.get(i).getAttribute("name"));
//		    System.out.println("Frame " + i + " Source: " + frames.get(i).getAttribute("src"));
//		}
            Thread.sleep(7000);
            //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Sign in with Google Button']")));
            Set<String> newTab = driver.getWindowHandles();
            for (String x : newTab) {
                System.out.println(x);
            }
            //System.out.println(driver.getTitle());
            //driver.findElement(By.xpath("//input[@type='email']")).sendKeys("vijayvishwa242@gmail.com");
            //System.out.println(driver.findElement(By.cssSelector("h1[id='headingText'] span")).getText());


//		driver.switchTo().defaultContent();
//		//driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Vijay Bala");
//		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='frame_2.html']")));
//		driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("Vijay Bala");
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='frame_4.html']")));
//		driver.findElement(By.xpath("//input[@name='mytext4']")).sendKeys("Vijay Bala");
//		driver.switchTo().defaultContent();
//		Thread.sleep(2000);
            return driver;
        }

        public static void firstProject(WebDriver driver) throws InterruptedException {

        driver.get("https://www.makemytrip.com/");
        String homePage = driver.getWindowHandle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Sign in with Google Button']")));
        Wait<WebDriver> w = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);
        WebElement google = driver.findElement(By.xpath("//div[@class='nsm7Bb-HzV7m-LgbsSe-Bz112c']"));
        w.until(ExpectedConditions.elementToBeClickable(google));
        google.click();
        Set<String> windows = driver.getWindowHandles();
        for(String j : windows){
            if (!j.equalsIgnoreCase(homePage)){
                driver.switchTo().window(j);
                break;
            }
        }
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("vijayvishwa242@gmail.com");
        driver.close();
        driver.switchTo().window(homePage);
        System.out.println(driver.getTitle());

    }


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // String fileLoc =
        // "C:\\Users\\VijayBala\\eclipse-workspace\\DataDrivenFramework\\src\\main\\java\\Automation\\DataDrivenFramework\\Upload
        // file.txt";
        // fileUpload(driver, fileLoc); --TO TEST FILE UPLOAD
        // handlingAlerts(driver); --TO TEST HANDLING ALERTS
        // handlingDates(driver, "24", "October", "2025");
        // dragAndDropping(driver);
        efficientCalendar(driver, 2026, "June", "12");
        try{
            efficientCalendar(driver, 2026, "June", "12");
            //handlingDates(driver, "15", "June", "2025");
        //iframeTesting(driver);
        } catch (ElementNotInteractableException e) {
            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}