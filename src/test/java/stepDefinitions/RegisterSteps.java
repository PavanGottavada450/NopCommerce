package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import org.testng.Assert;

public class RegisterSteps {
    WebDriver driver;
    HomePage home;
    RegisterPage register;
    String email;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver = DriverFactory.initDriver("chrome");
        driver.get("https://demo.nopcommerce.com/");
        home = new HomePage(driver);
    }

    @When("I navigate to Register")
    public void i_navigate_to_register() {
        home.clickRegister();
        register = new RegisterPage(driver);
    }

    @When("I enter valid registration details")
    public void i_enter_valid_registration_details() {
        email = "user" + System.currentTimeMillis() + "@test.com";
        register.register("John", "Doe", email, "Password123");
    }

    @Then("I should see {string}")
    public void i_should_see(String expectedMsg) {
        Assert.assertEquals(register.getSuccessMsg(), expectedMsg);
        DriverFactory.quitDriver();
    }
}
