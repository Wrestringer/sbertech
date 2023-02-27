package cloud.autotests.tests.sberTech;

import cloud.autotests.config.cberTech.App;
import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Apply tests")
public class ApplyForJob extends TestBase {

    @BeforeAll
    static void configureBaseUrl() {
        Configuration.baseUrl = App.config.webUrl();
    }

    @Test
    @Tag("sbertech")
    @DisplayName("Successful apply for a job QA Engineer")
    void applyTest() {

        step("Open main page", () -> {
            open("/");
        });

        step("Open vacancies page", () -> {
            $("[target='blank']").click();
        });

        step("Check the successful transition to the job site", () -> {
            Selenide.switchTo().window("Поиск вакансий");
            $(".Text-sc-36c35j-0.etedRj").shouldHave(text("Вакансии"));
        });

        step("Search 'QA Engineer'", () ->
                $("[placeholder='Какую специальность ищешь?']").setValue("QA Engineer")).pressEnter();

        step("Choose job 'Middle QA Engineer (manual+auto)'", () -> {
            $(byText("Middle QA Engineer (manual+auto)")).click();
        });

        step("Check the successful transition to the job page", () ->{
            Selenide.switchTo().window("Поиск вакансий: Middle QA Engineer (manual+auto)");
            $(".Text-sc-36c35j-0.etedRj").shouldHave(text("Middle QA Engineer (manual+auto)"));
        });

        step("Fill form", () -> {
            $("[placeholder='Фамилия']").setValue("Мартюшев");
            $("[placeholder='Имя']").setValue("Данил");
            $("[placeholder='Email']").setValue("martyusheff.danil@yandex.ru");
            $("[placeholder='Телефон']").setValue("79028020904");
        });

        step("Attach CV", () -> {
            File CV = new File("src/test/resources/Мартюшев Данил Александрович.pdf");
            $("input[type='file']").uploadFile(CV);
        });

        step("Check the successful upload CV", () -> {
            $(".Text-sc-36c35j-0.okCCd.fileNameText").shouldHave(text("Мартюшев Данил Александрович.pdf"));
        });

        step("Give consent to the processing of personal data", () -> {
            $(".styled__IconWrapper-sc-l642s6-2.gjQTYa").click();
        });

        //Данный шаг закоменчен, потому что вакансия была найдена на hh и не соответствует описанию https://hh.ru/vacancy/76846039
        step("Click to the button 'Откликнуться'", () -> {
            //$(".styled__IconWrapper-sc-l642s6-2.gjQTYa").click();
        });
    }
}
