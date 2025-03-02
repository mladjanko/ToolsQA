package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomepagePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        homepagePage = new HomepagePage();
        excelReader = new ExcelReader("ToolsQAData.xlsx");
        driver.navigate().to(excelReader.getStringData("Homepage", 0, 0));
    }

    @Test
    public void testPageReached() {
        String expectedUrl = excelReader.getStringData("Homepage", 0, 0);
        String currentUrl = driver.getCurrentUrl();
        int expectedCardsNumber = 6;
        Assert.assertEquals(currentUrl, expectedUrl);
        Assert.assertEquals(homepagePage.cardsList.size(), expectedCardsNumber);
    }
}
