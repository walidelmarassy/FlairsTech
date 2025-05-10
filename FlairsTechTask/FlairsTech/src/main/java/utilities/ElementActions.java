package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ElementActions {
    private static Wait<WebDriver> wait;
    private static int defaultWaitTime = 10;

    @Step("Click on Element: {elementLocator}")
    public static void click(WebDriver driver, By elementLocator) {
        elementWaitingStrategy(driver, elementLocator);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).click();
    }
    @Step("Enter data to Element: {elementLocator}")
    public static void enterData(WebDriver driver, By elementLocator, String data) {
        elementWaitingStrategy(driver, elementLocator);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).sendKeys(data);
    }

    @Step("Get text of Element: {elementLocator}")
    public static String getElementText(WebDriver driver, By elementLocator) {
        elementWaitingStrategy(driver, elementLocator);
        return driver.findElement(elementLocator).getText();
    }

    @Step("Wait for visibility of Element: {elementLocator}")
    public static boolean visibilityOfElementLocated(WebDriver driver, By elementLocator) {
        elementWaitingStrategy(driver, elementLocator);
        return true;
    }
    @Step("Clear data from Element: {elementLocator}")
    public static void clearData(WebDriver driver, By elementLocator) {
        elementWaitingStrategy(driver, elementLocator);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).clear();
    }
    @Step("Select option from dropdown: {elementLocator}")
    public static void selectOptionFromDropdown(WebDriver driver, By elementLocator, String option) {
        click(driver, elementLocator);
        try {
            WebElement optionToBeSelected = driver.findElement(By.xpath("//div[@role='listbox']/div/span[text()='" + option + "']"));
            clickElement(optionToBeSelected);
        } catch (NoSuchElementException e) {
            Assert.fail("Error: Option not found in the dropdown.");
        } catch (StaleElementReferenceException e) {
            Assert.fail("Error: Dropdown element is no longer attached to the DOM.");
        } catch (ElementNotInteractableException e) {
            Assert.fail("Error: Dropdown element is present but not interactable.");
        } catch (WebDriverException e) {
            Assert.fail("WebDriver error: " + e.getMessage());
        }
    }

    @Step("Click on Element {element}")
    public static void clickElement(WebElement element) {
        waitForElementToBeVisible(element, defaultWaitTime);
        try {
            element.click();
        } catch (NoSuchElementException e) {
            Assert.fail("Error: Element not found in the DOM.");
        } catch (StaleElementReferenceException e) {
            Assert.fail("Error: Element is no longer attached to the DOM.");
        } catch (ElementNotInteractableException e) {
            Assert.fail("Error: Element is present but not intractable.");
        } catch (WebDriverException e) {
            Assert.fail("WebDriver error: " + e.getMessage());
        }
    }
    @Step("Wait for Element to be visible")
    public static void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Assert.fail("Timeout: Element is not visible within the specified time.", e);
        } catch (NoSuchElementException e) {
            Assert.fail("Error: Element not found in the DOM.", e);
        } catch (StaleElementReferenceException e) {
            Assert.fail("Error: Element is no longer attached to the DOM.", e);
        } catch (ElementNotInteractableException e) {
            Assert.fail("Error: Element is present but not interactable.", e);
        } catch (WebDriverException e) {
            Assert.fail("WebDriver error: " + e.getMessage(), e);
        }
    }

    @Step("Make sure the page is loaded")
    public static void waitForPageToLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    private static void elementWaitingStrategy(WebDriver driver, By elementLocator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
}
