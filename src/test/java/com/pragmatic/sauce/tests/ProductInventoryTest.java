package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.LoginPage;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;
import com.pragmatic.sauce.util.ConfigReader;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class ProductInventoryTest extends BaseTest {

    @Test
    public void testProductExistsByName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"), ConfigReader.getProperty("demo.password"));
        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue(productsPage.isProductExist("Sauce Labs Backpack"));
    }

    @Test
    public void testProductDoesNotExistsByName() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"), ConfigReader.getProperty("demo.password"));
        ProductsPage productsPage = new ProductsPage(driver);
        assertFalse(productsPage.isProductExist("Sauce Labs Backpack New"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void testProductDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"), ConfigReader.getProperty("demo.password"));
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetail productDetail = productsPage.getProductDetails("Sauce Labs Backpack");
        assertEquals(productDetail.getDescription(),
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        assertEquals(productDetail.getPriceWithCurrency(), "$29.99");
    }


}
