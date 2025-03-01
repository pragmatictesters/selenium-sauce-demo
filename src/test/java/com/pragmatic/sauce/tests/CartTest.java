package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.CartIconPage;
import com.pragmatic.sauce.pages.CartPage;
import com.pragmatic.sauce.pages.ProductDetail;
import com.pragmatic.sauce.pages.ProductsPage;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
        List<String> productNamesToAdd = Arrays.asList("Sauce Labs Bike Light", "Sauce Labs Backpack","Sauce Labs Onesie");
        ProductsPage productsPage = new ProductsPage(driver);

        // Add multiple products by chaining the addProduct() method
        for (String product : productNamesToAdd) {
            productsPage = productsPage.addProduct(product);
        }
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        List<String> actualProductNameListInCart = cart.getAllProductDetails()
                .stream()
                .map(ProductDetail::getName)
                .toList();
        assertEquals(actualProductNameListInCart,productNamesToAdd);
        assertEquals(cart.getProductCount(), productNamesToAdd.size());
    }


    @Test
    public void testMultipleProductDetails() {
        List<String> productNameList = Arrays.asList("Sauce Labs Bike Light", "Sauce Labs Backpack","Sauce Labs Onesie");

        ProductsPage productsPage = new ProductsPage(driver);
        // Add multiple products by chaining the addProduct() method
//        for (String product : productNameList) {
//            productsPage = productsPage.addProduct(product);
//        }
        productNameList.forEach(productsPage::addProduct);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        List<String> actualProductNamesList = cart.getAllProductDetails().stream()
                .map(ProductDetail::getName)
                .toList();
        assertEquals(productNameList, actualProductNamesList);
    }



    @Test
    public void testAllProductsAddedToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<String> allProductNames = productsPage.getAllProductNames();

        // Add all available products
//        for (String product : allProductNames) {
//            productsPage = productsPage.addProduct(product);
//        }

        allProductNames.forEach(productsPage::addProduct);

        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        List<String> actualProductList = cart.getAllProductDetails().stream().map(ProductDetail::getName).toList();
        assertEquals(actualProductList, allProductNames);
        assertEquals(cart.getProductCount(), allProductNames.size());
    }

    @Test
    public void testRandomProductAddition() {
        ProductsPage productsPage = new ProductsPage(driver);
        List<String> allProductNames = productsPage.getAllProductNames();

        // Randomly pick 2 products
        Collections.shuffle(allProductNames);
        List<String> selectedProductNames = allProductNames.subList(0, 2);

        // Add randomly selected products
        //for (String product : selectedProducts) {
        //    productsPage = productsPage.addProduct(product);
        //}
        //Use of method reference
        selectedProductNames.forEach(productsPage::addProduct);

        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        assertEquals(cart.getProductCount(), selectedProductNames.size());
    }

    @Test
    public void testProductDetails() {
        String productName = "Sauce Labs Bike Light";
        String expectedDescription = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. " +
                "Water-resistant with 3 lighting modes, 1 AAA battery included.";
        String expectedPrice = "$9.99";
        BigDecimal expectedPriceInDecimal = new BigDecimal("9.99").setScale(2, RoundingMode.HALF_UP);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        ProductDetail product = cart.getProductDetails(productName);

        assertEquals(product.getDescription(), expectedDescription);
        assertEquals(product.getPriceWithCurrency(), expectedPrice);
        assertEquals(product.getPrice(), expectedPriceInDecimal);
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

    @Test
    public void testRemoveMultipleProduct() {
        List<String> productNameList = Arrays.asList("Sauce Labs Bike Light", "Sauce Labs Backpack","Sauce Labs Onesie");
        ProductsPage productsPage = new ProductsPage(driver);
        productNameList.forEach(productsPage::addProduct);

        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);

        // Remove products one by one and verify the cart count
        for (int i = 0; i < productNameList.size(); i++) {
            cart.removeProduct(productNameList.get(i));
            int expectedCount = productNameList.size() - (i + 1);
            assertEquals(cartIcon.getCartItemCount(), expectedCount,
                    "Cart count mismatch after removing: " + productNameList.get(i));
        }
    }

    @Test
    public void testContinueShopping() {
        String productName = "Sauce Labs Bike Light";
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProduct(productName);
        CartIconPage cartIcon = new CartIconPage(driver);
        cartIcon.clickCartIcon();
        CartPage cart = new CartPage(driver);
        cart.clickContinue();
        productsPage = new ProductsPage(driver);
        assertEquals(productsPage.getTitle(), "Products");
    }

}
