package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductSortingTest extends BaseTest {


    @Test
    public void testDefaultSortingA2Z() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<String> actualProductNames = productsPage.getAllProductNames();
        // Create a sorted copy of the product names
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames); // Sort alphabetically
        assertEquals(actualProductNames, expectedProductNames, "Products are not sorted  A to Z by default!");
    }

    @Test
    public void testSortingA2Z() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceLowToHigh();
        productsPage.sortA2Z();

        List<String> actualProductNames = productsPage.getAllProductNames();
        // Create a sorted copy of the product names
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames); // Sort alphabetically
        assertEquals(actualProductNames, expectedProductNames, "Products are not sorted  A to Z by default!");
    }




    @Test
    public void testSortingZ2A() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortZ2A();
        List<String> actualProductNames = productsPage.getAllProductNames();
        // Create a sorted copy of the product names
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        expectedProductNames.sort(Collections.reverseOrder()); // Sort alphabetically descending order
        assertEquals(actualProductNames, expectedProductNames, "Products are not sorted Z to A!");
    }

    @Test
    public void testSortingPriceLowToHigh() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceLowToHigh();
        List<Double> actualPriceList = productsPage.getAllProductPricesAsNumbers();
        // Create a sorted copy of the product names
        List<Double> expectedPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedPriceList); // Sort
        assertEquals(actualPriceList, expectedPriceList, "Products are not sorted from prices low to high");
    }

    @Test
    public void testMaximumProductPricesAfterSortingLowToHigh(){
        String expectedProductName = "Sauce Labs Fleece Jacket";
        String expectedPrice = "$49.99";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceLowToHigh();
        List<ProductDetail> productDetail = productsPage.getAllProductDetails();
        ProductDetail productDetail1 = productDetail.getLast();
        assertEquals(productDetail1.getName(), expectedProductName);
        assertEquals(productDetail1.getPriceWithCurrency(), expectedPrice);
    }


    @Test
    public void testMinimumProductPricesAfterSortingLowToHigh(){
        String expectedProductName = "Sauce Labs Onesie";
        String expectedPrice = "$7.99";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceLowToHigh();
        List<ProductDetail> productDetail = productsPage.getAllProductDetails();
        ProductDetail productDetail1 = productDetail.getFirst();
        assertEquals(productDetail1.getName(), expectedProductName);
        assertEquals(productDetail1.getPriceWithCurrency(), expectedPrice);
    }




    @Test
    public void testSortingPriceHighToLow() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceHighToLow();
        List<Double> actualPriceList = productsPage.getAllProductPricesAsNumbers();
        // Create a sorted copy of the product names
        List<Double> expectedPriceList = new ArrayList<>(actualPriceList);
        expectedPriceList.sort(Collections.reverseOrder()); // Sort
        assertEquals(actualPriceList, expectedPriceList, "Products are not sorted from prices high to low");
    }

    @Test
    public void testMaximumProductPricesAfterSortingHighToLow(){
        String expectedProductName = "Sauce Labs Fleece Jacket";
        String expectedPrice = "$49.99";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceHighToLow();
        List<ProductDetail> productDetail = productsPage.getAllProductDetails();
        ProductDetail productDetail1 = productDetail.getFirst();
        assertEquals(productDetail1.getName(), expectedProductName);
        assertEquals(productDetail1.getPriceWithCurrency(), expectedPrice);
    }


    @Test
    public void testMinimumProductPricesAfterSortingHighToLow(){
        String expectedProductName = "Sauce Labs Onesie";
        String expectedPrice = "$7.99";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceHighToLow();
        List<ProductDetail> productDetail = productsPage.getAllProductDetails();
        ProductDetail productDetail1 = productDetail.getLast();
        assertEquals(productDetail1.getName(), expectedProductName);
        assertEquals(productDetail1.getPriceWithCurrency(), expectedPrice);
    }


}
