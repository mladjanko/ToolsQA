package Tests;

import Base.BaseTest;
import Pages.CheckBoxPage;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Helpers.HomepageCards.ELEMENTS;
import static Helpers.URLs.HOMEPAGEURL;

public class CheckBoxTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        checkBoxPage = new CheckBoxPage();

        driver.navigate().to(HOMEPAGEURL);
        homepagePage.clickOnCard(ELEMENTS);
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 2, 1));
    }

    @Test
    public void checkHomeWillCheckAll() {
        checkBoxPage.expandCheckBoxTree();
        System.out.println(checkBoxPage.checkBoxList.getFirst().getText());
        scrollToElement(checkBoxPage.uncheckedCheckboxList.getFirst());
        Assert.assertEquals(checkBoxPage.uncheckedCheckboxList.size(), 17); //Checking how many unchecked boxes there are
        checkBoxPage.uncheckedCheckboxList.getFirst().click(); //Here we clicked on the first checkbox "Home" which will check everything
        Assert.assertEquals(checkBoxPage.uncheckedCheckboxList.size(), 0); //Checking whether there are no more unchecked
        Assert.assertEquals(checkBoxPage.checkedCheckboxList.size(), 17); //Checking whether all boxes are checked
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
