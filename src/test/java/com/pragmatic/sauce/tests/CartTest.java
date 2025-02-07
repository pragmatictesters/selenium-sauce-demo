package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.CartIconPage;
import com.pragmatic.sauce.pages.CartPage;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void testPageTitle() {
        String productName = "Sauce Labs Backpack";
        String expectedTitle = "Your Cart";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        assertEquals(cart.getTitle(), expectedTitle);
    }

    @Test
    public void testProductCountSingle() {
        String productName = "Sauce Labs Bike Light";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        assertEquals(cart.getProductCount(), 1);
    }

    @Test
    public void testMultipleProductCount() {
        List<String> products = Arrays.asList("Sauce Labs Bike Light", "Sauce Labs Backpack");

        ProductsPage productsPage = new ProductsPage(driver);

        // Add multiple products by chaining the addProduct() method
        for (String product : products) {
            productsPage = productsPage.addProduct(product);
        }

        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        assertEquals(cart.getProductCount(), products.size());
    }

    @Test
    public void testAllProductsAddedToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<String> allProducts = productsPage.getAllProductNames();

        // Add all available products
        for (String product : allProducts) {
            productsPage = productsPage.addProduct(product);
        }
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        assertEquals(cart.getProductCount(), allProducts.size());
    }

    @Test
    public void testRandomProductAddition() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<String> allProducts = productsPage.getAllProductNames();

        // Randomly pick 2 products
        Collections.shuffle(allProducts);
        List<String> selectedProducts = allProducts.subList(0, 2);

        // Add randomly selected products
        for (String product : selectedProducts) {
            productsPage = productsPage.addProduct(product);
        }

        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        assertEquals(cart.getProductCount(), selectedProducts.size());
    }

    @Test
    public void testProductDetails() {
        String productName = "Sauce Labs Bike Light";
        String expectedDescription = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. " +
                "Water-resistant with 3 lighting modes, 1 AAA battery included.";
        String expectedPrice = "$9.99";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        ProductDetail product = cart.getProductDetails(productName);

        assertEquals(product.getDescription(), expectedDescription);
        assertEquals(product.getPriceWithCurrency(), expectedPrice);
    }

    @Test
    public void testRemoveProduct() {
        String productName = "Sauce Labs Bike Light";

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        cart.removeProduct(productName);
        cartIcon = new CartIconPage(driver);
        assertEquals(cartIcon.getCartItemCount(), 0);
    }
}
