package Tests;

import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class UserTestCase extends TestBase {

    @DataProvider(name = "dataForUserTestCase")
    public Object[][] dataForUserTestCase() {

        return new Object[][]{
                {"Маркет", "Компьютерная техника", "Компьютеры", "Планшеты", "20000", "25000", "Asus"}
        };
    }

    @Test(dataProvider = "dataForUserTestCase", description="Yandex market user test scenario")
    public void userTestCase(String serviceName, String mainCategoryName, String subCategory, String categoryName,
                             String priceFromFilterValue, String priceToFilterValue, String manufacturerName) {
        List<String> resultsTitles;
        String resultTitle,
               cardItemTitle;

        MainPage.clickMenuService(serviceName);
        MarketMenus.clickTopDropdownMenuCategory(mainCategoryName);
        MarketMenus.clickSideMenuCategory(subCategory);
        MarketMenus.clickSideMenuCategory(categoryName);
        MarketContentResults.clickAllFiltersButton();
        MarketAllFilters.setPriceFromValue(priceFromFilterValue);
        MarketAllFilters.setPriceToValue(priceToFilterValue);
        MarketAllFilters.selectManufacturerValue(manufacturerName);
        MarketAllFilters.clickShowAllResultsButton();

        resultsTitles = MarketContentResults.getFirstPageResultsTitles();
        Assert.assertTrue(resultsTitles.size() > 1, String.format("Unable to get second result, " +
                "there are only %d result(s)!", resultsTitles.size()));

        resultTitle = resultsTitles.get(1);

        MarketHeader.typeSearchQuery(resultTitle);
        MarketHeader.clickFindButton();

        MarketResultCard.waitUntilResultCardLoaded();
        cardItemTitle = MarketResultCard.getItemTitle();
        Assert.assertEquals(cardItemTitle, resultTitle, String.format("Expected %s title, actual %s",
                resultTitle, cardItemTitle));
    }
}