package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckoutTest extends BaseTest {


    @Test
    public void testCheckoutWithValidInformation() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInformation("Janesh", "Kodikara","11234");
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }

    @Test
    public void testSubmitCheckoutFormWithoutInformation() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getError(), "Error: First Name is required");

        checkoutPage.typeFirstname("Janesh");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getError(), "Error: Last Name is required");

        checkoutPage.typeLastname("Kodikara");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getError(), "Error: Postal Code is required");
    }

}
