import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;
import pageobjects.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static pageobjects.MainPage.mainURL;
import static pageobjects.ProfilePage.profilePageURL;
import static pageobjects.RegistrationPage.registrationURL;
import static com.codeborne.selenide.Selenide.*;

public class GoingToProfilePageTest {
    private User user;
    private String email;
    private String password;
    private String accessToken;
    private UserClient userClient;

    @Before
    public void setUp() {
        user = User.getRandom();
        String name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
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
        //Удаление созданного пользователя
        userClient = new UserClient();
        userClient.deleteAccessToken(accessToken);
        System.out.println("Конец теста");
    }

    @Test
    @DisplayName("Переход в личный кабинет с главной страницы с авторизацией")
    public void goingToProfileFromMainPageByClickProfileButton() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        //проверка что открылась страница авторизации ВХОД
        loginPage.waitForLoadLoginPage();
        loginPage.getInputFieldEmail().setValue(email);
        loginPage.getInputFieldPassword().setValue(password);
        loginPage.clickLogin();
        //Проверка открытия главной страницы
        mainPage.waitForLoadMainPage();
        //Получение токена
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
        mainPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
    }
}