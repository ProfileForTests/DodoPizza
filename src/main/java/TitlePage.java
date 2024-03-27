import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TitlePage {
    private WebDriver driver;

    public TitlePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String URL = "https://dodopizza.ru/";

    private By sendCityField = By.xpath("//*[@class='locality-selector-popup__search-input input']");
    private By titleText = By.xpath("//*[@class='header__about-slogan']");
    private By buttonPizza = By.xpath("//*[@class='sc-1c0ft0g-0 fPUrRB sc-1uavg9b-7 jzmaFx' and text() = 'Пиццы']");

    public void sendCity(String city) {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(sendCityField));
        driver.findElement(sendCityField).sendKeys(city, Keys.ENTER);
    }

    public String checkTitleWebsite() {
        new WebDriverWait(driver, Duration.ofSeconds(200))
                .until(ExpectedConditions.visibilityOfElementLocated(titleText));
        WebElement elementTitle = driver.findElement(titleText);
        return elementTitle.getText();
    }

    // переход в раздел "Пицца"
    public void clickButtonPizza() {
        new WebDriverWait(driver, Duration.ofSeconds(200))
                .until(ExpectedConditions.elementToBeClickable(buttonPizza));
        driver.findElement(buttonPizza).click();
    }


}
