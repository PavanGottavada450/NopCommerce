package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;

@Listeners(utils.MyListener.class)
public class BrowseNavigationTest {
    WebDriver driver;
    HomePage home;
    CategoryPage category;
    ProductPage product;

    @Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
        driver.get("https://demo.nopcommerce.com/");
        home = new HomePage(driver);
        category = new CategoryPage(driver);
        product = new ProductPage(driver);
    }

    @Test(priority = 1)
    public void testNavigateComputers_Notebooks() {
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Notebooks")).click();
        Assert.assertTrue(category.getBreadcrumbText().contains("Notebooks"), "Breadcrumb incorrect!");
    }

    @Test(priority = 2)
    public void testNavigateElectronics_CellPhones() {
        driver.findElement(By.linkText("Electronics")).click();
        driver.findElement(By.linkText("Cell phones")).click();
        Assert.assertTrue(category.getBreadcrumbText().contains("Cell phones"), "Breadcrumb incorrect!");
    }

    @Test(priority = 3)
    public void testNavigateApparel_Shoes() {
        driver.findElement(By.linkText("Apparel")).click();
        driver.findElement(By.linkText("Shoes")).click();
        Assert.assertTrue(category.getBreadcrumbText().contains("Shoes"), "Breadcrumb incorrect!");
    }

    @Test(priority = 4)
    public void testProductImageRedirection() {
        driver.findElement(By.linkText("Computers")).click();
        driver.findElement(By.linkText("Notebooks")).click();
        category.clickProductByImage("Apple MacBook Pro");   // updated name
        Assert.assertTrue(product.getProductTitle().contains("Apple MacBook Pro"), "Product page not opened!");
    }

    @Test(priority = 5)
    public void testProductTitleRedirection() {
        driver.findElement(By.linkText("Electronics")).click();
        driver.findElement(By.linkText("Cell phones")).click();
        category.clickProductByTitle("Nokia Lumia 1020");  // updated to existing product
        Assert.assertTrue(product.getProductTitle().contains("Nokia Lumia"), "Product page not opened!");
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
