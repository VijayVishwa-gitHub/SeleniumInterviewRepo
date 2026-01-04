package TestNG;

import SeleniumFramework.Base;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static SeleniumFramework.Base.driver;


public class ListenerClass implements ITestListener {

        @Override
        public void onTestStart(ITestResult result) {
            System.out.println("Test Started: " + result.getName());
            System.out.println();
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            System.out.println("Test Passed: " + result.getName());
        }

        @Override
        public void onTestFailure(ITestResult result) {

            try {
                Base.takingScreenshot(driver);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Screenshot saved at : ./target/generated-sources/failed.png " );
            System.out.println("Test Failed: " + result.getName());

        }

        @Override
        public void onTestSkipped(ITestResult result) {
            System.out.println("Test Skipped: " + result.getName());
        }

        @Override
        public void onStart(ITestContext context) {
            System.out.println("TestNG Started: " + context.getName());
        }

        @Override
        public void onFinish(ITestContext context) {
            System.out.println("TestNG Finished: " + context.getName());
        }
    }


