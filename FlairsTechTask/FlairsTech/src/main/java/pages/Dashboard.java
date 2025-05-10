package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class Dashboard extends BasePage {

    public Dashboard(WebDriver driver) {
        super(driver);
    }
    // Locators
    private By dashboardPage = By.xpath("//header//h6");
    private By adminButton = By.xpath("(//ul//li)[1]");

    /////////// Actions \\\\\\\\\\\\
    @Step("Click on 'Admin ' Button")
    public Dashboard clickOnAdminButton() {
        ElementActions.click(driver, adminButton);
        return this;
    }
    ///////////////// Validations \\\\\\\\\\\\
    @Step("Validate on 'Dashboard' page")
    public Dashboard validateOnDashboardPage() {
        ElementActions.getElementText(driver, dashboardPage);
        return this;
    }
}
