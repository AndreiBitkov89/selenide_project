package steps;

import constants.Categories;
import pages.mainpage.CategoryFilter;

import java.util.List;

public class FilterItemsSteps {

    private final CategoryFilter filterPage = new CategoryFilter();

    public List<String> applyFilter(Categories category) {
        return  filterPage.extractTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(category.getMESSAGE())
                )
        );
    }
}
