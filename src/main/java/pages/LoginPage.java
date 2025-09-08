package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	By email = By.id("Email");
	By password = By.id("Password");
	By loginBtn = By.xpath("//button[text()='Log in']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String mail, String pass) {
		driver.findElement(email).sendKeys(mail);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginBtn).click();
	}
}
