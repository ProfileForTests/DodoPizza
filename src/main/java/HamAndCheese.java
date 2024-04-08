import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HamAndCheese extends TitlePage {

    private WebDriver driver;

    public HamAndCheese(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By pizzaHamAndCheeseField = By.xpath("//*[@title='Ветчина и сыр']");
    private By blockOrderPizza = By.xpath("/html/body/div[3]/div/div[2]/div/div");
    private By buttonNutritionalValue = By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]/div/div[1]/div/div/button");
    private By blockNutritionalValue = By.xpath("//*[@class='sc-6k321-0 dEgNfL sc-13bk731-2 etEOJD tooltip']");
    private By smallSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Маленькая']");
    private By middleSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Средняя']");
    private By bigeSizePizza = By.xpath("//*[@data-testid='menu__pizza_size_Большая']");
    private By traditionalField = By.xpath("//*[@class='sc-1rpjq4r-2 fkoocK' and text() = 'Традиционное']");
    private By thinField = By.xpath("//*[@data-disabled='false' and @class='sc-1rpjq4r-2 fkoocK' and text()='Тонкое']");
    private By thinFieldDisabled = By.xpath("//*[@data-disabled='true' and @class='sc-1rpjq4r-2 fkoocK' and text()='Тонкое']");
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
    private By buttonAddAfterAdress = By.xpath("//*[@data-testid='add_or_save_spinner_button']");
    private By orderPizza = By.xpath("//*[@class='sc-1b7shmc-5 jNWDBI']");
    private By cart = By.xpath("//*[@data-testid='navigation__cart']");
    private By buttonNextOrder = By.xpath("//*[@data-testid='cart__button_next']");

    public HamAndCheese clickPizzaHamAndCheese() {
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.elementToBeClickable(pizzaHamAndCheeseField));
        WebElement element = driver.findElement(pizzaHamAndCheeseField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(pizzaHamAndCheeseField).click();
        return this;
    }

    public HamAndCheese waitLoadImagePizza() {
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.visibilityOfElementLocated(blockOrderPizza));
        return this;
    }

    public String checkNutritionalValue() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(buttonNutritionalValue));
        driver.findElement(buttonNutritionalValue).click();
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(blockNutritionalValue));
        WebElement blockWithParametrs = driver.findElement(blockNutritionalValue);
        driver.findElement(buttonNutritionalValue).click();
        return blockWithParametrs.getText();
    }

    public WebElement getLocatorThinField(){
        return driver.findElement(thinField);
    }

    public WebElement getLocatorThinFieldDisable(){
        return driver.findElement(thinFieldDisabled);
    }

    public WebElement getLocatorTraditionalField(){
        return driver.findElement(traditionalField);
    }

    public HamAndCheese checkAttributsSmallSizePizza() {
        driver.findElement(smallSizePizza).click();
        return this;
    }

    public HamAndCheese checkAttributsMiddleSizePizza() {
        driver.findElement(middleSizePizza).click();
        return this;
    }

    public HamAndCheese checkAttributsBigSizePizza() {
        driver.findElement(bigeSizePizza).click();
        return this;
    }

    public int getTotalDefaultAmount(){
        WebElement price = driver.findElement(currentTotalPrice);
        int startingPrice = Integer.parseInt(price.getText());
        return startingPrice;
    }
    public int getSelectAttributs(){
        driver.findElement(priceMocarella).click();
        WebElement cheese = driver.findElement(priceMocarella);
        int cheesePrice = Integer.parseInt(cheese.getText());
        return cheesePrice;
    }

    public int getTotalAmountAfterSelectAttributs(){
        WebElement price = driver.findElement(currentTotalPrice);
        int endPrice = Integer.parseInt(price.getText());
        return endPrice;
    }
    public HamAndCheese setBlockHowAdress() {
        driver.findElement(buttonAddToCart).click();
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(blockHowAdress));
        return this;
    }

    public HamAndCheese addressOrder(String city, String street, String house, String entrance, String door, String floor, String flat, String comment) {
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
        driver.findElement(blockListAdress).click();
        return this;
    }

    public HamAndCheese endOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(buttonAddAfterAdress));
        driver.findElement(buttonAddAfterAdress).click();
        return this;
    }

    public String orderComparison() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.invisibilityOfElementLocated(blockHowAdress));
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.invisibilityOfElementLocated(blockOrderPizza));
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(cart));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(cart).click();
        new WebDriverWait(driver, Duration.ofSeconds(200))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonNextOrder));
        WebElement getTextPizza = driver.findElement(orderPizza);
        return getTextPizza.getText();
    }
}
