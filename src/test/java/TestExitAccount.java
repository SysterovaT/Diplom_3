import PageObject.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestExitAccount implements ApiInterface{

    private WebDriver driver;

    private HeaderPageObject headerPageObject;
    private ConstructorPageObject constructorPageObject;
    private LoginPageObject loginPageObject;
    private AccountPageObject accountPageObject;

    private CreateUserData userData;
    private ResponseAuthUser userResponse;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        userData = createUserData();
        userResponse = registerUser(userData);
    }

    @Test
    public void testExitFromAccount() {

        headerPageObject = new HeaderPageObject(driver);
        constructorPageObject = new ConstructorPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        accountPageObject = new AccountPageObject(driver);
        constructorPageObject.waitForLoadConstructor();
        constructorPageObject.clickLoginButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(userData.getEmail());
        loginPageObject.setPassword(userData.getPassword());
        loginPageObject.loginButtonClick();
        headerPageObject.waitForLoadHeader();
        headerPageObject.clickAccountButton();
        accountPageObject.waitForLoad();
        accountPageObject.clickExitButton();
        loginPageObject.waitForLoad();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/login"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");

    }


    @After
    public void tearDown() {
        driver.quit();
        deleteUser(userResponse.getAccessToken());
    }
}
