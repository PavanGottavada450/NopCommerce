package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage {
    WebDriver driver;

    By searchBox = By.id("small-searchterms");
    By searchButton = By.cssSelector("button.search-box-button");
    By productTitles = By.cssSelector("h2.product-title a");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    public boolean verifyResultsContain(String titleName) {
        List<WebElement> results = driver.findElements(productTitles);
        if (results.isEmpty()) return false;
        for (WebElement result : results) {
            if (!result.getText().toLowerCase().contains(titleName.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public List<WebElement> getResults() {
        return driver.findElements(productTitles);
    }
}
