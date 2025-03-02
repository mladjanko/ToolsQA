package Tests;

import Base.BaseTest;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import Pages.LinksPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinksPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {

        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        linksPage = new LinksPage();

        driver.navigate().to(excelReader.getStringData("Homepage", 0, 0));

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 1, 1));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 6, 1));
    }

    @Test(priority = 10)
    public void clickOnHomeLink() {
        linksPage.homeLink.click();
    }
}
