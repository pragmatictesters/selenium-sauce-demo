package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class ProductInventoryTest extends BaseTest {

    @Test
    public void testProductExistsByName() {
        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue(productsPage.isProductExist("Sauce Labs Backpack"));
    }

    @Test
    public void testProductDoesNotExistsByName() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        ProductsPage productsPage = new ProductsPage(driver);
        assertFalse(productsPage.isProductExist("Sauce Labs Backpack New"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void testProductDetails() {
        String productName="Sauce Labs Backpack";
        String expectedProductDescription = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String expectedPrice = "$29.99";
        String expectedAlt = "Sauce Labs Backpack";
        String regexSrc = "/static/media/sauce-backpack.*.jpg$";

        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetail productDetail = productsPage.getProductDetails(productName);

        softAssert.assertEquals(productDetail.getDescription(), expectedProductDescription);
        softAssert.assertEquals(productDetail.getPriceWithCurrency(), expectedPrice);
        softAssert.assertEquals(productDetail.getImageAlt(), expectedAlt);
        softAssert.assertTrue(productDetail.getImageSrc().matches(regexSrc));
        softAssert.assertAll();
    }

    @Test
    public void testChangeOfButtonsInInventoryPage() {
        String productName="Sauce Labs Backpack";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickButton(productName, "Add to cart");
        assertEquals(productsPage.getButtonCaption(productName), "Remove");
        productsPage.clickButton(productName, "Remove");
        assertEquals(productsPage.getButtonCaption(productName), "Add to cart");
    }

    @Test
    public void testProductCountInProductPage() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<ProductDetail> productDetail = productsPage.getAllProductDetails();
        assertEquals(productDetail.size(), 6);
    }
}
