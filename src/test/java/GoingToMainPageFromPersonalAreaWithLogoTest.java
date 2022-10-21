import POM.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class GoingToMainPageFromPersonalAreaWithLogoTest {
    private User user;
    private String email;
    private String password;
    private String accessToken;
    private UserClient userClient;

    @Before
    public void setUp() {
        user = User.getRandom();
        String name = user.name;
        email = user.email;
        password = user.password;
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/register",
                RegistrationPage.class);
        registrationPage.getInputFieldName().setValue(name);
        registrationPage.getInputFieldEmail().setValue(email);
        registrationPage.getInputFieldPassword().setValue(password);
        registrationPage.clickRegister();
        LoginPage loginPageS = page(LoginPage.class);
        loginPageS.waitForLoadLoginPage();
    }

    @After
    public void tearDown() {
        ProfilePage profilePage = open("https://stellarburgers.nomoreparties.site/account",
                ProfilePage.class);
        profilePage.clickExitButton();
        LoginPage logPage = page(LoginPage.class);
        logPage.waitForLoadLoginPage();
        userClient = new UserClient();
        userClient.deleteAccessToken(accessToken);
        System.out.println("Конец теста");
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через нажатие на логотип Stellar Burgers")
    public void goingToMainPageFromProfilePageByClickLogo() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страницы авторизации
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
        mainPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
        //Переход на главную страницу
        profilePage.clicklogoStellarisBurgers();
        mainPage.waitForLoadMainPage();
    }
}