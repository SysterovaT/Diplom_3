package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPageObject {

    private final WebDriver driver;

    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public AccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }


}
