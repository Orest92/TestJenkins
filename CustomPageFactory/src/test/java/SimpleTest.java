import framework.CustomElementLocatorFactory;
import framework.CustomFieldDecorator;
import framework.CustomPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.Assert.assertEquals;

/**
 * Created by Orest on 05.06.2016.
 */
public class SimpleTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void openPage() {
        System.setProperty("webdriver.chrome.driver", "C://Chrome");
        driver = new ChromeDriver();
        driver.get("https://www.google.com.ua");
    }

    @Test
    public void Test1() {
        homePage = CustomPageFactory.initElements(driver, HomePage.class);
        testContext();
    }

    @Test
    public void Test2() {
        homePage = new HomePage(driver);
        CustomPageFactory.initElements(driver, homePage);
        testContext();
    }

    @Test
    public void Test3() {
        homePage = new HomePage(driver);
        CustomPageFactory.initElements(new CustomElementLocatorFactory(driver), homePage);
        testContext();
    }

    @Test
    public void Test4() {
        homePage = new HomePage(driver);
        CustomPageFactory.initElements(new CustomFieldDecorator(new CustomElementLocatorFactory(driver)), homePage);
        testContext();
    }

    private void testContext() {
        assertEquals(driver.getTitle(), "Google", "The page title should equal Google at the start of the test.");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(homePage.searchInput));
        homePage.searchInput.sendKeys("Let's search");
        wait.until(ExpectedConditions.visibilityOf(homePage.searchButton));
        homePage.searchButton.click();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

}
