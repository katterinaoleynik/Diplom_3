package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static java.time.Duration.ofSeconds;

public class LoginPage {
    public static final String loginURL = "https://stellarburgers.nomoreparties.site/account";
    //Кнопка "Зарегистрироваться"
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement registerLink;

    //Локатор надписи "Вход"
    @FindBy(how = How.XPATH, using = ".//div[@class='Auth_login__3hAey']/h2[text()='Вход']")
    private SelenideElement loginHead;

    //Коллекция инпутов для полей ввода email и password
    @FindBy(how = How.CSS, using = "input.text.input__textfield.text_type_main-default")
    private ElementsCollection inputFieldsForRegistration;

    //Ккнопка "Войти" в форме авторизации
    @FindBy(how = How.XPATH, using = ".//form[@class='Auth_form__3qKeq mb-20']/button[text()='Войти']")
    private SelenideElement loginButton;

    //Клик по кнопке "Зарегистрироваться"
    @Step("Клик по кнопке Зарегистрироваться на странице авторизации")
    public void clickRegisterLink() {
        registerLink.click();
    }

    //Страница авторизации
    @Step("Ожидание загрузки страницы авторизации - ждем появления надписи Вход")
    public void waitForLoadLoginPage() {
        loginHead.shouldBe(Condition.visible, ofSeconds(8));
    }

    //Вернуть поле ввода email
    @Step("Вернуть поле для ввода Email на странице авторизации")
    public SelenideElement getInputFieldEmail() {
        return this.inputFieldsForRegistration.get(0);
    }

    //Вернуть поле ввода password
    @Step("Вернуть поле для ввода Password на странице авторизации")
    public SelenideElement getInputFieldPassword() {
        return this.inputFieldsForRegistration.get(1);
    }

    //Клик на кнопку "Войти"
    @Step("Клик по кнопке Войти на странице авторизации")
    public void clickLogin() {
        loginButton.click();
    }
}