package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

public class MyListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        test.get().log(Status.INFO, "🔹 Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test Passed: " + result.getName());
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test Failed: " + result.getName());
        test.get().log(Status.FAIL, result.getThrowable()); // log error/stacktrace

        System.out.println("❌ Test Failed: " + result.getName());

        // Get driver from DriverFactory
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            System.out.println("📸 Screenshot saved at: " + screenshotPath);
            try {
                test.get().addScreenCaptureFromPath(screenshotPath); // embed screenshot in report
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getName());
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.get().log(Status.WARNING, "⚠️ Test failed but within success percentage: " + result.getName());
        System.out.println("⚠️ Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        test.get().log(Status.FAIL, "⏱️ Test failed due to timeout: " + result.getName());
        System.out.println("⏱️ Test failed due to timeout: " + result.getName());
        onTestFailure(result); // also capture screenshot
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=======> Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=======> Finished Test Suite: " + context.getName());
        extent.flush(); // generate report
        System.out.println("📊 Extent Report generated at: " 
            + System.getProperty("user.dir") + "/reports/ExtentReport.html");
    }
}
