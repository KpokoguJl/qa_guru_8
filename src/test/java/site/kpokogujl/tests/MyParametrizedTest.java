package site.kpokogujl.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class MyParametrizedTest {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize="1920x1080";
    }

    static Stream<Arguments> loginTestValueSource() {
        return Stream.of(
                Arguments.of("testtest@test.test", "654321", "NEsergey asdf"),
                Arguments.of("testvova@test.test", "098765", "Vova Test")
        );
    }

    @MethodSource("loginTestValueSource")
    @ParameterizedTest()
    void someTest(String login, String pass, String fullname){
        open("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        $("#email").setValue(login);
        $("#passwd").setValue(pass);
        $("#SubmitLogin").click();

        $$(".header_user_info").find(text(fullname)).shouldBe(visible); //shouldHave(text((fullname)));
        closeWebDriver();
    }
}
