package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public By cartTable = By.cssSelector("table.cart");
    By qtyInput = By.cssSelector("input.qty-input");
    By updateCartBtn = By.name("updatecart");
    By removeCheckbox = By.cssSelector("td[class='remove-from-cart']");
    By checkoutBtn = By.xpath("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartTable).isDisplayed();
    }

    public void updateQuantity(String qty) {
        driver.findElement(qtyInput).clear();
        driver.findElement(qtyInput).sendKeys(qty);
        driver.findElement(updateCartBtn).click();
    }

    public void removeItem() {
        driver.findElement(removeCheckbox).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}
