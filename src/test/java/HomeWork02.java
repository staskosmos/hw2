import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class HomeWork02 {

    String url = "https://demoqa.com/automation-practice-form";

    @BeforeAll
    static void BrowserSize() {
        Configuration.browserSize = "1920x3072";
    }

    @Test
    void fillFormTest() {
        open(url);

        $("#firstName").setValue("Stas");
        $("#lastName").setValue("Che");
        $("#userEmail").setValue("s@mail.com");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("3455679809");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("2022");
        $(".react-datepicker__day--003").click();

        $("#subjectsInput").setValue("b").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();

        File file = new File ("src/test/resources/pes.jpg");
        $("#uploadPicture").uploadFile(file);

        $("#currentAddress").setValue("Cherepovets");

        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        $("#react-select-4-input").setValue("Jaipur").pressEnter();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        $(".table-responsive").shouldHave(text("Stas"),
                (text("Che")),
                (text("s@mail.com")),
                (text("Male")),
                (text("3455679809")),
                (text("03 May,2022")),
                (text("Biology")),
                (text("Sports, Reading, Music")),
                (text("pes.jpg")),
                (text("Rajasthan Jaipur")));
    }
}

