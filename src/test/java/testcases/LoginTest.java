package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;

@Listeners(utils.MyListener.class)
public class LoginTest {
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
	}

	@Test
	public void testValidLogin() {
		home.clickLogin();
		login.login("pavan@gmail.com", "pavan@123"); // use a registered account
		Assert.assertTrue(driver.findElement(home.logoutLink).isDisplayed());
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
