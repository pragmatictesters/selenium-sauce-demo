package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortingTest extends BaseTest {

    String[][] products = {
            {"Sauce Labs Backpack", "$29.99"},
            {"Sauce Labs Bike Light", "$9.99"},
            {"Sauce Labs Bolt T-Shirt", "$15.99"},
            {"Sauce Labs Fleece Jacket", "$49.99"},
            {"Sauce Labs Onesie", "$7.99"},
            {"Test.allTheThings() T-Shirt (Red)", "$15.99"}
    };

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
    public void testSortingZ2A() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortZ2A();
        List<String> actualProductNames = productsPage.getAllProductNames();
        // Create a sorted copy of the product names
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder()); // Sort alphabetically descending order
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
    public void testSortingPriceHighToLow() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortPriceHighToLow();
         List<Double> actualPriceList = productsPage.getAllProductPricesAsNumbers();
        // Create a sorted copy of the product names
        List<Double> expectedPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedPriceList, Collections.reverseOrder()); // Sort
        assertEquals(actualPriceList, expectedPriceList, "Products are not sorted from prices high to low");
    }




}
