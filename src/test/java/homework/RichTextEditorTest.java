package homework;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RichTextEditorPage;
import runner.TestBase;

@Feature("Text Editor")
public class RichTextEditorTest extends TestBase {

    private static final String AUTOMATION = "Automation";
    private static final String TEST_EXAMPLE = "Test Example";
    private static final String SPACE = " ";
    private static final String NEITHER_ALPHANUMERIC_NOR_SPACE = "[^a-zA-Z0-9 ]";

    @Test
    @Story("Rich Text Editor Test")
    @Description("Playing with rich text editor")
    public void validateTextInRichTextEditor_Test3() {
        //Given
        logStep("Writing bold and underlined text into Rich text editor");
        RichTextEditorPage editor = new RichTextEditorPage()
                // When
                .clickOnBold().type(AUTOMATION).clickOnBold().type(SPACE).clickOnUnderline().type(TEST_EXAMPLE);

        // Then
        String actualText = editor.getTextBox().getText().replaceAll(NEITHER_ALPHANUMERIC_NOR_SPACE, "");
        Assert.assertEquals(actualText, AUTOMATION + SPACE + TEST_EXAMPLE,
                            AUTOMATION + SPACE + TEST_EXAMPLE + "is missing");
    }
}
