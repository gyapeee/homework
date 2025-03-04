package page;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Click;
import util.action.Fill;
import util.action.Wait;

public class RichTextEditorPage {
    @Getter
    @FindBy(css = "[data-placeholder='Type or paste your content here!']")
    private WebElement textBox;

    @FindBy(css = "[data-cke-tooltip-text*='Bold']")
    private WebElement boldButton;

    @FindBy(css = "[data-cke-tooltip-text*='Underline']")
    private WebElement underlineButton;

    public RichTextEditorPage() {
        Driver.get().get("https://onlinehtmleditor.dev");
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(textBox);
        Wait.forVisible(boldButton);
        Wait.forVisible(underlineButton);
    }

    public RichTextEditorPage type(String text) {
        Fill.in(textBox, text);
        return this;
    }

    public RichTextEditorPage clickOnBold() {
        Click.on(boldButton);
        return this;
    }

    public RichTextEditorPage clickOnUnderline() {
        Click.on(underlineButton);
        return this;
    }

}
