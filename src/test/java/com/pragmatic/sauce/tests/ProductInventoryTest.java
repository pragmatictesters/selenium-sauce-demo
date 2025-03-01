package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.*;


public class ProductInventoryTest extends BaseTest {

    @Test
    public void testProductExistsByName() {
        ProductsPage productsPage = new ProductsPage(driver);

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
        String productName = "Sauce Labs Backpack";
        String expectedProductDescription =
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String expectedPrice = "$29.99";
        String expectedAlt = "Sauce Labs Backpack";
        Pattern regexSrcPattern = Pattern.compile(".*/static/media/sauce-backpack.*\\.jpg$");

        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetail productDetail = productsPage.getProductDetails(productName);

        softAssert.assertEquals(productDetail.getDescription(), expectedProductDescription,
                "Product description does not match for: " + productName);
        softAssert.assertEquals(productDetail.getPriceWithCurrency(), expectedPrice,
                "Price does not match for: " + productName);
        softAssert.assertEquals(productDetail.getImageAlt(), expectedAlt,
                "Image alt text does not match for: " + productName);
        softAssert.assertTrue(regexSrcPattern.matcher(productDetail.getImageSrc()).matches(),
                "Image src does not match expected regex pattern for: " + productName);

        softAssert.assertAll();
    }

    @Test(dataProvider = "productData", dataProviderClass = com.pragmatic.sauce.util.TestDataProvider.class)
    public void testProductDetails(String productName, String expectedDescription,
                                   String expectedPrice, String expectedAlt, String regexSrc) {

        Pattern regexSrcPattern = Pattern.compile(regexSrc);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetail productDetail = productsPage.getProductDetails(productName);

        softAssert.assertEquals(productDetail.getDescription(), expectedDescription,
                "Mismatch in description for: " + productName);
        softAssert.assertEquals(productDetail.getPriceWithCurrency(), expectedPrice,
                "Mismatch in price for: " + productName);
        softAssert.assertEquals(productDetail.getImageAlt(), expectedAlt,
                "Mismatch in image alt text for: " + productName);
        softAssert.assertTrue(regexSrcPattern.matcher(productDetail.getImageSrc()).matches(),
                "Mismatch in image src pattern for: " + productName);

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
