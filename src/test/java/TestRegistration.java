import PageObject.RegisterPageObject;
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
import java.util.Random;

public class TestRegistration {

    private WebDriver driver;
    private RegisterPageObject registerPageObject;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.waitForLoad();
    }

    @Test
    public void testRegistration() {
        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String name = "Test" + random.nextInt(10000000);
        String password = "password";
        registerPageObject.setName(name);
        registerPageObject.setEmail(email);
        registerPageObject.setPassword(password);
        registerPageObject.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/login"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
    }

    @Test
    public void testErrorPasswordRegistration() {
        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String name = "Test" + random.nextInt(10000000);
        String password = "pass";
        registerPageObject.setName(name);
        registerPageObject.setEmail(email);
        registerPageObject.setPassword(password);
        registerPageObject.clickRegisterButton();
        Assert.assertTrue(registerPageObject.checkErrorText());
        Assert.assertEquals(registerPageObject.getErrorText(), "Некорректный пароль");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
