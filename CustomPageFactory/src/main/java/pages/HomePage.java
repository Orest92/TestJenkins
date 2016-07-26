package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Orest on 05.06.2016.
 */
public class HomePage {
    private WebDriver driver;

    @FindBy(id="lst-ib")
    public WebElement searchInput;

    @FindBy(name="btnG")
    public WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
}
