package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage {
    WebDriver driver;

    By breadcrumb = By.cssSelector("div.breadcrumb");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBreadcrumbText() {
        return driver.findElement(breadcrumb).getText();
    }

    public void clickProductByImage(String productPartialName) {
        driver.findElement(By.xpath(
        		"//div[@class='picture']//img[@title='Show details for " + productPartialName + "']"
        )).click();
    }

    public void clickProductByTitle(String productName) {
        driver.findElement(By.xpath(
            "//h2[@class='product-title']//a[normalize-space()='" + productName + "']"
        )).click();
    }

}
