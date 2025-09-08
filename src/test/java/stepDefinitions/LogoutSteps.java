package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import org.testng.Assert;

public class LogoutSteps {
    WebDriver driver;
    HomePage home;
    LoginPage login;

    @Given("I am logged in")
    public void i_am_logged_in() {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://demo.nopcommerce.com/");
        home = new HomePage(driver);
        login = new LoginPage(driver);
        home.clickLogin();
        login.login("pavan@gmail.com", "pavan@123"); // <-- replace with registered account
    }

    @When("I click logout")
    public void i_click_logout() {
        home.clickLogout();
    }

    @Then("I should be logged out")
    public void i_should_be_logged_out() {
        Assert.assertTrue(driver.findElement(home.loginLink).isDisplayed());
        DriverFactory.quitDriver();
    }
}
