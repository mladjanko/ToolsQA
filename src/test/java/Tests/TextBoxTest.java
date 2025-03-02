package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import Pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TextBoxTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        textBoxPage = new TextBoxPage();
        excelReader = new ExcelReader("ToolsQAData.xlsx");
        driver.navigate().to(excelReader.getStringData("Homepage", 0, 0));

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 1, 1));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 1, 1));
    }

    @Test
    public void testWithValidData() {
        String userName = excelReader.getStringData("Text Box",1,0);
        String userEmail = excelReader.getStringData("Text Box",2,0);
        String currentAddress = excelReader.getStringData("Text Box",3,0);
        String permanentAddress = excelReader.getStringData("Text Box",4,0);

        textBoxPage.userNameField.clear();
        textBoxPage.userNameField.sendKeys(userName);
        textBoxPage.userEmailField.clear();
        textBoxPage.userEmailField.sendKeys(userEmail);
        textBoxPage.currentAddressField.clear();
        textBoxPage.currentAddressField.sendKeys(currentAddress);
        textBoxPage.permanentAddressField.clear();
        textBoxPage.permanentAddressField.sendKeys(permanentAddress);
        scrollToElement(textBoxPage.submitButton);
        textBoxPage.submitButton.click();

        Assert.assertTrue(textBoxPage.outputTextBox.getText().contains("Name:" + userName));
        Assert.assertTrue(textBoxPage.outputTextBox.getText().contains("Email:" + userEmail));
        Assert.assertTrue(textBoxPage.outputTextBox.getText().contains("Current Address :" + currentAddress));
        Assert.assertTrue(textBoxPage.outputTextBox.getText().contains("Permananet Address :" + permanentAddress));
    }

    @Test
    public void testWithInvalidEmail() {
        String userName = excelReader.getStringData("Text Box",1,1);
        String userEmail = excelReader.getStringData("Text Box",2,1);
        String currentAddress = excelReader.getStringData("Text Box",3,1);
        String permanentAddress = excelReader.getStringData("Text Box",4,1);

        textBoxPage.userNameField.clear();
        textBoxPage.userNameField.sendKeys(userName);
        textBoxPage.userEmailField.clear();
        textBoxPage.userEmailField.sendKeys(userEmail);
        textBoxPage.currentAddressField.clear();
        textBoxPage.currentAddressField.sendKeys(currentAddress);
        textBoxPage.permanentAddressField.clear();
        textBoxPage.permanentAddressField.sendKeys(permanentAddress);
        scrollToElement(textBoxPage.submitButton);
        textBoxPage.submitButton.click();

        Assert.assertTrue(textBoxPage.errorField.isDisplayed());
    }
}
