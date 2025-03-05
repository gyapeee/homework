import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RichTextEditorPage;
import runner.TestBase;

@Slf4j
public class RichTextEditorTest extends TestBase {

    private static final String AUTOMATION = "Automation";
    private static final String TEST_EXAMPLE = "Test Example";
    private static final String SPACE = " ";
    private static final String NEITHER_ALPHANUMERIC_NOR_SPACE = "[^a-zA-Z0-9 ]";

    @Test
    public void validateTextInRichTextEditor_Test3() {
        //Given
        RichTextEditorPage editor = new RichTextEditorPage()
                // When
                .clickOnBold()
                .type(AUTOMATION)
                .clickOnBold()
                .type(SPACE)
                .clickOnUnderline()
                .type(TEST_EXAMPLE);

        // Then
        String actualText = editor.getTextBox().getText().replaceAll(NEITHER_ALPHANUMERIC_NOR_SPACE, "");
        Assert.assertEquals(actualText, AUTOMATION + SPACE + TEST_EXAMPLE,
                AUTOMATION + SPACE + TEST_EXAMPLE + "is missing");
    }
}
