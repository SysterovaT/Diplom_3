package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPageObject {
    private By constructorButton = By.className("AppHeader_header__link__3D_hX");
    private By mainLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private By accountButton = By.xpath(".//nav/a[@class='AppHeader_header__link__3D_hX']");

    private final WebDriver driver;

    public HeaderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(mainLogo));
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickMainLogo () {
        driver.findElement(mainLogo).click();
    }

    public void clickAccountButton () {
        driver.findElement(accountButton).click();
    }
}
