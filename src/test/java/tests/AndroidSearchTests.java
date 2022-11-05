package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {
    @Test
    @DisplayName("Поиск в Wikipedia по слову BrowserStack")
    void searchTestBrowserStack() {
        step("Поиск BrowserStack", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("BrowserStack");
        });
        step("Проверка найденного контента по BrowserStack", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

        step("Удаление введеного текста в поле поиск", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_close_btn")).click());
    }


    @Test
    @DisplayName("Поиск в Wikipedia по слову TestExecute")
    void searchTestTestExecute() {
        step("Поиск TestExecute", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("TestExecute");
        });
        step("Проверка найденного контента по TestExecute", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Удаление введеного текста в поле поиск", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_close_btn")).click());
    }

    @Test
    @DisplayName("Mobile Wikipedia search test")
    void searchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }

    @Test
    @DisplayName("Проверка поиска по первой букве D")
    void LetterSearch() {
        step("Ввод буквы D в поиске", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("D");
        });
        step("Проверка результата", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text("Letter of the Latin alphabet"));
        });
    }


    @Test
    @DisplayName("Проверка скрытия функции карточки")
    void hideThisCard() {
        step("Нажать на меню действии карточки", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/view_list_card_header_menu")).click();
        });
        step("Нажать на функцию скрытия карточки", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).shouldHave(exactText("Hide this card"));
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).click();
        });

    }

    @Test
    @DisplayName("Проверка языковых настроек")
    void settingsTest() {
        step("Открыть вкладку настроек вики", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/explore_overflow_settings")).click();
        });
        step("Нажмите на языковые настройки Википедии.", () -> {
            $(AppiumBy.id("android:id/title")).click();
        });
        step("Поиск французского языка", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/preference_languages_filter")).sendKeys("French");
        });
        step("Установка французского языка", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/language_subtitle")).click();
        });
        step("Проверка, что выбранн язык в настройках французский", () -> {
            $(AppiumBy.id("android:id/summary")).shouldHave(exactText("Français"));
        });


    }
}
