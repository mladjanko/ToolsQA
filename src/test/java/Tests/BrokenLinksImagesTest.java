package Tests;

import Base.BaseTest;
import Pages.BrokenLinksImagesPage;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.HomepageCards.ELEMENTS;
import static Helpers.URLs.HOMEPAGEURL;

public class BrokenLinksImagesTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        brokenLinksImagesPage = new BrokenLinksImagesPage();

        driver.navigate().to(HOMEPAGEURL);
        homepagePage.clickOnCard(ELEMENTS);
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 7, 1));
    }

    @Test(priority = 10)
    public void assertValidImage() {
        Assert.assertTrue(brokenLinksImagesPage.validImage.isEnabled());
    }

    @Test(priority = 20)
    public void assertBrokenImage() {
        Assert.assertTrue(brokenLinksImagesPage.brokenImage.isDisplayed());
    }
}
