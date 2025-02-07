package com.pragmatic.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CartIconPage {

    private final WebDriver driver;

    @FindBy(css="#shopping_cart_container>a")
    WebElement lnkCart;

    // Locator for the cart badge (which contains the item count)
    private static final By CART_BADGE = By.xpath("//span[@class='shopping_cart_badge']");

    public CartIconPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to get the cart item count
    public int getCartItemCount() {
        setImplicitWait(1);
        try {
            WebElement cartBadge = driver.findElement(CART_BADGE);
            setImplicitWait(0);
            return Integer.parseInt(cartBadge.getText());
        } catch (NoSuchElementException e) {
            // If the cart badge is not present, it means the cart is empty
            setImplicitWait(0);
            return 0;
        }
    }

    public void clickCartIcon(){
        lnkCart.click();
    }

    private void setImplicitWait(int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }


}
