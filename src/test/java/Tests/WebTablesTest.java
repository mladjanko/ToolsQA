package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomepagePage;
import Pages.LeftsidemenuPage;
import Pages.RegistrationFormPage;
import Pages.WebTablesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Helpers.URLs.HOMEPAGEURL;

public class WebTablesTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        homepagePage = new HomepagePage();
        leftsidemenuPage = new LeftsidemenuPage();
        webTablesPage = new WebTablesPage();
        registrationFormPage = new RegistrationFormPage();
        excelReader = new ExcelReader("ToolsQATestData.xlsx");
        driver.navigate().to(HOMEPAGEURL);

        homepagePage.clickOnCard(excelReader.getStringData("Homepage", 0, 0));
        leftsidemenuPage.clickOnLeftMenuItem(excelReader.getStringData("Left Side Menu", 4, 1));
    }

    @Test
    public void addUsersFromExcel() {
        Assert.assertEquals(webTablesPage.rowsFilled(), excelReader.getIntegerData("Web Tables", 0, 1));
        webTablesPage.removeAllRecords();
        Assert.assertTrue(webTablesPage.noRowsFoundAlert.isDisplayed());

        for (int i = 0; i < excelReader.getLastRow("Web Tables") - 2; i++) {
            scrollToElement(webTablesPage.addButton);
            webTablesPage.addButton.click();
            String firstName = excelReader.getStringData("Web Tables", i + 3, 0);
            String lastName = excelReader.getStringData("Web Tables", i + 3, 1);
            String email = excelReader.getStringData("Web Tables", i + 3, 2);
            String age = String.valueOf(excelReader.getIntegerData("Web Tables", i + 3, 3));
            String salary = String.valueOf(excelReader.getIntegerData("Web Tables", i + 3, 4));
            String department = excelReader.getStringData("Web Tables", i + 3, 5);

            registrationFormPage.inputFirstName(firstName);
            registrationFormPage.inputLastName(lastName);
            registrationFormPage.inputEmail(email);
            registrationFormPage.inputAge(age);
            registrationFormPage.inputSalary(salary);
            registrationFormPage.inputDepartment(department);
            registrationFormPage.submitButton.click();

            Assert.assertEquals(webTablesPage.rowsFilled(), i + 1);
            Assert.assertEquals(webTablesPage.cells.get(i * 7).getText(), firstName);
            Assert.assertEquals(webTablesPage.cells.get(i * 7 + 1).getText(), lastName);
            Assert.assertEquals(webTablesPage.cells.get(i * 7 + 2).getText(), age);
            Assert.assertEquals(webTablesPage.cells.get(i * 7 + 3).getText(), email);
            Assert.assertEquals(webTablesPage.cells.get(i * 7 + 4).getText(), salary);
            Assert.assertEquals(webTablesPage.cells.get(i * 7 + 5).getText(), department);
        }
    }
}
