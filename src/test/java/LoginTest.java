import PageObjects.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static PageObjects.MainPage.mainURL;
import static PageObjects.PasswordRecovery.forgotPasswordURL;
import static PageObjects.ProfilePage.profilePageURL;
import static PageObjects.RegistrationPage.registrationURL;
import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
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
        RegistrationPage registrationPage = open(registrationURL, RegistrationPage.class);
        registrationPage.getInputFieldName().setValue(name);
        registrationPage.getInputFieldEmail().setValue(email);
        registrationPage.getInputFieldPassword().setValue(password);
        registrationPage.clickRegister();
        LoginPage loginPageS = page(LoginPage.class);
        loginPageS.waitForLoadLoginPage();
    }

    @After
    public void tearDown() {
        ProfilePage profilePage = open(profilePageURL, ProfilePage.class);
        profilePage.clickExitButton();
        LoginPage logPage = page(LoginPage.class);
        logPage.waitForLoadLoginPage();
        userClient = new UserClient();
        userClient.deleteAccessToken(accessToken);
        System.out.println("Конец теста");
    }

    @Test
    @DisplayName("Авторизация пользователя с главной страницы через нажатие кнопки Личный кабинет")
    public void loginFromMainPageByClickProfileButton() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страницы авторизации
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя с главной страницы через нажатие кнопки Войти в аккаунт")
    public void loginFromMainPageByClickLoginButton() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.clickLogin();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страница авторизации
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя  " + accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя со страницы регистрации через нажатие кнопки Войти")
    public void loginFromRegistrationPageByClickInputButton() {
        RegistrationPage registrationPage = open(registrationURL, RegistrationPage.class);
        registrationPage.clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страницы авторизации ВХОД
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        MainPage mainPage = page(MainPage.class);
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
    }

    @Test
    @DisplayName("Авторизация пользователя со страницы Восстановление пароля через нажатие кнопки Войти")
    public void authorizationFromRecoveryPasswordPageByClickInputButton() {
        PasswordRecovery forgotPasswordPage = open(forgotPasswordURL, PasswordRecovery.class);
        forgotPasswordPage.clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        //Проверка открытия страницы авторизации
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        MainPage mainPage = page(MainPage.class);
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
    }
}
