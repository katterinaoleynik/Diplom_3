import pageobjects.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static pageobjects.MainPage.mainURL;
import static com.codeborne.selenide.Selenide.open;

public class GoingToChapterOnMainPageTest {

    @Test
    @DisplayName("Переход на главной странице в раздел Булки")
    public void constructorBunChapter() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.checkBunActive();
        mainPage.clickFillingInActive();
        mainPage.checkBunInActive();
        mainPage.clickBunInActive();
        mainPage.checkBunActive();
    }

    @Test
    @DisplayName("Переход на главной странице в раздел Начинки")
    public void constructorFillingChapter() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.checkFillingInActive();
        mainPage.clickFillingInActive();
        mainPage.checkFillingActive();
    }

    @Test
    @DisplayName("Переход на главной странице в раздел Соусы")
    public void constructorSaucesChapter() {
        MainPage mainPage = open(mainURL, MainPage.class);
        mainPage.checkSaucesInActive();
        mainPage.clickSaucesInActive();
        mainPage.checkSaucesActive();
    }
}

