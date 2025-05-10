package pages;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ElementActions;

import java.time.Duration;

public class UserManagement extends BasePage {
    private WebDriverWait wait;
    public UserManagement(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By userRoleList = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div//i");
    private By statusList = By.xpath("//label[text()='Status']/parent::div/following-sibling::div//i");
    private By employeeNameField = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By employeeNameOption = By.xpath("(//div[@role='listbox']//child::div)[2]");
    private By userNameField = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By passwordField = By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input");
    private By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input");
    private By saveButton = By.xpath("//button[@type='submit' and text()=' Save ']");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast--success')]//p");

    /////////// Actions \\\\\\\\\\\\
    @Step("Chose 'User Role' from the list {Admin}")
    public UserManagement choseUserRole(){
        ElementActions.selectOptionFromDropdown(driver, userRoleList, "Admin");
        return this;
    }

    @Step("Chose 'Status' from the list {Enabled}")
    public UserManagement choseStatus() {
        ElementActions.selectOptionFromDropdown(driver, statusList, "Enabled");
        return this;
    }
    @Step("Enter Employee Name: {employeeName}")
    public UserManagement enterEmployeeName() {
        ElementActions.click(driver, employeeNameField);
        ElementActions.enterData(driver,employeeNameField,"t");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();  // Move down once
        wait.until(ExpectedConditions.elementToBeClickable(employeeNameField)); // Wait for the option to be clickable

        actions.sendKeys(Keys.ARROW_DOWN).perform();  // Move down again
        wait.until(ExpectedConditions.elementToBeClickable(employeeNameField)); // Wait for the option to be clickable

        actions.sendKeys(Keys.ENTER).perform();
        ElementActions.click(driver, employeeNameOption);
        return this;
    }

    @Step("Enter User Name: {userName}")
    public UserManagement enterUserName(String userName) {
        ElementActions.enterData(driver, userNameField, userName);
        return this;
    }
    @Step("Enter Password: {password}")
    public UserManagement enterPassword(String password) {
        ElementActions.enterData(driver, passwordField, password);
        return this;
    }
    @Step("Enter Confirm Password: {confirmPassword}")
    public UserManagement enterConfirmPassword(String confirmPassword) {
        ElementActions.enterData(driver, confirmPasswordField, confirmPassword);
        return this;
    }
    @Step("Click on 'Save' Button")
    public UserManagement clickOnSaveButton() {
        ElementActions.click(driver, saveButton);
        return this;
    }
    ///////////////// Validations \\\\\\\\\\\\
    @Step("Assert on Success Message: {expectedMessage}")
    public UserManagement assertOnSuccessMessage() {
        Assert.isTrue(ElementActions.visibilityOfElementLocated(driver, successMessage),"Success message is not displayed");
        return this;
    }
}
