import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class TestTitlePage {
    private WebDriver driver;

    @BeforeEach
    public void before() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--incognito");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(TitlePage.URL);
    }

    @AfterEach
    public void endTest() {
        driver.quit();
    }
    @ParameterizedTest
    @CsvSource({"Москва , улица Павла Корчагина , 3, 1, 1, 1, 1, Можно передать через окно",})  // укажи тестовые данные
    public void Test(String city, String street, String house, String entrance, String door, String floor, String flat, String comment){
        HamAndCheese object2 = new HamAndCheese(driver);
        object2.sendCity(city);
        String elementTitle = object2.checkTitleWebsite();
        assertEquals("Доставка пиццы " + city, elementTitle);
        //object2.clickButtonPizza();
        object2.clickPizzaHamAndCheese();
        object2.waitLoadImagePizza();
        object2.checkNutritionalValue();
        object2.checkSmallSizePizza();
        object2.checkMiddleSizePizza();
        object2.totalAmountWithAttributs();
        object2.addressOrder(city, street, house, entrance, door, floor, flat, comment);
        object2.endOrder();
        //object2.checkPopUpNotification();
        object2.orderComparison();
    }
}
