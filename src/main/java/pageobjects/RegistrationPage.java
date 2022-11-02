package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static java.time.Duration.ofSeconds;

public class RegistrationPage {

    public static final String registrationURL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор надписи "Регистрация" для проверки открытия страницы
    @FindBy(how = How.XPATH, using = ".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']")
    private SelenideElement registerHead;

    //Коллекция инпутов с полями ввода email,password и name
    @FindBy(how = How.CSS, using = "input.text.input__textfield.text_type_main-default")
    private ElementsCollection inputFieldsForRegistration;

    //Кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//form[@class='Auth_form__3qKeq mb-20']/button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //Кнопка Войти на странице регистрации
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement loginLink;

    //Подсказка о некорректном пароле
    @FindBy(how = How.XPATH, using = ".//div[@class='input__container']/p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordError;

    //Метод ожидания загрузки страницы: проверили видимость надписи "Регистрация"
    @Step("Ожидание загрузки страницы регистрации - ожидание появления надписи Регистрация")
    public void waitForLoadRegisterPage() {
        registerHead.shouldBe(Condition.visible, ofSeconds(8));
    }

    //Вернуть поле ввода name
    @Step("Вернуть поле для ввода Name на странице регистрации")
    public SelenideElement getInputFieldName() {
        return this.inputFieldsForRegistration.get(0);
    }

    //Вернуть поле ввода password
    @Step("Вернуть поле для ввода Password на странице регистрации")
    public SelenideElement getInputFieldPassword() {
        return this.inputFieldsForRegistration.get(2);
    }

    //Вернуть поле ввода email
    @Step("Вернуть поле для ввода Email на странице регистрации")
    public SelenideElement getInputFieldEmail() {
        return this.inputFieldsForRegistration.get(1);
    }

    //Нажатие на кнопку Зарегистрироваться
    @Step("Клик по кнопке Зарегистрироваться на странице регистрации")
    public void clickRegister() {
        registerButton.click();
    }

    //Клик по кнопке "Войти"
    @Step("Клик по кнопке Войти на странице регистрации")
    public void clickLoginLink() {
        loginLink.click();
    }

    //Видимость подсказки о некорректном пароле
    @Step("Проверка видимости подсказки о невалидном пароле на странице регистрации")
    public void incorrectPasswordNotification() {
        incorrectPasswordError.shouldBe(Condition.visible);
    }

}