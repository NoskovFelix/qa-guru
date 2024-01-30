import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest extends BaseTest {

    private static final String BASE_PATH = "/automation-practice-form";


    @Test
    public void fillAllFieldsTest() {

        open(BASE_PATH);

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Test");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("test@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9269500099");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__header").shouldBe(Condition.visible);
        $(".react-datepicker__month-select").selectOption("March");

        var currentYear = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR));

        $(".react-datepicker__year-select").selectOption(currentYear.toString());
        $(".react-datepicker__month").$(byText("14")).click();

        $("#subjectsInput").setValue("Maths").pressEnter();

        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/documents/blankDoc.pdf"));
        $("#currentAddress").setValue("Test address");

        $("#stateCity-wrapper").$("#state").click();
        $("#state").$(byText("Haryana")).click();

        $("#stateCity-wrapper").$("#city").click();
        $("#city").$(byText("Karnal")).click();

        $("#submit").click();

    }
}
