package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.CartIconPage;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductCountTest extends BaseTest {

    @Test
    public void testInitialProductCount() {
        CartIconPage cartIconPage = new CartIconPage(driver);
        assertEquals(cartIconPage.getCartItemCount(), 0);
    }


    @Test
    public void testProductCountAfterAddingAProduct() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickButton(productName, "Add to cart");

        CartIconPage cartIconPage = new CartIconPage(driver);
        assertEquals(cartIconPage.getCartItemCount(), 1);

        productsPage.clickButton(productName, "Remove");
        assertEquals(cartIconPage.getCartItemCount(), 0);
    }


}
