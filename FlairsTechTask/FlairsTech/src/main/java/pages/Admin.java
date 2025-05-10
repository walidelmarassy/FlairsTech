package pages;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class Admin extends BasePage {


    public Admin(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By adminPage = By.xpath("//header//h6");
    private By numberOfRecords = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span");
    private By addButton = By.xpath("//div[@class='orangehrm-header-container']//button");

    private By userNameField = By.xpath("//div[@class='oxd-input-group__label-wrapper']/following-sibling::div/input");
    private By searchButton = By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']");

    private By userNameSearchResult = By.xpath("(//div[@class='oxd-table-body']/div/div/div)[2]/div");
    private By userRoleSearchResult = By.xpath("(//div[@class='oxd-table-body']/div/div/div)[3]/div");
    private By employeeSearchResult = By.xpath("(//div[@class='oxd-table-body']/div/div/div)[4]/div");
    private By statusSearchResult = By.xpath("(//div[@class='oxd-table-body']/div/div/div)[5]/div");
    private By deleteIcon = By.xpath("(//div[@class='oxd-table-cell-actions']/button)[1]");
    private By confirmDeleteButton = By.xpath("(//div[@class='orangehrm-modal-footer']/button)[2]");
    private By noRecordsFoundAlert = By.xpath("//div[@class='orangehrm-header-container']/following::div/div/span");



    /////////// Actions \\\\\\\\\\\\
    public int getRecordsCount() {
        ElementActions.visibilityOfElementLocated(driver, numberOfRecords);
        String fullText = ElementActions.getElementText(driver,numberOfRecords);
        String numberOnly = fullText.replaceAll("\\D+", "");
        return Integer.parseInt(numberOnly);
    }
    @Step("Click on 'Add' Button")
    public Admin clickOnAddButton(){
        ElementActions.visibilityOfElementLocated(driver, addButton);
        ElementActions.click(driver, addButton);
        return this;
    }

    @Step("Enter the user name {userName}")
    public Admin enterUserName(String userName) {
        ElementActions.enterData(driver, userNameField, userName);
        return this;
    }
    @Step("Click on 'Search' Button")
    public Admin clickOnSearchButton(){
        ElementActions.click(driver, searchButton);
        return this;
    }

    @Step("Refresh the page")
    public Admin refreshPage() {
        driver.navigate().refresh();
        ElementActions.waitForPageToLoad(driver);
        return this;
    }
    @Step("Click on 'Delete' Icon")
    public Admin clickOnDeleteIcon() {
        ElementActions.click(driver, deleteIcon);
        return this;
    }
    @Step("Click on 'Confirm Delete' Button")
    public Admin clickOnConfirmDeleteButton(){
        ElementActions.click(driver, confirmDeleteButton);
        return this;
    }
    @Step("Assert on 'No Records Found' Alert")
    public Admin assertOnNoRecordsFoundAlert(){
        ElementActions.visibilityOfElementLocated(driver, noRecordsFoundAlert);
        return this;
    }
    @Step("Clear the User Name Field after search")
    public Admin clearUserNameField() {
        ElementActions.click(driver, userNameField);
        ElementActions.clearData(driver, userNameField);
        return this;
    }

    ///////////////// Validations \\\\\\\\\\\\
    @Step("Validate on 'Admin' page")
    public Admin validateOnAdminPage() {
        ElementActions.getElementText(driver, adminPage);
        return this;
    }
    @Step("Assert on user name search result, Should be {expectedUserName}")
    public Admin assertOnUserDataSearchResult(String expectedUserName, String expectedUserRole, String expectedStatus) {
        String actualUserName = ElementActions.getElementText(driver, userNameSearchResult);
        String actualUserRole = ElementActions.getElementText(driver, userRoleSearchResult);
        String actualStatus = ElementActions.getElementText(driver, statusSearchResult);

        if (!actualUserName.equals(expectedUserName)) {
            throw new AssertionError("Expected user name is: " + expectedUserName + " but found: " + actualUserName);
        }
        if (!actualUserRole.equals(expectedUserRole)) {
            throw new AssertionError("Expected user role is: " + expectedUserRole + " but found: " + actualUserRole);
        }
        if (!actualStatus.equals(expectedStatus)) {
            throw new AssertionError("Expected status is: " + expectedStatus + " but found: " + actualStatus);
        }
        return this;
    }
    @Step("Assert on number of records, Should be {expectedRecords}")
    public void validateNumberOfRecordsAfterAdding(int before, int after) {
        Assert.isTrue(after==before, "The number of records did not increase by 1");
    }
    @Step("Assert on number of records, Should be {expectedRecords}")
    public void validateNumberOfRecordsAfterDeleting(int before, int after) {
        Assert.isTrue(after==before, "The number of records did not decrease by 1");
    }
}
