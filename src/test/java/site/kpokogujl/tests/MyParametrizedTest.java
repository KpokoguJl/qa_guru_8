package site.kpokogujl.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class MyParametrizedTest {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize="1920x1080";
    }

    static Stream<Arguments> secondLoginTestValueSource() {
        return Stream.of(
                Arguments.of("saa", "654321", "Your username is invalid!"),
                Arguments.of("tomsmith", "SuperSecretPassword!", "You logged into a secure area!")
        );
    }

    @MethodSource("secondLoginTestValueSource")
    @ParameterizedTest()
    void authTest(String username, String password, String expectedResult){
        open("http://the-internet.herokuapp.com/login");
        $("#username").setValue(username);
        $("#password").setValue(password);
        $(".radius").click();

        $("#flash").shouldHave(text(expectedResult)).shouldBe(visible); //shouldHave(text((fullname)));
        closeWebDriver();
    }

    @ValueSource(strings = {"Name1", "Name2"})
    @ParameterizedTest()
    void checkboxTest(String name){
        open("https://demoqa.com/text-box");

        $("#userName").setValue(name);
        $("#submit").click();

        $("#name").shouldHave(text(name));
    }

    @CsvSource(value = {
            "Name, 123",
            "Surname, 987"
    })
    @ParameterizedTest()
    void checkboxTest(String name, int address){
        open("https://demoqa.com/text-box");

        $("#userName").setValue(name);
        $("#permanentAddress").setValue(String.valueOf(address));
        $("#submit").click();

        $("#output").$("#name").shouldHave(text(name));
        $("#output").$("#permanentAddress").shouldHave(text(String.valueOf(address)));
    }
}
