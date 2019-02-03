package PageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarketContentResults {

    private static final SelenideElement RESULTS_PAGE_ALL_FILTERS_BUTTON = $(By.linkText("Все фильтры"));

    private static final String RESULT_CARD_ITEM_TITLE_SELECTOR = ".n-snippet-card2__title a",
                                RESULTS_CARDS_SELECTOR = ".n-snippet-card2";

    @Step("Click all filters")
    public static void clickAllFiltersButton() {

        RESULTS_PAGE_ALL_FILTERS_BUTTON
                .should(appear)
                .click();
    }

    @Step("Get results titles on the first page")
    public static List<String> getFirstPageResultsTitles() {
        List<String> resultsTitles;

        $$(RESULTS_CARDS_SELECTOR).shouldHave(sizeGreaterThan(0).because("No results"));
        resultsTitles = $$(RESULT_CARD_ITEM_TITLE_SELECTOR).texts();

        return resultsTitles;
    }
}