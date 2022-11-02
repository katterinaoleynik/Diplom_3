import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
import PageObjects.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static PageObjects.MainPage.mainURL;
import static PageObjects.RegistrationPage.registrationURL;
import static com.codeborne.selenide.Selenide.*;

public class LogoutTest {

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
        userClient = new UserClient();
        userClient.deleteAccessToken(accessToken);
        System.out.println("Конец теста");
    }

    @Test
    @DisplayName("Выход из системы со страницы личного кабинета через нажатие кнопки Выход")
    public void logoutFromProfileByClickExitButton() {
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
        profilePage.clickExitButton();
        loginPage.waitForLoadLoginPage();
    }
}