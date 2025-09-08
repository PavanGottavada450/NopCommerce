package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FilterPage {
    WebDriver driver;

    By sortDropdown = By.xpath("//select[@id='products-orderby']");
    By productPrices = By.cssSelector("div.prices span");

    public FilterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String option) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(option);
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(productPrices);
    }
}
