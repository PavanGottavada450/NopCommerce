package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverFactory;

@Listeners(utils.MyListener.class)
public class LoginNegativeTests {

    WebDriver driver;
    LoginPage loginPage;

    @Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
    	driver = DriverFactory.initDriver(browser);
        driver.get("https://demo.nopcommerce.com/login");
        loginPage = new LoginPage(driver);
    }

    // TC-2: Login with invalid email
    @Test
    public void testInvalidEmail() {
        loginPage.login("invalid@email.com", "anyPassword");
        String errorMsg = driver.findElement(By.cssSelector(".message-error")).getText();
        Assert.assertTrue(errorMsg.contains("No customer account found"), "Error message mismatch");
    }

    // TC-3: Login with invalid password
    @Test
    public void testInvalidPassword() {
        loginPage.login("pavan@gmail.com", "wrongPassword");
        String errorMsg = driver.findElement(By.cssSelector(".message-error")).getText();
        Assert.assertTrue(errorMsg.contains("The credentials provided are incorrect"), "Error message mismatch");
    }

    // TC-4: Login with blank email
    @Test
    public void testBlankEmail() {
        loginPage.login("", "somePassword");
        String errorMsg = driver.findElement(By.cssSelector(".field-validation-error")).getText();
        Assert.assertTrue(errorMsg.contains("Please enter your email"), "Error message mismatch");
    }

    // TC-5: Login with blank password
    @Test
    public void testBlankPassword() {
        loginPage.login("valid_email@example.com", "");
        String errorMsg = driver.findElement(By.cssSelector(".message-error")).getText();
        Assert.assertTrue(errorMsg.contains("Login was unsuccessful"), "Error message mismatch");
    }

    // TC-6: Login with blank email and password
    @Test
    public void testBlankEmailAndPassword() {
        loginPage.login("", "");
        String errorMsg = driver.findElement(By.cssSelector(".field-validation-error")).getText();
        Assert.assertTrue(errorMsg.contains("Please enter your email"), "Error message mismatch");
    }

    // TC-7: Login with invalid email format
    @Test
    public void testInvalidEmailFormat() {
        loginPage.login("invalidEmailFormat", "anyPassword");
        String errorMsg = driver.findElement(By.cssSelector(".field-validation-error")).getText();
        Assert.assertTrue(errorMsg.contains("Please enter a valid email address."), "Error message mismatch");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
