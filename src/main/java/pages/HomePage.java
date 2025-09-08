package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;
	By registerLink = By.className("ico-register");
	public By loginLink = By.className("ico-login");
	public By logoutLink = By.className("ico-logout");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegister() {
		driver.findElement(registerLink).click();
	}

	public void clickLogin() {
		driver.findElement(loginLink).click();
	}

	public void clickLogout() {
		driver.findElement(logoutLink).click();
	}
}
