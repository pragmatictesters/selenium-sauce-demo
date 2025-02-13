package com.pragmatic.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement ttlCart;

    @FindBy(css = "[data-test='inventory-item']")
    List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    WebElement btnCheckout;

    @FindBy(css = "[data-test='continue-shopping']")
    WebElement btnContinueShopping;

    // Define the XPath pattern as a reusable String
    private static final String PRODUCT_XPATH = "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']";


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return ttlCart.getText();
    }

    public int getProductCount() {
        return cartItems.size();
    }

    public ProductDetail getProductDetails(String productName) {
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
        WebElement inventoryItem = product.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']"));
        return getProductDetail(inventoryItem);
    }

    private ProductDetail getProductDetail(WebElement inventoryItem) {
        return new ProductDetail()
                .setName(getElementText(inventoryItem, ".//div[@data-test='inventory-item-name']"))
                .setDescription(getElementText(inventoryItem, ".//div[@data-test='inventory-item-desc']"))
                .setPriceWithCurrency(getElementText(inventoryItem, ".//div[@data-test='inventory-item-price']"));
    }

    public List<ProductDetail> getAllProductDetail(){
        return driver.findElements(By.cssSelector("[data-test='inventory-item']"))
                 .stream()
                 .map(this::getProductDetail)
                 .collect(Collectors.toList());
    }


    // Utility methods to handle element retrieval safely
    private String getElementText(WebElement parent, String xpath) {
        return Optional.ofNullable(parent.findElement(By.xpath(xpath)))
                .map(WebElement::getText)
                .orElse("");
    }


    public CartPage removeProduct(String productName) {
        // Find the product based on its name
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
        product.findElement(By.xpath(".//ancestor::div[@data-test='inventory-item']//button")).click();
        return this;
    }

    public void clickContinue(){
        btnContinueShopping.click();
    }

    public void clickCheckout(){
        btnCheckout.click();
    }

}
