package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver;
    HomePage home;
    LoginPage login;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://demo.nopcommerce.com/");
        home = new HomePage(driver);
        home.clickLogin();
        login = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        login.login("pavan@gmail.com", "pavan@123"); // <-- replace with real account
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(driver.findElement(home.logoutLink).isDisplayed());
        DriverFactory.quitDriver();
    }
}
