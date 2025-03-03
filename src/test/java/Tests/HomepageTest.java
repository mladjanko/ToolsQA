package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomepagePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import static Helpers.URLs.HOMEPAGEURL;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        homepagePage = new HomepagePage();
        excelReader = new ExcelReader("ToolsQATestData.xlsx");
        driver.navigate().to(HOMEPAGEURL);
    }

    @Test
    public void testPageReached() {
        String currentUrl = driver.getCurrentUrl();
        int expectedCardsNumber = 6;
        Assert.assertEquals(currentUrl, HOMEPAGEURL);
        Assert.assertEquals(homepagePage.cardsList.size(), expectedCardsNumber);
    }
}
