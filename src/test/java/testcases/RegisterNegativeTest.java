package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.DriverFactory;

@Listeners(utils.MyListener.class)
public class RegisterNegativeTest {
	WebDriver driver;
    private RegisterPage registerPage;

    @Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
		driver = DriverFactory.initDriver(browser);
		driver.get("https://demo.nopcommerce.com/register");
		registerPage = new RegisterPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    
    @Test(priority = 1)
    public void testEmptyForm() {
        registerPage.clickRegister();
        Assert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailError(), "Email is required.");
//        Assert.assertEquals(registerPage.getPasswordError(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordError(), "Password is required.");
    }

    @Test(priority = 2)
    public void testInvalidEmailFormat() {
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("abc@");
        registerPage.enterPassword("Test123");
        registerPage.enterConfirmPassword("Test123");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getEmailError(), "Please enter a valid email address.");
    }

    @Test(priority = 3)
    public void testPasswordMismatch() {
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("john.doe@example.com");
        registerPage.enterPassword("Test123");
        registerPage.enterConfirmPassword("Test321");
        registerPage.clickRegister();

        Assert.assertTrue(registerPage.getMismatchPasswordError()
                .contains("The password and confirmation password do not match."));
    }

    @Test(priority = 4)
    public void testPasswordBelowMinLength() {
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("john.doe@example.com");
        registerPage.enterPassword("123");
        registerPage.enterConfirmPassword("123");
        registerPage.clickRegister();

        Assert.assertTrue(registerPage.getPasswordError()
                .contains("Password must meet the following rules: must have at least 6 characters and not greater than 64 characters"));
    }

    @Test(priority = 5)
    public void testFirstNameBlank() {
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("john.doe@example.com");
        registerPage.enterPassword("Test123");
        registerPage.enterConfirmPassword("Test123");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
    }

    @Test(priority = 6)
    public void testLastNameBlank() {
        registerPage.enterFirstName("John");
        registerPage.enterEmail("john.doe@example.com");
        registerPage.enterPassword("Test123");
        registerPage.enterConfirmPassword("Test123");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
    }
}
