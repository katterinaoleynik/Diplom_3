package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static java.time.Duration.ofSeconds;

public class MainPage {

    public static final String mainURL = "https://stellarburgers.nomoreparties.site";

    //Кнопка "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profileButton;

    //Кнопка "Войти" в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    //Локатор надписи "Соберите бургер" для проверки открытия главной страницы
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement assembleABurger;

    //Булки в конструкторе активны
    @FindBy(how = How.XPATH, using = "//div[@class=\\\"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect\\\"]" +
            "/span[text()=\\\"Булки\\\"]")
    private SelenideElement bunIsActive;

    //Булки в конструкторе неактивны
    @FindBy(how = How.XPATH, using = "//span[@class=\\\"text text_type_main-default\\\" " +
            "and text()=\\\"Булки\\\"]")
    private SelenideElement bunIsInActive;

    //Соусы в конструкторе активны
    @FindBy(how = How.XPATH, using = "//div[@class=\\\"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect\\\"]" +
            "/span[text()=\\\"Соусы\\\"]")
    private SelenideElement saucesIsActive;

    //Соусы в конструкторе неактивны
    @FindBy(how = How.XPATH, using = "//span[@class=\\\"text text_type_main-default\\\" " +
            "and text()=\\\"Соусы\\\"]")
    private SelenideElement saucesIsInActive;

    //Начинки в конструкторе активны
    @FindBy(how = How.XPATH, using = "//div[@class=\\\"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect\\\"]" +
            "/span[text()=\\\"Начинки\\\"]")
    private SelenideElement fillingsIsActive;

    //Начинки в конструкторе неактивны
    @FindBy(how = How.XPATH, using = "//span[@class=\\\"text text_type_main-default\\\" " +
            "and text()=\\\"Начинки\\\"]")
    private SelenideElement fillingsIsInActive;

    //Клик по кнопке Личный кабинет
    @Step("Клик по кнопке Личный кабинет на главной странице")
    public void clickProfileButton() {
        profileButton.click();
    }

    //Клик по кнопке "Войти"
    @Step("Клик по кнопке Войти на главной странице")
    public void clickLogin() {
        loginButton.click();
    }

    //Метод ожидания загрузки страницы: проверить видимость надписи "Соберите бургер"
    @Step("Ожидание загрузки главной страницы: ожидание появления надписи Соберите бургер")
    public void waitForLoadMainPage() {
        assembleABurger.shouldBe(Condition.visible, ofSeconds(8));
    }

    //Проверка видимости раздела с булками
    @Step("Проверка видимости активного раздела Булки в конструкторе на главной странице")
    public void checkBunActive() {
        bunIsActive.shouldBe(Condition.visible);
    }

    //Проверка неактивности раздела Булки
    @Step("Проверка видимости неактивной вкладки Булки в конструкторе на главной странице")
    public void checkBunInActive() {
        bunIsInActive.shouldBe(Condition.visible);
    }

    //Клик по неактивному разделу Булки
    @Step("Клик по неактивному разделу Булки на главной странице")
    public void clickBunInActive() {
        bunIsInActive.click();
    }

    //Проверка видимости раздела Соусы
    @Step("Проверка видимости активного раздела Соусы в конструкторе на главной странице")
    public void checkSaucesActive() {
        saucesIsActive.shouldBe(Condition.visible);
    }

    //Проверка неактивности раздела Соусы
    @Step("Проверка видимости неактивного раздела Соусы в конструкторе на главной странице")
    public void checkSaucesInActive() {
        saucesIsInActive.shouldBe(Condition.visible);
    }

    //Клик по неактивному разделу Соусы
    @Step("Клик по неактивному разделу Соусы на главной странице")
    public void clickSaucesInActive() {
        saucesIsInActive.click();
    }

    //Проверка видимости раздела Начинки
    @Step("Проверка видимости активной вкладки Начинки в конструкторе на главной странице")
    public void checkFillingActive() {
        fillingsIsActive.shouldBe(Condition.visible);
    }

    //Проверка неактивности раздела Начинки
    @Step("Проверка видимости неактивного раздела Начинки в конструкторе на главная странице")
    public void checkFillingInActive() {
        fillingsIsInActive.shouldBe(Condition.visible);
    }

    //Клик по неактивному разделу Начинки
    @Step("Клик по неактивной вкладке Начинки на главной странице")
    public void clickFillingInActive() {
        fillingsIsInActive.click();
    }

}