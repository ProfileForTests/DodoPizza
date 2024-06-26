import org.openqa.selenium.*;
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

    public TitlePage sendCity(String city) {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(sendCityField));
        driver.findElement(sendCityField).sendKeys(city, Keys.ENTER);
        return this;
    }

    public String checkTitleWebsite() {
        new WebDriverWait(driver, Duration.ofSeconds(200))
                .until(ExpectedConditions.visibilityOfElementLocated(titleText));
        WebElement elementTitle = driver.findElement(titleText);
        return elementTitle.getText();

    }
}
