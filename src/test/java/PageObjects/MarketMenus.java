package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarketMenus {

    private static final SelenideElement TOP_MENU_CATEGORIES_CONTAINER = $(".n-w-tabs__horizontal-tabs"),
                                         TOP_MENU_ALL_CATEGORIES_BUTTON = TOP_MENU_CATEGORIES_CONTAINER.
                                                 $$(".n-w-tab").findBy(text("Все категории"));
    private static final ElementsCollection TOP_DROPDOWN_MENU_CATEGORIES_LINKS = $(".n-w-tabs__vertical-tabs").
                                                $$(".n-w-tab a"),
                                            SIDE_MENU_CATEGORIES_LINKS = $$("div[data-zone-name=\"link\"] a");

    @Step("Click all categories button")
    private static void clickAllCategoriesButton() {

        TOP_MENU_ALL_CATEGORIES_BUTTON.should(appear).click();
    }

    @Step("Click {0} category in drop down menu")
    public static void clickTopDropdownMenuCategory(String categoryName) {

        clickAllCategoriesButton();

        TOP_DROPDOWN_MENU_CATEGORIES_LINKS.shouldHave(sizeGreaterThan(0));

        TOP_DROPDOWN_MENU_CATEGORIES_LINKS.findBy(text(categoryName))
                .should(appear)
                .click();
    }

    @Step("Click {0} category in side menu")
    public static void clickSideMenuCategory(String categoryName) {

        SIDE_MENU_CATEGORIES_LINKS.shouldHave(sizeGreaterThan(0));

        SIDE_MENU_CATEGORIES_LINKS.findBy(text(categoryName))
                .should(appear)
                .click();
    }
}