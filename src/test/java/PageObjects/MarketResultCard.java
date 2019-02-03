package PageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class MarketResultCard {

    private static final SelenideElement ITEM_CONTAINER = $(".n-product-summary"),
                                         ITEM_TITLE = $(".n-title__text");

    @Step("Wait until item card page loaded")
    public static void waitUntilResultCardLoaded() {

        ITEM_CONTAINER.waitUntil(appear, 10000);
    }

    @Step("Get title on item card page")
    public static String getItemTitle() {

        ITEM_TITLE.should(appear);

        return ITEM_TITLE.getText();
    }
}