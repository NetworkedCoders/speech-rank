package configuration;

import driver.manager.BrowserType;

public class LocalWebDriverProperties {



    public static BrowserType getLocalBrowser() {
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("local.browser"));
    }

    public static String getChromeWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("chrome.driver.location");
    }

    public static String getFirefoxWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("firefox.driver.location");
    }

    public static String getInternetExplorerWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("ie.driver.location");
    }
}