package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksImagesPage extends BaseTest {

    public BrokenLinksImagesPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "img[src='/images/Toolsqa.jpg'")
    public WebElement validImage;

    @FindBy(css="img[src='/images/Toolsqa_1.jpg")
    public WebElement brokenImage;
}
