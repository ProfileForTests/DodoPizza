import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class HamAndCheese extends TitlePage{

    private WebDriver driver;

    public HamAndCheese(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    private By pizzaHamAndCheeseField = By.xpath("//*[@title='Ветчина и сыр']");
    private By blockOrderPizza = By.xpath("/html/body/div[3]/div/div[2]/div/div");
    private By buttonNutritionalValue = By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[1]/div/div/button");
    //private By buttonNutritionalValue = By.xpath("//button[@type='button' and @class='sc-1f9df4k-0 GFPGv']");
    private By blockNutritionalValue = By.xpath("//*[@class='sc-6k321-0 dEgNfL sc-13bk731-2 etEOJD tooltip']");
    private By smallSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Маленькая']");
    private By middleSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Средняя']");
    private By bigeSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Большая']");
    private By traditionalField = By.xpath("//*[@class='sc-1rpjq4r-2 fkoocK' and text() = 'Традиционное']");
    private By thinField = By.xpath("//*[@class='sc-1rpjq4r-2 fkoocK' and text() = 'Тонкое']");
    private By thinFieldDisabled = By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[4]/div[2]/label[2]");
    private By currentTotalPrice = By.xpath("//button[@data-testid='button_add_to_cart']//span[@class='money__value']");
    private By priceMocarella = By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[4]/div[3]/div/section/button[2]/span/span[1]");
    private By buttonAddToCart = By.xpath("//button[@data-testid='button_add_to_cart']");
    private By blockHowAdress = By.xpath("//*[@class='popup-inner undefined']");
    private By specifyTheAddress = By.xpath("//*[@data-testid='how_to_get_order_delivery_action']");
    private By cityStreetHoseField = By.xpath("//*[@data-testid='delivery_placeholder_on_input_street']");
    private By blockListAdress = By.xpath("//*[@data-testid='two-line-list']");
    private By entranceField = By.xpath("//*[@id='animated-input-2']");
    private By doorField = By.xpath("//*[@id='animated-input-3']");
    private By floorField = By.xpath("//*[@id='animated-input-4']");
    private By flatField = By.xpath("//*[@id='animated-input-5']");
    private By commentField = By.xpath("//*[@id='animated-input-6']");
    private By currentAdress = By.xpath("//*[@class=\"item__title\" and text() = 'Москва, улица Павла Корчагина, 3']/parent::li");
    private By buttonAddAfterAdress = By.xpath("//*[@data-testid='add_or_save_spinner_button']");
    private By popUpWindow = By.xpath("//*[@class='sc-1lz859j-1 DktGv notification-enter-done']");
    private By orderPizza = By.xpath("//*[@class='sc-1b7shmc-5 jNWDBI']");
    private By cart = By.xpath("//*[@data-testid='navigation__cart']");
    private By blockCart = By.xpath("//*[@class='sc-1gxjiqu-0 hdUjND']");

    public void clickPizzaHamAndCheese(){
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.elementToBeClickable(pizzaHamAndCheeseField));
        WebElement element = driver.findElement(pizzaHamAndCheeseField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(pizzaHamAndCheeseField).click();
    }

    public void waitLoadImagePizza(){
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.visibilityOfElementLocated(blockOrderPizza));
    }

    public void checkNutritionalValue(){
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(buttonNutritionalValue));
        driver.findElement(buttonNutritionalValue).click();
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(blockNutritionalValue));
        String blockWithParametrs = driver.findElement(blockNutritionalValue).getText();
        assertAll(
                () -> assertTrue(blockWithParametrs.contains("Энерг"), "Не содержит 'Энерг'"),
                () -> assertTrue(blockWithParametrs.contains("Белки"), "Не содержит 'Белки'"),
                () -> assertTrue(blockWithParametrs.contains("Жиры"), "Не содержит 'Жиры'"),
                () -> assertTrue(blockWithParametrs.contains("Углеводы"), "Не содержит 'Углеводы'"),
                () -> assertTrue(blockWithParametrs.contains("Вес"), "Не содержит 'Вес'"),
                () -> assertTrue(blockWithParametrs.contains("Диаметр"), "Не содержит 'Диаметр'")
        );
        driver.findElement(buttonNutritionalValue).click();
    }

    public void checkSmallSizePizza(){
        driver.findElement(smallSizePizza).click();
        assertNotEquals(thinField, thinFieldDisabled);
    }
    public void checkMiddleSizePizza(){
        WebElement middle = driver.findElement(middleSizePizza);
        middle.click();
        WebElement traditionalMiddle = driver.findElement(traditionalField);
        WebElement thinMiddle = driver.findElement(thinField);
        assertAll(
                () -> assertTrue(traditionalMiddle.isEnabled()),
                () -> assertTrue(thinMiddle.isEnabled())
        );
    }
    public void checkBigSizePizza(){
        driver.findElement(bigeSizePizza).click();
        WebElement traditionalMiddle = driver.findElement(traditionalField);
        WebElement thinMiddle = driver.findElement(thinField);
        assertAll(
                () -> assertTrue(traditionalMiddle.isEnabled()),
                () -> assertTrue(thinMiddle.isEnabled())
        );
    }

    // проверка на обновление суммы при добавлении дополнительного ингридиента
    public void totalAmountWithAttributs(){
        WebElement price = driver.findElement(currentTotalPrice);
        int startingPrice = Integer.parseInt(price.getText());
        driver.findElement(priceMocarella).click();
        WebElement cheese = driver.findElement(priceMocarella);
        int cheesePrice = Integer.parseInt(cheese.getText());
        int endPrice = Integer.parseInt(price.getText());
        assertEquals(startingPrice + cheesePrice, endPrice);
    }

    public void setBlockHowAdress(){
        driver.findElement(buttonAddToCart).click();
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(blockHowAdress));
    }
    public void addressOrder(String city, String street, String house, String entrance, String door, String floor, String flat, String comment){
        setBlockHowAdress();
        driver.findElement(specifyTheAddress).click();
        driver.findElement(entranceField).sendKeys(entrance);
        driver.findElement(doorField).sendKeys(door);
        driver.findElement(floorField).sendKeys(floor);
        driver.findElement(flatField).sendKeys(flat);
        driver.findElement(commentField).sendKeys(comment);
        WebElement elements = driver.findElement(cityStreetHoseField);
        elements.sendKeys(city, street, house);
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(blockListAdress));
        driver.findElement(currentAdress).click();
        /*driver.findElement(blockListAdress).click();
        driver.findElement(blockListAdress).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);*/
    }
    public void endOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(buttonAddAfterAdress));
        driver.findElement(buttonAddAfterAdress).click();
    }
    public void checkPopUpNotification(){
        WebElement window = driver.findElement(popUpWindow);
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(popUpWindow));
        assertTrue(window.isDisplayed());
    }

    public void orderComparison(){
        new WebDriverWait(driver,Duration.ofSeconds(100))
                .until(ExpectedConditions.invisibilityOfElementLocated(blockHowAdress));
        
        new WebDriverWait(driver,Duration.ofSeconds(100))
                .until(ExpectedConditions.invisibilityOfElementLocated(blockOrderPizza));
        driver.findElement(cart).click();
        String getTextPizza = driver.findElement(orderPizza).getText();
        assertEquals("Ветчина и сыр", getTextPizza);
    }
}
