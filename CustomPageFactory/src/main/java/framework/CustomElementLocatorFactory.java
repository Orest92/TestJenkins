package framework;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Created by Orest on 05.06.2016.
 */

public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;

    public CustomElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new CustomElementLocator(searchContext, field);
    }
}
