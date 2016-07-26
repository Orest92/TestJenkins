package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Orest_Zamorylo on 4/21/2016.
 */
public class CustomPageFactory {
    public static void initElements(WebDriver driver, Object page) {
        final WebDriver driverRef = driver;
        initElements(new CustomDefaultElementLocatorFactory(driverRef), page);
    }

    public static void initElements(org.openqa.selenium.support.pagefactory.ElementLocatorFactory factory, Object page) {
        final org.openqa.selenium.support.pagefactory.ElementLocatorFactory factoryRef = factory;
        initElements(new DefaultFieldDecorator(factoryRef), page);
    }

    public static void initElements(FieldDecorator decorator, Object page) {
        Class<?> proxyIn = page.getClass();
        while (proxyIn != Object.class) {
            proxyFields(decorator, page, proxyIn);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    public static void initElements(CustomDefaultElementLocatorFactory factory, Object page) {
        final CustomDefaultElementLocatorFactory factoryRef = factory;
        initElements(new CustomDefaultFieldDecorator(factoryRef), page);
    }


    private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
