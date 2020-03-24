package driver.manager;

import org.openqa.selenium.WebDriver;

import static configuration.TestRunProperties.getBrowserToRun;
import static configuration.TestRunProperties.getIsRemoteRun;
import static driver.manager.BrowserType.FIREFOX;


public class DriverManager {



    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserType> browserTypeThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setWebDriver(BrowserType browserType) {


        WebDriver browser = null;

        if (browserType == null) {

            browserType = getBrowserToRun();
            browser = new BrowserFactory(browserType, getIsRemoteRun()).getBrowser();
        } else {

            browser = new BrowserFactory(browserType, getIsRemoteRun()).getBrowser();
        }

        browserTypeThreadLocal.set(browserType);

        webDriverThreadLocal.set(browser);
    }

    public static WebDriver getWebDriver() {

        if (webDriverThreadLocal.get() == null) {

            throw new IllegalStateException("WebDriver Instance was null! Please create instance of WebDriver using setWebDriver!");
        }

        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {
        webDriverThreadLocal.get().close();

        if (!browserTypeThreadLocal.get().equals(FIREFOX)) {
            webDriverThreadLocal.get().quit();
        }

        webDriverThreadLocal.remove();
        browserTypeThreadLocal.remove();
    }


    }
