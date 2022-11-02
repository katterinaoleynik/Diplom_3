import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import java.util.Objects;

import static PageObjects.MainPage.mainURL;
import static PageObjects.ProfilePage.profilePageURL;
import static com.codeborne.selenide.Selenide.*;

public class GoingToMainPageTest {
    private User user;
    private String email;
    private String password;
    private String accessToken;
    private UserClient userClient;

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
    @DisplayName("Переход из личного кабинета на главную страницу через нажатие на логотип Stellar Burgers")
    public void goingToMainPageFromProfilePageByClickLogo() {
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
        accessToken = Objects.requireNonNull(localStorage().getItem("accessToken")).substring(7);
        System.out.println("Токен для удаления созданного пользователя " + accessToken);
        mainPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
        //Переход на главную страницу
        profilePage.clicklogoStellarisBurgers();
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через кнопку Конструктор")
    public void goingToMainPageFromProfileByClickConstructorButton() {
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
        mainPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
        profilePage.clickConstructorButton();
        mainPage.waitForLoadMainPage();
    }
}