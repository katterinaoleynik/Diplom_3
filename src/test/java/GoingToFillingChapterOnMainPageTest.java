import POM.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class GoingToFillingChapterOnMainPageTest {
    @Test
    @DisplayName("Переход на главной странице в раздел Начинки")
    public void ConstructorFillingChapter() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.checkFillingInActive();
        mainPage.clickFillingInActive();
        mainPage.checkFillingActive();
    }
}
