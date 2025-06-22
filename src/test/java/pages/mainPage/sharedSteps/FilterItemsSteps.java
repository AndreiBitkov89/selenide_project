package pages.mainPage.sharedSteps;

import enums.ItemCategory;
import pages.mainPage.CategoryFilter;
import pages.mainPage.MainPage;

import java.util.List;

public class FilterItemsSteps {
    private final MainPage mainPage;
    private final CategoryFilter filterPage;

    public FilterItemsSteps(MainPage mainPage, CategoryFilter filterPage) {
        this.mainPage = mainPage;
        this.filterPage = filterPage;
    }

    public List<String> applyFilter(ItemCategory category) {
        return filterPage.getTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(category.getMessage()).getText()
                )
        );
    }
}
