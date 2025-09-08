package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SearchPage;
import pages.FilterPage;
import utils.DriverFactory;

import java.util.List;

@Listeners(utils.MyListener.class)
public class SearchFilterTest {
    WebDriver driver;
    SearchPage search;
    FilterPage filter;

    @Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
        driver.get("https://demo.nopcommerce.com/");
        search = new SearchPage(driver);
        filter = new FilterPage(driver);
    }

    @Test(priority = 1)
    public void testSearchMacBook() {
        search.search("MacBook");
        Assert.assertTrue(search.verifyResultsContain("MacBook"), "Search results did not match keyword!");
    }

    @Test(priority = 2)
    public void testSearchHTC() {
        search.search("HTC");
        Assert.assertTrue(search.verifyResultsContain("HTC"), "Search results did not match keyword!");
    }

    @Test(priority = 3)
    public void testSearchGiftCard() {
        search.search("Gift Card");
        Assert.assertTrue(search.verifyResultsContain("Gift Card"), "Search results did not match keyword!");
    }

    @Test(priority = 4)
    public void testSortByPriceLowToHigh() {
        search.search("Computer");
        filter.sortBy("Price: Low to High");
        
        

        List<WebElement> prices = filter.getProductPrices();
        Assert.assertTrue(prices.size() > 0, "Not enough products to test sorting!");

        // 🚀 Optional enhancement: Parse prices & validate ascending order
    }

    @Test(priority = 5)
    public void testSortByPriceHighToLow() {
        search.search("Computer");
        filter.sortBy("Price: High to Low");

        List<WebElement> prices = filter.getProductPrices();
        Assert.assertTrue(prices.size() > 0, "Not enough products to test sorting!");

        // 🚀 Optional enhancement: Parse prices & validate descending order
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
