package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	WebDriver driver;

	By genderMale = By.id("gender-male");
	By firstName = By.id("FirstName");
	By lastName = By.id("LastName");
	By email = By.id("Email");
	By password = By.id("Password");
	By confirmPassword = By.id("ConfirmPassword");
	By registerBtn = By.id("register-button");
	By successMsg = By.className("result");

	// Validation messages
	By firstNameError = By.id("FirstName-error");
	By lastNameError = By.id("LastName-error");
	By emailError = By.id("Email-error");
	By passwordError = By.id("Password-error");
	By confirmPasswordError = By.id("ConfirmPassword-error");
	By mismatchPasswordError = By.xpath("//span[contains(text(),'do not match')]");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void register(String fName, String lName, String mail, String pass) {
		driver.findElement(genderMale).click();
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(lName);
		driver.findElement(email).sendKeys(mail);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(confirmPassword).sendKeys(pass);
		driver.findElement(registerBtn).click();
	}

	public String getSuccessMsg() {
		return driver.findElement(successMsg).getText();
	}

	public void enterFirstName(String fName) {
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(fName);
	}

	public void enterLastName(String lName) {
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(lName);
	}

	public void enterEmail(String emailAddr) {
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(emailAddr);
	}

	public void enterPassword(String pwd) {
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);
	}

	public void enterConfirmPassword(String pwd) {
		driver.findElement(confirmPassword).clear();
		driver.findElement(confirmPassword).sendKeys(pwd);
	}

	public void clickRegister() {
		driver.findElement(registerBtn).click();
	}

	// Validation message getters
	public String getFirstNameError() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameError)).getText();
	}

	public String getLastNameError() {
		return driver.findElement(lastNameError).getText();
	}

	public String getEmailError() {
		return driver.findElement(emailError).getText();
	}

	public String getPasswordError() {
		return driver.findElement(passwordError).getText();
	}

	public String getConfirmPasswordError() {
		return driver.findElement(confirmPasswordError).getText();
	}

	public String getMismatchPasswordError() {
		return driver.findElement(mismatchPasswordError).getText();
	}
}
