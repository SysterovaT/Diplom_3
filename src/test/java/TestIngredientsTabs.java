import PageObject.ConstructorPageObject;
import PageObject.HeaderPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestIngredientsTabs {

    private WebDriver driver;

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
    public void testFillingTab() {
        constructorPageObject = new ConstructorPageObject(driver);
        constructorPageObject.waitForLoadConstructor();
        constructorPageObject.clickIngredientListFilling();
        Assert.assertTrue(constructorPageObject.isTabSelected(2));
    }

    @Test
    public void testSauceTab() {
        constructorPageObject = new ConstructorPageObject(driver);
        constructorPageObject.waitForLoadConstructor();
        constructorPageObject.clickIngredientListSauce();
        Assert.assertTrue(constructorPageObject.isTabSelected(1));
    }

    @Test
    public void testBunTab() {
        constructorPageObject = new ConstructorPageObject(driver);
        constructorPageObject.waitForLoadConstructor();
        constructorPageObject.clickIngredientListFilling();
        constructorPageObject.clickIngredientListBun();
        Assert.assertTrue(constructorPageObject.isTabSelected(0));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
