package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageObject {

    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By loginButton = By.className("button_button__33qZ0");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By restoreLink = By.linkText("Восстановить пароль");


    private final WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
    public void loginRegisterLink() {
        driver.findElement(registerLink).click();
    }
    public void loginRestoreLink() {
        driver.findElement(restoreLink).click();
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
}
