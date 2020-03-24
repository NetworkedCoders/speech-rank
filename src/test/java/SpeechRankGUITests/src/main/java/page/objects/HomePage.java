package page.objects;

import driver.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;



public class HomePage {

    private Logger logger = LogManager.getLogger(HomePage.class);

    @FindBy(css = "body > ui-view > header > div > nav > div.navbar-header > a")
    private WebElement MainLogo;

    @FindBy(css = "#navbar > ul > li:nth-child(1) > a")
    private WebElement HomeButton;

    //search fields

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(3) > input")
    private WebElement confName;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(4) > input")
    private WebElement Year;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(5) > input")
    private WebElement playlistId;

    @FindBy(css = "body > ui-view > ui-view > div > button")
    private WebElement ImportButton;


    //conferences
    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(7) > div:nth-child(2) > div:nth-child(1) > a > p")
    private WebElement DevConf2019;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(7) > div:nth-child(2) > div:nth-child(2) > a > p")
    private WebElement BoilingFrogs2019;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(7) > div:nth-child(2) > div:nth-child(3) > a > p")
    private WebElement Scalar2019;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(7) > div:nth-child(2) > div:nth-child(4) > a > p")
    private WebElement Confitura2019;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(8) > div:nth-child(2) > div:nth-child(1) > a > p")
    private WebElement BoilingFrogs2018;
    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(8) > div:nth-child(2) > div:nth-child(2) > a > p")
    private static WebElement Confitura2018;
    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(9) > div:nth-child(2) > div > a > p")
    private WebElement DevConf2017;

    public String defaultName ="Some conference name";
    public String year = "2016";
    public String playlistID ="someID";

    public HomePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public String Import(){

        // Create an instance of JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();;

        confName.clear();
        confName.sendKeys(defaultName);
        logger.info("name typed");

        Year.clear();
        Year.sendKeys(year);
        logger.info("year typed");

        playlistId.clear();
        playlistId.sendKeys(playlistID);
        logger.info("id typed");

        js.executeScript("window.scrollBy(0,1000);");

        ImportButton.click();

        String finalAlertText = playlistId.getText();

        return finalAlertText;
    }

    public static void goToConfitura2018()

    {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();;
        js.executeScript("window.scrollBy(0,1000);");
        Confitura2018.click();
    }

}
