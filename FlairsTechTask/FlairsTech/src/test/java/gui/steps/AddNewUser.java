package gui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Admin;
import pages.Dashboard;
import pages.Login;
import pages.UserManagement;
import utilities.*;
import static gui.steps.BaseTest_Hooks.driver;

public class AddNewUser {

    // Variables
    int recordsCountBeforeAdding;
    int recordsCountAfterAdding;
    int recordsCountAfterDeleting;
    JsonFileManager loginTestData = new JsonFileManager("src/test/resources/TestDataJsonFiles/Login/LoginTestsTestData.json");
    JsonFileManager newUserTestData = new JsonFileManager("src/test/resources/TestDataJsonFiles/Login/NewUserGUITestsTestData.json");

    Login loginPage = new Login(driver);
    Admin adminPage = new Admin(driver);
    UserManagement userManagementPage = new UserManagement(driver);
    Dashboard dashboardPage = new Dashboard(driver);


    @Given("I am an admin and I am logged in to the system with username and password")
    public void loginWithCorrectUserNameAndPassword(){

        loginPage
                .validateOnLoginPage()
                .enterUserName(loginTestData.getTestData("userName"))
                .enterPassword(loginTestData.getTestData("password"))
                .clickOnLogin();
    }
    @When("I click on the 'Admin' button")
    public void clickOnAdminButton() {
        dashboardPage
                .validateOnDashboardPage()
                .clickOnAdminButton();
    }
    @Then("I should be redirected to the Admin page")
    public void validateOnAdminPage() {
        adminPage
                .validateOnAdminPage();
    }
    @And("I get the number of records before adding a new user")
    public void getNumberOfRecords() {
        recordsCountBeforeAdding = adminPage.getRecordsCount();
    }
    @Then("I click the 'Add' button")
    public void clickOnAddButton(){
        adminPage.clickOnAddButton();
    }
    @And("I enter the user's data")
    public void createNewUser(){
        userManagementPage
                .choseUserRole()
                .choseStatus()
                .enterEmployeeName()
                .enterUserName(newUserTestData.getTestData("userName"))
                .enterPassword(newUserTestData.getTestData("password"))
                .enterConfirmPassword(newUserTestData.getTestData("confirmPassword"))
                .clickOnSaveButton()
                .assertOnSuccessMessage();
    }

    @And("I get the number of records after adding a new user")
    public void getNumberOfRecordsAfterAdding() {
        recordsCountAfterAdding = adminPage.getRecordsCount();
    }
    @And("Verify number of records is increased by 1")
    public void validateNumberOfRecords() {
        adminPage.validateNumberOfRecordsAfterAdding(recordsCountBeforeAdding + 1, recordsCountAfterAdding);
    }
    @Then("I should be able to see the new user's data")
    public void validateNewUserData(){
        adminPage.enterUserName("OrangeTest123").clickOnSearchButton();
    }
    @And("Verify the new user's data should be correct")
    public void validateNewUserDataCorrectness() {
        adminPage.assertOnUserDataSearchResult(
                        newUserTestData.getTestData("userName"),
                        newUserTestData.getTestData("userRole"),
                        newUserTestData.getTestData("status"));
    }
    @And("I should be able to delete the new user")
    public void deleteNewUser() {
        try {
            adminPage.clickOnDeleteIcon()
                    .clickOnConfirmDeleteButton()
                    .assertOnNoRecordsFoundAlert();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @And("the number of records should be decreased by 1")
    public void validateNumberOfRecordsAfterDeleting() {
        adminPage.refreshPage().validateOnAdminPage();
        recordsCountAfterDeleting = adminPage.getRecordsCount();
        adminPage.validateNumberOfRecordsAfterDeleting(recordsCountAfterAdding - 1, recordsCountAfterDeleting);
    }

}
