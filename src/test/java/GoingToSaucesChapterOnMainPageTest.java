import POM.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class GoingToSaucesChapterOnMainPageTest {
    @Test
    @DisplayName("Переход на главной странице в раздел Соусы")
    public void ConstructorSaucesChapter() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.checkSaucesInActive();
        mainPage.clickSaucesInActive();
        mainPage.checkSaucesActive();
    }
}
