import POM.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class GoingToBunChapterOnMainPageTest {
    @Test
    @DisplayName("Переход на главной странице в раздел Булки")
    public void ConstructorBunChapter() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.checkBunActive();
        mainPage.clickFillingInActive();
        mainPage.checkBunInActive();
        mainPage.clickBunInActive();
        mainPage.checkBunActive();
    }
}