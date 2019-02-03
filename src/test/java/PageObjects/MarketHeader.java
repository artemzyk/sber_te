package PageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class MarketHeader {

    private static final SelenideElement SEARCH_FIELD = $("#header-search"),
                                         FIND_BUTTON = $(".search2__button"),
                                         SEARCH_FIELD_CLEAR_BUTTON = SEARCH_FIELD.$(".input__clear");

    @Step("Type {0} in search field")
    public static void typeSearchQuery(String searchQuery) {

        SEARCH_FIELD
                .should(appear)
                .click();

        if (SEARCH_FIELD_CLEAR_BUTTON.isDisplayed())
            SEARCH_FIELD_CLEAR_BUTTON.click();

        SEARCH_FIELD.sendKeys(searchQuery);
    }

    @Step("Click find button")
    public static void clickFindButton() {

        FIND_BUTTON
                .should(appear)
                .click();
    }
}