package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;
import utils.WaitUtils;

@Listeners(utils.MyListener.class)
public class ProductCartTest {
    WebDriver driver;
    ProductPage product;
    CartPage cart;

    @Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
        driver.get("https://demo.nopcommerce.com/");
        product = new ProductPage(driver);
        cart = new CartPage(driver);
    }

    @Test(priority = 1)
    public void testBuildYourOwnComputer_AddToCart() {
        driver.findElement(By.linkText("Build your own computer")).click();
        Assert.assertTrue(product.getProductTitle().contains("Build your own computer"));
        Assert.assertTrue(product.getProductDescription().length() > 0);

     // Select configurable options
        product.selectRAM("8GB [+$60.00]");
        product.selectHDD("400 GB");
        
        product.setQuantity("2");
        product.clickAddToCart();
        
     // Wait for success message
        String successMessage = product.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("The product has been added"));
        
        Assert.assertTrue(product.getCartQty().contains("2"));
    }

    @Test(priority = 2)
    public void testAppleMacBookPro_Wishlist() {
        driver.findElement(By.linkText("Apple MacBook Pro")).click();
        Assert.assertTrue(product.getProductPrice().contains("1,800"));

        product.clickAddToWishlist();
        String wishlistMsg = product.getNotificationMessage();
        Assert.assertTrue(wishlistMsg.contains("The product has been added"), "Wishlist message not found!");
    }
    
    @Test(priority = 3)
    public void testVirtualGiftCard_AddMultiple() {
        driver.findElement(By.linkText("$25 Virtual Gift Card")).click();

        // Fill required fields
        product.fillGiftCardDetails(
            "John Doe", "johndoe@example.com",
            "Alice", "alice@example.com"
        );

        // Set multiple quantities
        product.setQuantityGF("5");
        product.clickAddToCartGF();

        // Validate success + cart qty
        Assert.assertTrue(product.getSuccessMessage().contains("The product has been added"));
        Assert.assertTrue(product.getCartQty().contains("5"));
    }


    @Test(priority = 4)
    public void testShoppingCart_Modify_Remove_Checkout() {
    	
    	driver.findElement(By.linkText("Build your own computer")).click();
        Assert.assertTrue(product.getProductTitle().contains("Build your own computer"));
        Assert.assertTrue(product.getProductDescription().length() > 0);

     // Select configurable options
        product.selectRAM("8GB [+$60.00]");
        product.selectHDD("400 GB");
        
        product.setQuantity("2");
        product.clickAddToCart();
        
        // ✅ Wait until green success notification disappears
        WaitUtils.waitForInvisibility(driver, By.cssSelector("div.bar-notification.success"), 10);

//        -----------------------------------------------------------
        
        driver.findElement(By.className("cart-label")).click();
        WaitUtils.waitForVisibility(driver, By.cssSelector("table.cart"), 10);
        Assert.assertTrue(cart.isCartDisplayed());

        cart.removeItem(); // remove all
        
        // Validate empty cart message
        Assert.assertTrue(driver.getPageSource().contains("Your Shopping Cart is empty!"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
