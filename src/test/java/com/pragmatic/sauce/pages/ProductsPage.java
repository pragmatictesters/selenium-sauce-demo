package com.pragmatic.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.pragmatic.sauce.util.LogManager.*;

import java.util.ArrayList;
import java.util.List;
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
        WebElement inventoryItem = product.findElement(By.xpath("./ancestor::div[@data-test='inventory-item']"));
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(inventoryItem.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText());
        productDetail.setName(productName);
        productDetail.setPriceWithCurrency(inventoryItem.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText());

        // Extract image alt and src
        WebElement productImage = inventoryItem.findElement(By.xpath(".//img[starts-with(@data-test, 'inventory-item-')]"));
        productDetail.setImageAlt(productImage.getDomAttribute("alt"));
        productDetail.setImageSrc(productImage.getDomAttribute("src"));

        return productDetail;
    }


    // Method to extract details of all products into an array
    public List<ProductDetail> getAllProductDetails() {
        // Find all products on the page
        List<WebElement> products = driver.findElements(By.xpath(ALL_PRODUCTS_XPATH));

        // List to store product details
        List<ProductDetail> productDetailsList = new ArrayList<>();

        // Loop through each product and extract the details
        for (WebElement product : products) {
            String productName = product.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
            ProductDetail productDetail = getProductDetails(productName);
            productDetailsList.add(productDetail);
        }
        return productDetailsList;
    }


    public String getButtonCaption(String productName) {
        // Find the product based on its name
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_XPATH, productName)));

        // Locate the button for this product
        WebElement button = product.findElement(By.xpath(".//ancestor::div[@data-test='inventory-item']//button"));
        // Return the caption (button text)
        return button.getText();
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
            System.out.println("Button caption mismatch for product: " + productName);
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

        for (ProductDetail productDetail: allProducts) {
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

    public ProductsPage sortZ2A(){
        Select selSort =  new Select (lstSort);
        selSort.selectByVisibleText("Name (Z to A)");
        return this;
    }

    public ProductsPage sortPriceLowToHigh() {
        Select selSort =  new Select (lstSort);
        selSort.selectByVisibleText("Price (low to high)");
        return this;
    }

    public ProductsPage sortPriceHighToLow() {
        Select selSort =  new Select (lstSort);
        selSort.selectByVisibleText("Price (high to low)");
        return this;
    }
}
