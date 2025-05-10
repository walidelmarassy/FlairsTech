package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
    }
    // Locators
    private By userNameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("(//form/div)[3]");
    private By loginPage = By.className("orangehrm-login-form");

    /////////// Actions \\\\\\\\\\\\

    @Step("Enter the user name {userName}")
    public Login enterUserName(String userName){
        ElementActions.enterData(driver, userNameField, userName);
        return this;
    }
    @Step("Enter the password {password}")
    public Login enterPassword(String password){
        ElementActions.enterData(driver, passwordField, password);
        return this;
    }
    @Step("Click on 'Login ' Button")
    public Login clickOnLogin() {
        ElementActions.click(driver, loginButton);
        return this;
    }

    ///////////////// Validations \\\\\\\\\\\\
    @Step("Validate on 'Login ' page")
    public Login validateOnLoginPage() {
        driver.findElement(loginPage).isDisplayed();
        System.out.println("Validating on Login Page");
        return this;
    }
}
