import POM.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    private User user;
    private String accessToken;
    private UserClient userClient;

    @After
    public void tearDown() {

        LoginPage logPage = open("https://stellarburgers.nomoreparties.site/account", LoginPage.class);
        String email = user.email;
        String password = user.password;
        logPage.getInputFieldEmail().setValue(email);
        logPage.getInputFieldPassword().setValue(password);
        logPage.clickLogin();
        MainPage mainPageS = page(MainPage.class);
        mainPageS.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя  " + accessToken);
        mainPageS.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
        profilePage.clickExitButton();
        logPage.waitForLoadLoginPage();
        userClient = new UserClient();
        userClient.deleteAccessToken(accessToken);
        System.out.println("Конец теста");
    }

    @Test
    @DisplayName("Регистрация пользователя с корректными параметрами")
    public void registerNewUserWithCorrectParameters() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страницы авторизации
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegisterLink();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.waitForLoadRegisterPage();
        user = User.getRandom();
        String name = user.name;
        String email = user.email;
        String password = user.password;
        registrationPage.getInputFieldName().setValue(name);
        registrationPage.getInputFieldEmail().setValue(email);
        registrationPage.getInputFieldPassword().setValue(password);
        registrationPage.clickRegister();
        loginPage.waitForLoadLoginPage();
    }
}
