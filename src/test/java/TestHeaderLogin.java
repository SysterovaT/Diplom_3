import PageObject.ConstructorPageObject;
import PageObject.HeaderPageObject;
import PageObject.LoginPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestHeaderLogin {
    private WebDriver driver;
    private HeaderPageObject headerPageObject;
    private LoginPageObject loginPageObject;
    private ConstructorPageObject constructorPageObject;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void testAccountClick() {
        headerPageObject = new HeaderPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        headerPageObject.clickAccountButton();
        loginPageObject.waitForLoad();
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
    }

    @Test
    public void testClickLogoFromAccount() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        loginPageObject = new LoginPageObject(driver);
        loginPageObject.waitForLoad();
        headerPageObject = new HeaderPageObject(driver);
        constructorPageObject = new ConstructorPageObject(driver);
        headerPageObject.waitForLoadHeader();
        headerPageObject.clickMainLogo();
        constructorPageObject.waitForLoadConstructor();
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
