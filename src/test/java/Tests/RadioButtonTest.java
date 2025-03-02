package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import Pages.RadioButtonPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class RadioButtonTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        radioButtonPage = new RadioButtonPage();
        excelReader = new ExcelReader("ToolsQATestData.xlsx");
        driver.navigate().to(excelReader.getStringData("Homepage", 0, 0));

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 1, 1));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 3, 1));
    }

    @Test(priority = 10)
    public void clickOnYesRadioButtonTest() throws InterruptedException {
        radioButtonPage.clickRadioButton(excelReader.getStringData("Radio Button", 0, 0));
        wait.until(ExpectedConditions.visibilityOf(radioButtonPage.textSuccess.getFirst()));
        Assert.assertTrue(radioButtonPage.youHaveSelectedText.isDisplayed());
        Assert.assertTrue(radioButtonPage.textSuccess.getFirst().getText().equalsIgnoreCase("yes"),
                "Success message should be 'Yes' when 'Yes' is selected.");
        Thread.sleep(3000);
    }

    @Test(priority = 20)
    public void clickOnImpressiveRadioButton() throws InterruptedException {
        radioButtonPage.clickRadioButton(excelReader.getStringData("Radio Button", 1, 0));
        wait.until(ExpectedConditions.visibilityOf(radioButtonPage.textSuccess.getFirst()));
        Assert.assertTrue(radioButtonPage.youHaveSelectedText.isDisplayed());
        Assert.assertTrue(radioButtonPage.textSuccess.getFirst().getText().equalsIgnoreCase("impressive"),
                "Success message should be 'Impressive' when 'Impressive' is selected.");
        Thread.sleep(3000);
    }

    @Test(priority = 30)
    public void clickOnNoRadioButton() throws InterruptedException {
        // Clicking on 'No' radio button, assert that the success message is empty
        radioButtonPage.clickRadioButton(excelReader.getStringData("Radio Button", 2, 0));
        Assert.assertTrue(radioButtonPage.textSuccess.isEmpty(), "Success message should be empty when 'No' is selected.");
        Thread.sleep(3000);
    }
}
