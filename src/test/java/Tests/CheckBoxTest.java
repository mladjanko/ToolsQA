package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CheckBoxPage;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckBoxTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        checkBoxPage = new CheckBoxPage();
        excelReader = new ExcelReader("ToolsQATestData.xlsx");
        driver.navigate().to(excelReader.getStringData("Homepage", 0, 0));

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 1, 1));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 2, 1));
    }

    @Test
    public void checkHomeWillCheckAll() {
        checkBoxPage.expandCheckBoxTree();
        System.out.println(checkBoxPage.checkBoxList.getFirst().getText());
        scrollToElement(checkBoxPage.uncheckedCheckboxList.getFirst());
        Assert.assertEquals(checkBoxPage.uncheckedCheckboxList.size(), 17); //Provera koliko ima necekiranih boxova
        checkBoxPage.uncheckedCheckboxList.getFirst().click(); //Ovde smo kliknuli na prvi checkbox "Home" koji ce da cekira sve
        Assert.assertEquals(checkBoxPage.uncheckedCheckboxList.size(), 0); //Provera da li nema vise necekiranih
        Assert.assertEquals(checkBoxPage.checkedCheckboxList.size(), 17); //Provera da li su svi boxovi cekirani
    }

    @Test
    public void clickCustomCheckbox() {
        checkBoxPage.expandCheckBoxTree();
        checkBoxPage.checkNode("Angular");
        Assert.assertTrue(checkBoxPage.result.getText().contains("angular"));
        checkBoxPage.checkNode("General");
        Assert.assertTrue(checkBoxPage.result.getText().contains("general"));
    }
}
