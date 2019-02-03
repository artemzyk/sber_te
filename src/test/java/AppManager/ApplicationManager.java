package AppManager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

    public WebDriver driver;
    private final Properties properties;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() {
        try {
            properties.load(new FileReader(new File("src/test/resources/app.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (browser) {
            case "chrome":
                setChromeDriver();
                driver = new ChromeDriver();
                break;
            case "internet explorer":
                setExplorerDriver();
                driver = new InternetExplorerDriver();
                setDefaultTimeoutForIe();
                break;
            default:
                break;
        }
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("mainPageUrl"));
    }

    private void setChromeDriver() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("pathToChromeDriverLinux"));
        } else if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("pathToChromeDriverWindows"));
        }
    }

    private void setExplorerDriver() {
        System.setProperty("webdriver.ie.driver", properties.getProperty("pathToExplorerDriverWindows"));
    }

    private void setDefaultTimeoutForIe() {
        Configuration.timeout = Long.parseLong(properties.getProperty("ieTimeout"));
        Configuration.collectionsTimeout = Long.parseLong(properties.getProperty("ieTimeout"));
    }
}