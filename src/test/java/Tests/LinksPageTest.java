package Tests;

import Base.BaseTest;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import Pages.LinksPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static Helpers.HomepageCards.ELEMENTS;
import static Helpers.URLs.HOMEPAGEURL;

public class LinksPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        linksPage = new LinksPage();

        driver.navigate().to(HOMEPAGEURL);
        homepagePage.clickOnCard(ELEMENTS);
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 6, 1));
    }

    @Test(priority = 10)
    public void clickOnSimpleHomeLink() {
        scrollToElement(linksPage.simpleHomeLink);
        linksPage.simpleHomeLink.click();
        ArrayList<String> browserTabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabList.get(1));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, HOMEPAGEURL);
    }

    @Test(priority = 20)
    public void clickOnDynamicHomeLink() {
        scrollToElement(linksPage.dynamicHomeLink);
        linksPage.dynamicHomeLink.click();
        ArrayList<String> browserTabList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabList.get(1));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, HOMEPAGEURL);
    }

    @Test(priority = 30)
    public void clickOnCreatedLink() {
        scrollToElement(linksPage.createdLink);
        linksPage.createdLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 201 and status text Created";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 40)
    public void clickOnNoContentLink() {
        scrollToElement(linksPage.noContentLink);
        linksPage.noContentLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 204 and status text No Content";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 50)
    public void clickOnMovedLink() {
        scrollToElement(linksPage.movedLink);
        linksPage.movedLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 301 and status text Moved Permanently";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 60)
    public void clickOnBadRequestLink() {
        scrollToElement(linksPage.badRequestLink);
        linksPage.badRequestLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 400 and status text Bad Request";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 70)
    public void clickUnauthorizedLink() {
        scrollToElement(linksPage.unauthorizedLink);
        linksPage.unauthorizedLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 401 and status text Unauthorized";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 80)
    public void clickOnForbiddenLink() {
        scrollToElement(linksPage.forbiddenLink);
        linksPage.forbiddenLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 403 and status text Forbidden";
        Assert.assertEquals(linkResponse, expectedResponse);
    }

    @Test(priority = 90)
    public void clickOnNotFoundLink() {
        scrollToElement(linksPage.notFoundLink);
        linksPage.notFoundLink.click();
        waitUntilVisibilityOf(linksPage.linkResponse);
        String linkResponse = linksPage.linkResponse.getText();
        String expectedResponse = "Link has responded with staus 404 and status text Not Found";
        Assert.assertEquals(linkResponse, expectedResponse);
    }
}
