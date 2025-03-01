package com.pragmatic.sauce.pages;

import com.pragmatic.sauce.util.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.pragmatic.sauce.util.LogManager.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductsPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement ttlProducts;

    @FindBy(css = "[data-test='product-sort-container']")
    WebElement lstSort;


    // Define the XPath pattern as a reusable String
    private static final String PRODUCT_XPATH = "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']";

    // Define the XPath to find all products (adjust this as needed based on your page structure)
    private static final String ALL_PRODUCTS_XPATH = "//div[@data-test='inventory-item']";


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        info("Product page is initialise");
    }

    public String getTitle() {
        return ttlProducts.getText();
    }

    public boolean isProductExist(String productName) {
        try {
            driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
            info("Product {} exists in the inventory page", productName);
            return true;
        } catch (NoSuchElementException e) {
            warn("Product {} does not exist in the inventory page", productName);
            return false;
        }
    }


//    This code is refactored
//    public ProductDetail getProductDetails(String productName) {
//        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
//        WebElement inventoryItem = product.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']"));
//        ProductDetail productDetail = new ProductDetail();
//        productDetail.setDescription(inventoryItem.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText());
//        productDetail.setName(productName);
//        productDetail.setPriceWithCurrency(inventoryItem.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText());
//
//        // Extract image alt and src
//        WebElement productImage = inventoryItem.findElement(By.xpath(".//img[starts-with(@data-test, 'inventory-item-')]"));
//        productDetail.setImageAlt(productImage.getDomAttribute("alt"));
//        productDetail.setImageSrc(productImage.getDomAttribute("src"));
//
//        return productDetail;
//    }


    public ProductDetail getProductDetails(String productName) {
        WebElement productElement = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
        WebElement inventoryItem = productElement.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']"));

        // Extract product details using a streamlined approach
        return new ProductDetail()
                .setName(productName)
                .setDescription(getElementText(inventoryItem, ".//div[@data-test='inventory-item-desc']"))
                .setPriceWithCurrency(getElementText(inventoryItem, ".//div[@data-test='inventory-item-price']"))
                .setImageAlt(getElementAttribute(inventoryItem, ".//img[starts-with(@data-test, 'inventory-item-')]", "alt"))
                .setImageSrc(getElementAttribute(inventoryItem, ".//img[starts-with(@data-test, 'inventory-item-')]", "src"));
    }

    // Utility methods to handle element retrieval safely
    private String getElementText(WebElement parent, String xpath) {
        return Optional.ofNullable(parent.findElement(By.xpath(xpath)))
                .map(WebElement::getText)
                .orElse("");
    }

    private String getElementAttribute(WebElement parent, String xpath, String attribute) {
        return Optional.ofNullable(parent.findElement(By.xpath(xpath)))
                .map(element -> element.getDomAttribute(attribute))
                .orElse("");
    }


    public ProductsPage clickAllAddToCartButtons() {
        driver.findElements(By.xpath("//button[text()='Add to cart']")).forEach(WebElement::click);
        return this;

    }



//    This code is refactored
//    public List<ProductDetail> getAllProductDetails() {
//        // Find all products on the page
//        List<WebElement> products = driver.findElements(By.xpath(ALL_PRODUCTS_XPATH));
//
//        // List to store product details
//        List<ProductDetail> productDetailsList = new ArrayList<>();
//
//        // Loop through each product and extract the details
//        for (WebElement product : products) {
//            String productName = product.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
//            ProductDetail productDetail = getProductDetails(productName);
//            productDetailsList.add(productDetail);
//        }
//        return productDetailsList;
//    }

    public List<ProductDetail> getAllProductDetails() {
        return driver.findElements(By.xpath("//div[@data-test='inventory-item']"))
                .stream()
                .map(this::extractProductDetails)
                .collect(Collectors.toList());
    }

    private ProductDetail extractProductDetails(WebElement inventoryItem) {
        return new ProductDetail()
                .setName(getElementText(inventoryItem, ".//div[@data-test='inventory-item-name']"))
                .setDescription(getElementText(inventoryItem, ".//div[@data-test='inventory-item-desc']"))
                .setPriceWithCurrency(getElementText(inventoryItem, ".//div[@data-test='inventory-item-price']"))
                .setImageAlt(getElementAttribute(inventoryItem, ".//img[starts-with(@data-test, 'inventory-item-')]", "alt"))
                .setImageSrc(getElementAttribute(inventoryItem, ".//img[starts-with(@data-test, 'inventory-item-')]", "src"));
    }

    public String getButtonCaption(String productName) {
        try {
            WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));
            WebElement button = product.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']//button"));
            return button.getText().trim();
        } catch (NoSuchElementException e) {
            error("Product or Button is not found for the product: " + productName);
            return "";
        }
    }


    public ProductsPage clickButton(String productName, String buttonCaption) {
        // Find the product based on its name
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));

        // Locate the button for this product
        WebElement button = product.findElement(By.xpath(".//ancestor::div[@data-test='inventory-item']//button"));

        // Ensure the button caption matches the required one before clicking
        if (button.getText().equals(buttonCaption)) {
            button.click();
        } else {
            error("Button caption mismatch for product: " + productName);
        }

        return this;
    }

    public ProductsPage addProduct(String productName) {
        this.clickButton(productName, "Add to cart");
        return this;
    }

    public ProductsPage removeProduct(String productName) {
        this.clickButton(productName, "Remove");
        return this;
    }


    public List<String> getAllProductNamesDeprecated() {
        List<ProductDetail> allProducts = getAllProductDetails();
        List<String> allProductNames = new ArrayList<>();

        for (ProductDetail productDetail : allProducts) {
            allProductNames.add(productDetail.getName());
        }
        return allProductNames;
    }

    public List<String> getAllProductNames() {
        return getAllProductDetails().stream()
                .map(ProductDetail::getName)
                .collect(Collectors.toList());
    }

    public List<Double> getAllProductPricesAsNumbers() {
        return getAllProductDetails().stream()
                .map(product -> Double.parseDouble(product.getPriceWithCurrency().replace("$", ""))) // Remove `$` and parse
                .collect(Collectors.toList());
    }

    public ProductsPage sortZ2A() {
        return sort("Name (Z to A)");
    }

    public ProductsPage sortPriceLowToHigh() {
        return sort("Price (low to high)");
    }

    public ProductsPage sortPriceHighToLow() {
        return sort("Price (high to low)");
    }

    public ProductsPage sortA2Z() {
        return sort("Name (A to Z)");
    }

    private ProductsPage sort(String option) {
        Select selSort = new Select(lstSort);
        selSort.selectByVisibleText(option);
        return this;
    }
}
