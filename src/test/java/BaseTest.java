import org.conf.Browser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pageobjects.MainPage;

public class BaseTest {
    protected WebDriver driver;


    @Before
    public void startUp() {
        driver = new Browser().getWebDriver("yandex");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public MainPage openMainPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        return mainPage;
    }
}
