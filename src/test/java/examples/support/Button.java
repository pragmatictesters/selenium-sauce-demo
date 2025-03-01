package examples.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Actions;

public class Button implements IButton, WrapsElement {


    private final WebElement webElement;
    private final WebDriver webDriver;

    public Button(WebDriver webDriver, WebElement webElement){
        this.webElement = webElement;
        this.webDriver = webDriver;
    }

    @Override
    public void click() {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void hover() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }
}
