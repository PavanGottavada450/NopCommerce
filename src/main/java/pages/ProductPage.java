package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtils;

public class ProductPage {
    WebDriver driver;

    By productTitle = By.cssSelector("div.product-name h1");
    By productDescription = By.cssSelector("div.full-description");
    By productPrice = By.xpath("//span[@id='price-value-4']");

    By quantityInput = By.id("product_enteredQuantity_1"); // may vary per product
    By quantityInputGiftCard = By.id("product_enteredQuantity_45");
    By addToCartBtn = By.id("add-to-cart-button-1"); // IDs differ per product
    By addToCartBtnGF = By.id("add-to-cart-button-45");
    By successMsg = By.xpath("//p[@class='content']");
    By cartQty = By.cssSelector("span.cart-qty");
   
    // Configurable option
    By ramDropdown = By.id("product_attribute_2");  // RAM dropdown
    By hddRadio320 = By.id("product_attribute_3_6"); // 320 GB HDD
    By hddRadio400 = By.id("product_attribute_3_7"); // 400 GB HDD

    By addToWishlistBtn = By.id("add-to-wishlist-button-4");
    By addToCompareBtn = By.cssSelector("div.compare-products input");
    
 // Notification bar message (wishlist/compare/cart all appear here)
    By notificationBar = By.cssSelector("div.bar-notification.success");
    
 // Gift card fields
    By recipientName = By.id("giftcard_45_RecipientName");
    By recipientEmail = By.id("giftcard_45_RecipientEmail");
    By senderName = By.id("giftcard_45_SenderName");
    By senderEmail = By.id("giftcard_45_SenderEmail");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void setQuantity(String qty) {
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(qty);
    }
    
    public void setQuantityGF(String qty) {
        driver.findElement(quantityInputGiftCard).clear();
        driver.findElement(quantityInputGiftCard).sendKeys(qty);
    }

    public void selectRAM(String ramOption) {
        Select ramSelect = new Select(driver.findElement(ramDropdown));
        ramSelect.selectByVisibleText(ramOption);
    }

    public void selectHDD(String hddSize) {
        if (hddSize.equals("320 GB")) {
            driver.findElement(hddRadio320).click();
        } else if (hddSize.equals("400 GB")) {
            driver.findElement(hddRadio400).click();
        }
    }
    
    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();
    }
    
    public void clickAddToCartGF() {
        driver.findElement(addToCartBtnGF).click();
    }

    public void clickAddToWishlist() {
        driver.findElement(addToWishlistBtn).click();
    }

    public void clickAddToCompare() {
        driver.findElement(addToCompareBtn).click();
    }

    public String getCartQty() {
        return driver.findElement(cartQty).getText();
    }

    public String getSuccessMessage() {
        By successMsg = By.cssSelector("p.content");
        return WaitUtils.waitForVisibility(driver, successMsg, 10).getText();
    }

    public String getNotificationMessage() {
        By notificationBar = By.cssSelector("div.bar-notification.success");
        return WaitUtils.waitForVisibility(driver, notificationBar, 10).getText();
    }
    
    public void fillGiftCardDetails(String recName, String recEmail, String sendName, String sendEmail) {
        driver.findElement(recipientName).clear();
        driver.findElement(recipientName).sendKeys(recName);
        driver.findElement(recipientEmail).clear();
        driver.findElement(recipientEmail).sendKeys(recEmail);
        driver.findElement(senderName).clear();
        driver.findElement(senderName).sendKeys(sendName);
        driver.findElement(senderEmail).clear();
        driver.findElement(senderEmail).sendKeys(sendEmail);
    }
    
}
