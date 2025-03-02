package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.ButtonsPage;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import static Helpers.URLs.HOMEPAGEURL;

public class ButtonsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        buttonsPage = new ButtonsPage();
        excelReader = new ExcelReader("ToolsQATestData.xlsx");
        driver.navigate().to(HOMEPAGEURL);

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 0, 0));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 5, 1));
    }

    @Test(priority = 10)
    public void clickDoubleClickMeButton() {
        Actions actions = new Actions(driver);
        scrollToElement(buttonsPage.doubleClickMeButton);
        actions.doubleClick(buttonsPage.doubleClickMeButton).perform();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.doubleClickMessage));
        Assert.assertTrue(buttonsPage.doubleClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.doubleClickMessage.getText(), "You have done a double click");
    }

    @Test(priority = 20)
    public void clickRightClickMeButton() {
        Actions actions = new Actions(driver);
        scrollToElement(buttonsPage.rightClickMeButton);
        actions.contextClick(buttonsPage.rightClickMeButton).perform();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.rightClickMessage));
        Assert.assertTrue(buttonsPage.rightClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.rightClickMessage.getText(), "You have done a right click");
    }

    @Test(priority = 30)
    public void clickSingleClickMeButton() {
        scrollToElement(buttonsPage.clickMeButton);
        buttonsPage.clickMeButton.click();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.dynamicClickMessage));
        Assert.assertTrue(buttonsPage.dynamicClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.dynamicClickMessage.getText(), "You have done a dynamic click");
    }

    @Test(priority = 40)
    public void performAllActions() {
        Actions actions = new Actions(driver);

        scrollToElement(buttonsPage.doubleClickMeButton);
        actions.doubleClick(buttonsPage.doubleClickMeButton).perform();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.doubleClickMessage));
        Assert.assertTrue(buttonsPage.doubleClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.doubleClickMessage.getText(), "You have done a double click");

        scrollToElement(buttonsPage.rightClickMeButton);
        actions.contextClick(buttonsPage.rightClickMeButton).perform();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.rightClickMessage));
        Assert.assertTrue(buttonsPage.rightClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.rightClickMessage.getText(), "You have done a right click");

        scrollToElement(buttonsPage.clickMeButton);
        buttonsPage.clickMeButton.click();
        wait.until(ExpectedConditions.visibilityOf(buttonsPage.dynamicClickMessage));
        Assert.assertTrue(buttonsPage.dynamicClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.dynamicClickMessage.getText(), "You have done a dynamic click");
    }
}
