package page.sauce.component;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Wait;

@Getter
public class Footer {
    @FindBy(css = "[data-test='footer-copy']")
    private WebElement copyRight;

    public Footer() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(copyRight);
    }
}
