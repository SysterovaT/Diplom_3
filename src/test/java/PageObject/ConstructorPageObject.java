package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPageObject {
    private final WebDriver driver;


    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By ingredientList = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[contains(@class, 'tab_tab__1SPyG')]");

    public ConstructorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadConstructor() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientList));
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickIngredientListBun() {
        driver.findElements(ingredientList).get(0).click();
    }
    public void clickIngredientListSauce() {
        driver.findElements(ingredientList).get(1).click();
    }
    public void clickIngredientListFilling() {
        driver.findElements(ingredientList).get(2).click();
    }
    public boolean isVisibleIngredientList() {
        return driver.findElement(ingredientList).isDisplayed();
    }

    public boolean isTabSelected(int index) {
        return driver.findElements(ingredientList).get(index).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }


}
