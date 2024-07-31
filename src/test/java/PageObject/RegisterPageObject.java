package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPageObject {
    private final WebDriver driver;

    private final By formDataInput = By.className("input__textfield");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.linkText("Войти");
    private final By errorText = By.className("input__error");


    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElements(formDataInput).get(0).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElements(formDataInput).get(1).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElements(formDataInput).get(2).sendKeys(password);
    }

    public void  clickRegisterButton () {
        driver.findElement(registerButton).click();
    }

    public void  clickLoginLink () {
        driver.findElement(loginLink).click();
    }

    public boolean checkErrorText() {
        return !driver.findElements(errorText).isEmpty();
    }

    public String getErrorText() {
        return driver.findElements(errorText).isEmpty() ? "" : driver.findElement(errorText).getText();
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(formDataInput));
    }

}
