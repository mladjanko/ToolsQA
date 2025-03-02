package Tests;

import Base.BaseTest;
import Pages.ButtonsPage;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.HomepageCards.ELEMENTS;
import static Helpers.URLs.HOMEPAGEURL;

public class ButtonsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        buttonsPage = new ButtonsPage();

        driver.navigate().to(HOMEPAGEURL);
        homepagePage.clickOnCard(ELEMENTS);
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 5, 1));
    }

    @Test(priority = 10)
    public void clickDoubleClickMeButton() {
        Actions actions = new Actions(driver);
        scrollToElement(buttonsPage.doubleClickMeButton);
        actions.doubleClick(buttonsPage.doubleClickMeButton).perform();
        waitUntilVisibilityOf(buttonsPage.doubleClickMessage);
        Assert.assertTrue(buttonsPage.doubleClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.doubleClickMessage.getText(), "You have done a double click");
    }

    @Test(priority = 20)
    public void clickRightClickMeButton() {
        Actions actions = new Actions(driver);
        scrollToElement(buttonsPage.rightClickMeButton);
        actions.contextClick(buttonsPage.rightClickMeButton).perform();
        waitUntilVisibilityOf(buttonsPage.rightClickMessage);
        Assert.assertTrue(buttonsPage.rightClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.rightClickMessage.getText(), "You have done a right click");
    }

    @Test(priority = 30)
    public void clickSingleClickMeButton() {
        scrollToElement(buttonsPage.clickMeButton);
        buttonsPage.clickMeButton.click();
        waitUntilVisibilityOf(buttonsPage.dynamicClickMessage);
        Assert.assertTrue(buttonsPage.dynamicClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.dynamicClickMessage.getText(), "You have done a dynamic click");
    }

    @Test(priority = 40)
    public void performAllActions() {
        Actions actions = new Actions(driver);

        scrollToElement(buttonsPage.doubleClickMeButton);
        actions.doubleClick(buttonsPage.doubleClickMeButton).perform();
        waitUntilVisibilityOf(buttonsPage.doubleClickMessage);
        Assert.assertTrue(buttonsPage.doubleClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.doubleClickMessage.getText(), "You have done a double click");

        scrollToElement(buttonsPage.rightClickMeButton);
        actions.contextClick(buttonsPage.rightClickMeButton).perform();
        waitUntilVisibilityOf(buttonsPage.rightClickMessage);
        Assert.assertTrue(buttonsPage.rightClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.rightClickMessage.getText(), "You have done a right click");

        scrollToElement(buttonsPage.clickMeButton);
        buttonsPage.clickMeButton.click();
        waitUntilVisibilityOf(buttonsPage.dynamicClickMessage);
        Assert.assertTrue(buttonsPage.dynamicClickMessage.isDisplayed());
        Assert.assertEquals(buttonsPage.dynamicClickMessage.getText(), "You have done a dynamic click");
    }
}
