package POM;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static java.time.Duration.ofSeconds;

public class ProfilePage {

    //Локатор ссылки "Профиль"
    @FindBy(how = How.LINK_TEXT, using = "Профиль")
    private SelenideElement accountLink;

    //Логотип Stellar Burgers для перехода на главную страницу
    @FindBy(how = How.XPATH, using = ".//a[@href='/']")
    private SelenideElement logoStellarisBurgers;

    //Кнопка Конструктор в Личном Кабинете
    @FindBy(how = How.XPATH, using = ".//a[@href='/']")
    private SelenideElement constructorButton;

    //Кнопка Выход в личном кабинете
    @FindBy(how = How.XPATH, using = ".//li[@class='Account_listItem__35dAP']/button[text()='Выход']")
    private SelenideElement exitButton;

    //Ожидание загрузки страницы личного кабинета:ждем появления надписи "Профили"
    @Step("Ожидание загрузки страницы личного кабинета: ожидание появления надписи Профили")
    public void waitForLoadProfilePage() {
        accountLink.shouldBe(Condition.visible, ofSeconds(8));
    }

    //Клик по логотипу Stellar Burgers
    @Step("Клик по логотипу Stellar Burgers на странице личного кабинета")
    public void clicklogoStellarisBurgers() {
        logoStellarisBurgers.click();
    }

    //Клик по кнопке Конструктор
    @Step("Клик по кнопке Конструктор на странице личного кабинета")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    //Клик по кнопке Выход
    @Step("Клик по кнопке Выход на странице личного кабинета")
    public void clickExitButton() {
        exitButton.click();
    }
}