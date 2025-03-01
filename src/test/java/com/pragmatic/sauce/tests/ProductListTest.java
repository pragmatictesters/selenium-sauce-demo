package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.CartIconPage;
import com.pragmatic.sauce.pages.CartPage;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.util.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ProductListTest extends BaseTest {


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "productData")
    public void testAllProducts(String expectedProductName, String expectedDescription, String expectedPrice, String expectedAlt, String expectedSrc) {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));


        long productCountWithGivenPriceAndName = inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']"))
                        .getText().equals(expectedProductName))
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                        .getText().equals(expectedPrice)).count();
        Assert.assertEquals(productCountWithGivenPriceAndName, 1, "Product price does not match");
    }






    @Test
    public void testStreamForEachExample() {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));
        inventoryList.stream().forEach(ProductListTest::clickButton);
        CartIconPage iconPage = new CartIconPage(driver);
        Assert.assertEquals(iconPage.getCartItemCount(), 6);
    }


     @Test
    public void testProductNames() {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));
         List<String> list = inventoryList.stream()
                 .map(inventory -> inventory.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                 .toList();
         List<ProductDetail> productDetails = new ArrayList<>();

         List<String> productNames = inventoryList.stream()
                 .map(inventory -> inventory.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                 .toList();
         for (String  productName : productNames) {
             ProductDetail productDetail = new ProductDetail();
             productDetail.setName(productName);
             productDetails.add(productDetail);
         }



     }



    private static void clickButton(WebElement webElement) {
        webElement.findElement(By.tagName("button")).click();
    }


    @Test
    public void testStreamFilterExample() {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));
        long productsStartWithSauce = inventoryList.stream().filter(
                        webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']"))
                                .getText().startsWith("Sauce"))
                .count();
        Assert.assertEquals(productsStartWithSauce, 5);
    }

    @Test
    public void testStreamDisplayDetailsOfEachItem() {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));

        inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']"))
                        .getText().startsWith("Sauce"))
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                .forEach(System.out::println);
    }


    @Test
    public void testStreamSortingItemsInDescendingOrder() {
        List<WebElement> inventoryList = driver.findElements(By.cssSelector("[data-test='inventory-item']"));
        inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']"))
                        .getText().startsWith("Sauce"))
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);
    }


}
