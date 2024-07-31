import PageObject.*;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
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

public class TestLogin implements ApiInterface{
    private WebDriver driver;
    private RegisterPageObject registerPageObject;
    private HeaderPageObject headerPageObject;
    private ConstructorPageObject constructorPageObject;
    private RestorePasswordPageObject restorePasswordPageObject;
    private LoginPageObject loginPageObject;

    private CreateUserData userData;
    private ResponseAuthUser userResponse;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
//        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        userData = createUserData();
        userResponse = registerUser(userData);
    }

    @Test
    public void testAccountButtonMain() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        constructorPageObject = new ConstructorPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        constructorPageObject.waitForLoadConstructor();
        constructorPageObject.clickLoginButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(userData.getEmail());
        loginPageObject.setPassword(userData.getPassword());
        loginPageObject.loginButtonClick();
        constructorPageObject.waitForLoadConstructor();
        Assert.assertTrue(constructorPageObject.isVisibleIngredientList());

//        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/"));
//        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void testAccountButtonHeader() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        constructorPageObject = new ConstructorPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        headerPageObject = new HeaderPageObject(driver);
        headerPageObject.clickAccountButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(userData.getEmail());
        loginPageObject.setPassword(userData.getPassword());
        loginPageObject.loginButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/"));
//        System.out.println(loginPageObject.getUrl());
//        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        constructorPageObject.waitForLoadConstructor();
        Assert.assertTrue(constructorPageObject.isVisibleIngredientList());
    }

    @Test
    public void testAccountButtonForgotPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        loginPageObject = new LoginPageObject(driver);
        constructorPageObject = new ConstructorPageObject(driver);
        restorePasswordPageObject = new RestorePasswordPageObject(driver);
        restorePasswordPageObject.clickLoginLink();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(userData.getEmail());
        loginPageObject.setPassword(userData.getPassword());
        loginPageObject.loginButtonClick();
//        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/"));
//        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        constructorPageObject.waitForLoadConstructor();
        Assert.assertTrue(constructorPageObject.isVisibleIngredientList());
    }

    @Test
    public void testLoginLinkWithRegistrationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registerPageObject = new RegisterPageObject(driver);
        constructorPageObject = new ConstructorPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        registerPageObject.clickLoginLink();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(userData.getEmail());
        loginPageObject.setPassword(userData.getPassword());
        loginPageObject.loginButtonClick();
//        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlContains("https://stellarburgers.nomoreparties.site/"));
//        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        constructorPageObject.waitForLoadConstructor();
        Assert.assertTrue(constructorPageObject.isVisibleIngredientList());
    }


    @After
    public void tearDown() {
        driver.quit();
        deleteUser(userResponse.getAccessToken());
    }


}
