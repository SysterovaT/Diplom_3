package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPageObject {
    private final WebDriver driver;
    private final By loginLink = By.linkText("Войти");
    private final By emailField = By.className("input__textfield");
    private final By restoreButton = By.className("button_button__33qZ0");


    public RestorePasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void  clickLoginLink () {
        driver.findElement(loginLink).click();
    }

    public void  clickRestoreButton () {
        driver.findElement(restoreButton).click();
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(restoreButton));
    }
}
