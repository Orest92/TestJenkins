package factory;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Orest_Zamorylo on 4/21/2016.
 */
public interface ElementLocator {
    WebElement findElement();
    List<WebElement> findElements();
}
