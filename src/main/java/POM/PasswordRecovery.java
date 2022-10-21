package POM;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecovery {

    //Кнопка Войти на странице "восстановление пароля"
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement loginLink;

    //Клик по ссылке
    @Step("Клик по кнопке Войти на странице восстановления пароля")
    public void clickLoginLink() {
        loginLink.click();
    }
}
