package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.*;
import net.datafaker.Faker;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CheckoutCompleteTest extends BaseTest {

    @Test
    public void testInformationInCheckoutCompletePage() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();
        fillCheckoutInformation();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        assertEquals(completePage.getTitle(), "Checkout: Complete!");
        assertEquals(completePage.getCompleteHeader(), "Thank you for your order!");
        assertEquals(completePage.getCompleteMessage(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private void fillCheckoutInformation() {
        Faker faker = new Faker();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.typeFirstname(faker.name().firstName());
        checkoutPage.typeLastname(faker.name().lastName());
        checkoutPage.typePostalCode(faker.address().postcode());
        checkoutPage.clickContinue();
    }


}
