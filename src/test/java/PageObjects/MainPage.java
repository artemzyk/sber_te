package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static final SelenideElement HORIZONTAL_MENU = $(".home-arrow__tabs"),
                                         HORIZONTAL_MENU_MORE_SERVICES_POPUP = $(".home-tabs__more");

    private static final ElementsCollection HORIZONTAL_MENU_SERVICES_LINKS = HORIZONTAL_MENU.$$("a.home-tabs__link"),
                                            HORIZONTAL_MENU_MORE_SERVICES_LINKS = HORIZONTAL_MENU_MORE_SERVICES_POPUP.
                                                    $$(".home-tabs__more-item a");

    @Step("Click {0} service on main page")
    public static void clickMenuService(String serviceName) {
        SelenideElement menuLink;

        HORIZONTAL_MENU.should(appear);

        menuLink = HORIZONTAL_MENU_SERVICES_LINKS.findBy(text(serviceName));

        if (menuLink.isDisplayed())
            menuLink.click();
        else {

            HORIZONTAL_MENU_SERVICES_LINKS.findBy(text("ещё"))
                    .should(appear)
                    .click();
            HORIZONTAL_MENU_MORE_SERVICES_POPUP.should(appear);
            HORIZONTAL_MENU_MORE_SERVICES_LINKS.findBy(text(serviceName))
                    .should(appear)
                    .click();
        }
    }
}