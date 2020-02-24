package driver.manager;

import java.util.concurrent.TimeUnit;

public class DriverUtils {

    public static void setInitialConfiguration(){
        DriverManager.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.getWebDriver().manage().window().maximize();
    }

    public static void navigateToPage(String pageUrl){
        DriverManager.getWebDriver().navigate().to(pageUrl);
    }

}