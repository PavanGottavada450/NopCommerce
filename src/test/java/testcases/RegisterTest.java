package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.DataProviders;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;

@Listeners(utils.MyListener.class)
public class RegisterTest {
	WebDriver driver;
	HomePage home;
	RegisterPage register;

	
	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
		driver = DriverFactory.initDriver(browser);
		driver.get("https://demo.nopcommerce.com/");
		home = new HomePage(driver);
		register = new RegisterPage(driver);
	}

	
	@Test(dataProvider = "registerData", dataProviderClass = DataProviders.class)
    public void testUserRegistration(String firstName, String lastName, String email, String password) {
        home.clickRegister();
        register.register(firstName, lastName, email, password);
        Assert.assertEquals(register.getSuccessMsg(), "Your registration completed");
    }

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
