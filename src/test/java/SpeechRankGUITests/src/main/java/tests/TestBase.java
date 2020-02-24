package tests;

import configuration.ConfigurationProperties;
import configuration.PropertiesLoader;
import driver.manager.BrowserType;
import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import utils.ScreenShotMaker;

import java.util.Properties;

import static navigation.ApplicationURLs.APPLICATION_URL;

public class TestBase {

    private Logger logger1 = LogManager.getLogger(TestBase.class);

    @BeforeClass
    public void beforeClass() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("configuration.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);
    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeTest(@Optional BrowserType browserType) {
        DriverManager.setWebDriver(browserType);
        DriverManager.getWebDriver();
        DriverUtils.setInitialConfiguration();
        DriverUtils.navigateToPage(APPLICATION_URL);

        logger1.info("page opened");
    }

    @AfterMethod

    public void afterTest() {

        ScreenShotMaker.makeScreenShot();
        DriverManager.disposeDriver();

    }
}