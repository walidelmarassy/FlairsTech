package gui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest_Hooks {
    public static WebDriver driver;




public static Properties properties = new Properties();

    public static WebDriver getDriver() throws IOException {
        if (driver == null) {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "src/test/resources/configuration.properties");

            properties.load(fileReader);

            if (properties.getProperty("Browser").equalsIgnoreCase("chrome")) {
                //Create prefs map to store all preferences
                Map<String, Object> prefs = new HashMap<String, Object>();
                //Put this into prefs map to switch off browser notification
                prefs.put("profile.default_content_setting_values.notifications", 1);
                //Create chrome options to set this prefs
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-cookies");
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver();
            } else if (properties.getProperty("Browser").equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (properties.getProperty("Browser").equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("profile.default_content_setting_values.notifications", 1);
                edgeOptions.addArguments("--start-maximized", "--disable-popup-blocking", "--disable-notifications");
                edgeOptions.addArguments("--disable-cookies");
                edgeOptions.setCapability("excludeSwitches", "disable-popup-blocking");
                edgeOptions.setCapability("unhandledPromptBehavior", "ignore");
                driver = new EdgeDriver();
            }

        }
        return driver;
    }

    @Before
    public void openUrl() throws IOException, InterruptedException {
        // Specify the browser type here: "chrome", "firefox", or "edge"
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit(); // Use quit() to close the entire browser session.
            driver = null;
        }
    }
    }
