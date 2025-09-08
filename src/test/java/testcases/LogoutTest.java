package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;

@Listeners(utils.MyListener.class)
public class LogoutTest {
	WebDriver driver;
	HomePage home;
	LoginPage login;

	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
		driver.get("https://demo.nopcommerce.com/");
		home = new HomePage(driver);
		login = new LoginPage(driver);
		home.clickLogin();
		login.login("pavan@gmail.com", "pavan@123"); // registered account
	}

	@Test
	public void testLogout() {
		home.clickLogout();
		Assert.assertTrue(driver.findElement(home.loginLink).isDisplayed());
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
