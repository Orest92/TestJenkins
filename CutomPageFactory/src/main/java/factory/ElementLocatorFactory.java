package factory;

import java.lang.reflect.Field;

/**
 * Created by Orest_Zamorylo on 4/21/2016.
 */
public interface ElementLocatorFactory {
    ElementLocator createLocator(Field field);
}
