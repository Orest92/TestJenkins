package factory;

import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

/**
 * Created by Orest_Zamorylo on 4/21/2016.
 */
public class CustomDefaultElementLocatorFactory implements ElementLocatorFactory{
    private final SearchContext searchContext;

    public CustomDefaultElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new Eleme(searchContext, field);
    }
}
