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
        checkoutPage.typeFirstname("Janesh");
        checkoutPage.typeLastname("Kodikara");
        checkoutPage.typePostalCode("11566");
        checkoutPage.clickContinue();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }
}
