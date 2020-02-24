package utils;

import driver.manager.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotMaker {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] makeScreenShot() {
        byte[] screenshotFile = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        return screenshotFile;
    }

}