import POM.LoginPage;
import POM.MainPage;
import POM.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationCheckErrorTest {
    private User user;

    @Test
    @DisplayName("Регистрация пользователя с недопустимой длиной пароля(меньше допустимого)")
    public void CheckErrorNotificationByRegisterUserWithUncorrectPassword() {
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
        String password = RandomStringUtils.randomAlphabetic(4);
        registrationPage.getInputFieldName().setValue(name);
        registrationPage.getInputFieldEmail().setValue(email);
        registrationPage.getInputFieldPassword().setValue(password);
        registrationPage.clickRegister();
        registrationPage.incorrectPasswordNotification();
    }
}