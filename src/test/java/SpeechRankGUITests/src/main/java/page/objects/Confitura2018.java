package page.objects;

import driver.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;
import org.openqa.selenium.JavascriptExecutor;


public class Confitura2018 {

    private static Logger logger = LogManager.getLogger(Confitura2018.class);

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > p > span.rating > i:nth-child(1)")
    public WebElement Star1;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > p > span.rating > i:nth-child(5)")
    private static WebElement Star5;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > textarea")
    private static WebElement commentField;

    @FindBy(css = "#navbar > ul > li:nth-child(1) > a")
    private WebElement homeButton;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > button")
    private static WebElement addComment;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > div > ul > li")
    private static WebElement addedComment;

    @FindBy(css = "body > ui-view > ui-view > div > div:nth-child(2) > div:nth-child(2) > div.col-md-7 > div > ul > li:nth-child(1) > strong")
    private static WebElement CommentedBy;

    @FindBy(css = "body > ui-view > ui-view > div > div.row > div > div > h2")
    private WebElement homeHeader;


    public Confitura2018() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
   }


    public static void scrollDown() {

        // Create an instance of JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();

        // Scroll the window down by 1800 pixels
        js.executeScript("window.scrollBy(0,1800);");
    }


    public String returnToHome()
    {
        Actions action = new Actions(DriverManager.getWebDriver());
        homeButton.click();
        WaitForElement.waitUntilElementIsVisible(homeHeader);
        String Label1=homeHeader.getText();

        return Label1;
    }

    public static String addComment()
    {
        //scrollDown();
        Star5.click();
        commentField.clear();
        commentField.sendKeys("gruszki na wierzbie");
        logger.info("comment typed");
        addComment.click();
        WaitForElement.waitUntilElementIsVisible(addedComment);
        String comment=CommentedBy.getText();

        return comment;
    }

}