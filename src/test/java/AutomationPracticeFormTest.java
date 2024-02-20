import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest extends BaseTest {

    private static final String AUTOMATION_PRACTICE_FORM = "/automation-practice-form";


    @Test
    public void fillAllFieldsTest() {

        open(AUTOMATION_PRACTICE_FORM);

        var name = "Test";
        var lastName = "Testov";
        var email = "test@test.com";
        var gender = "Male";
        var phoneNumber = "9269500099";

        var month = "March";
        var year = "2000";
        var day = "14";

        var subject = "Maths";
        var hobby = "Reading";
        var docName = "blankDoc.pdf";
        var address = "Test address";
        var state = "Haryana";
        var city = "Karnal";

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__header").shouldBe(Condition.visible);
        $(".react-datepicker__month-select").selectOption(month);

        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month").$(byText(day)).click();

        $("#subjectsInput").setValue(subject).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby)).click();

        $("#uploadPicture").uploadFromClasspath("documents/" + docName);
        $("#currentAddress").setValue(address);

        $("#stateCity-wrapper").$("#state").click();
        $("#state").$(byText(state)).click();

        $("#stateCity-wrapper").$("#city").click();
        $("#city").$(byText(city)).click();

        $("#submit").click();


        $(".table").shouldHave(text(name + " " + lastName));
        $(".table").shouldHave(text(email));
        $(".table").shouldHave(text(gender));
        $(".table").shouldHave(text(phoneNumber));
        $(".table").shouldHave(text(String.format("%s %s,%s", day, month, year)));
        $(".table").shouldHave(text(subject));
        $(".table").shouldHave(text(hobby));
        $(".table").shouldHave(text(docName));
        $(".table").shouldHave(text(address));
        $(".table").shouldHave(text(String.format("%s %s", state, city)));
    }
}
