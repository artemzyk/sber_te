package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarketAllFilters {
    private static final ElementsCollection FILTERS_HEADERS = $$(".n-filter-block__header .title__content");

    private static final String FILTER_BLOCK_SELECTOR = ".n-filter-block",
                                FILTER_VALUE_BLOCK = ".n-filter-block__item",
                                FILTER_VALUE_CHECKBOX_SELECTOR = ".checkbox__label",
                                FILTER_SHOW_ALL_VALUES_SELECTOR = "div.n-filter-block__list_type_more button";

    private static final SelenideElement PRICE_FROM_FIELD = $("#glf-pricefrom-var"),
                                         PRICE_TO_FIELD = $("#glf-priceto-var"),
                                         MANUFACTURER_FILTER_BLOCK = FILTERS_HEADERS.findBy(text("Производитель"))
                                                 .closest(FILTER_BLOCK_SELECTOR),
                                         SHOW_ALL_RESULTS_BUTTON = $("a.button_action_show-filtered");

    @Step("Set filter price from value {0}")
    public static void setPriceFromValue(String value) {

        PRICE_FROM_FIELD
                .should(appear)
                .click();
        PRICE_FROM_FIELD.sendKeys(value);
    }

    @Step("Set filter price to value {0}")
    public static void setPriceToValue(String value) {

        PRICE_TO_FIELD
                .should(appear)
                .click();
        PRICE_TO_FIELD.sendKeys(value);
    }

    @Step("Select {0} in manufacturer filter")
    public static void selectManufacturerValue(String manufacturerName) {
        SelenideElement filterValueBlock,
                        showAllValuesButton;

        filterValueBlock = MANUFACTURER_FILTER_BLOCK.$$(FILTER_VALUE_BLOCK).findBy(text(manufacturerName));

        if (!filterValueBlock.isDisplayed()) {

            showAllValuesButton = MANUFACTURER_FILTER_BLOCK.$(FILTER_SHOW_ALL_VALUES_SELECTOR);
            if (showAllValuesButton.isDisplayed())
                showAllValuesButton.click();
            filterValueBlock = MANUFACTURER_FILTER_BLOCK.$$(FILTER_VALUE_BLOCK).findBy(text(manufacturerName));
        }

        filterValueBlock.should(exist.because(String.format("Not found filter value %s", manufacturerName)));
        filterValueBlock.$(FILTER_VALUE_CHECKBOX_SELECTOR).should(appear).click();
    }

    @Step("Click show all results")
    public static void clickShowAllResultsButton() {

        SHOW_ALL_RESULTS_BUTTON
                .should(appear)
                .click();
    }
}