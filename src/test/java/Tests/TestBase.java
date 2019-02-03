package Tests;

import AppManager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static com.codeborne.selenide.WebDriverRunner.INTERNET_EXPLORER;

public class TestBase {
    protected ApplicationManager app;

    protected ApplicationManager getNewApp() {

        return new ApplicationManager(System.getProperty("browser", INTERNET_EXPLORER));
    }

    @BeforeMethod
    public void setUp() {
        app = getNewApp();
        app.init();
    }

    @AfterMethod
    public void stop() {
        app.driver.quit();
        app.driver = null;
    }
}