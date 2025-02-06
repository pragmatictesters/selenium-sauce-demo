package com.pragmatic.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.pragmatic.sauce.util.Log.*;

import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    @FindBy(css = "span[data-test='title']")
    WebElement ttlProducts;

    // Define the XPath pattern as a reusable String
    private static final String PRODUCT_XPATH = "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']";


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return ttlProducts.getText();
    }

    public boolean isProductExist(String productName) {
        List<WebElement> products = driver.findElements(By.xpath(String.format(PRODUCT_XPATH, productName)));
        if (!products.isEmpty()) {
            info("Product {} exists in the inventory page", productName);
            return true;
        } else {
            warn("Product {} does not exist in the inventory page", productName);
            return false;
        }
    }

    public ProductDetail getProductDetails(String productName) {
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
        WebElement inventoryItem = product.findElement(By.xpath(".//ancestor::div[@data-test='inventory-item']"));
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(inventoryItem.findElement(By.xpath("//div[@data-test='inventory-item-desc']")).getText());
        productDetail.setName(productName);
        productDetail.setPriceWithCurrency(inventoryItem.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText());
        return productDetail;
    }


}
