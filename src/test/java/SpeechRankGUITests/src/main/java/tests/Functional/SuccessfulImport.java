package tests.Functional;

import driver.manager.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import tests.TestBase;

import static navigation.ApplicationURLs.APPLICATION_URL;
import static org.testng.AssertJUnit.assertEquals;

//This test checks an user's login with correct credentials defined in a LoginPage class

public class SuccessfulImport extends TestBase {

   @Test
   @Severity(SeverityLevel.CRITICAL)
   @Description (".")

    public void Import() {
        DriverUtils.navigateToPage(APPLICATION_URL);


        //assertEquals(MessageTxt, "MY ACCOUNT");
    }

}