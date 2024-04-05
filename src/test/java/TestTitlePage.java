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
    @CsvSource({"Москва , улица Павла Корчагина , 3, 1, 1, 1, 1, Можно передать через окно",
            "Екатеринбург, улица Репина, 5, 2, 2, 2, 2, Не тороплюсь"})
    public void Test(String city, String street, String house, String entrance, String door, String floor, String flat, String comment){

        HamAndCheese object2 = new HamAndCheese(driver);

        object2.sendCity(city);
        //проверка на соответствие выбора города
        String elementTitle = object2.checkTitleWebsite();
        assertEquals("Доставка пиццы " + city, elementTitle, "Выбранный город не совпадает со значением титульника");


        object2.clickPizzaHamAndCheese()
                .waitLoadImagePizza();

        // проверка окна пищевой ценности
        String blockWithParametrs = object2.checkNutritionalValue();
        assertAll(
                () -> assertTrue(blockWithParametrs.contains("Энерг"), "Не содержит 'Энерг'"),
                () -> assertTrue(blockWithParametrs.contains("Белки"), "Не содержит 'Белки'"),
                () -> assertTrue(blockWithParametrs.contains("Жиры"), "Не содержит 'Жиры'"),
                () -> assertTrue(blockWithParametrs.contains("Углеводы"), "Не содержит 'Углеводы'"),
                () -> assertTrue(blockWithParametrs.contains("Вес"), "Не содержит 'Вес'"),
                () -> assertTrue(blockWithParametrs.contains("Диаметр"), "Не содержит 'Диаметр'")
        );

        //проверка недоступности выбора тонокого теста
        object2.checkAttributsSmallSizePizza();
        assertNotEquals(object2.getLocatorThinField(), object2.getLocatorTraditionalField(), "Доступен выбор тонкого теста, что является ошибкой");

        //проверка доступности выбора тонкого и традиционного теста
        object2.checkAttributsBigSizePizza();
        assertAll(
                () -> assertTrue(object2.getLocatorThinField().isEnabled(), "Кпонка 'Традиционное' не кликабельна"),
                () -> assertTrue(object2.getLocatorTraditionalField().isEnabled(), "Кпонка 'Тонкое' не кликабельна")
        );

        //проверка доступности выбора тонкого и традиционного теста
        object2.checkAttributsMiddleSizePizza();
        assertAll(
                () -> assertTrue(object2.getLocatorThinField().isEnabled(), "Кпонка 'Традиционное' не кликабельна"),
                () -> assertTrue(object2.getLocatorTraditionalField().isEnabled(), "Кпонка 'Тонкое' не кликабельна")
        );

        // проверка рассчета стоимости продукта после добавления новых ингридиентов
        int defaultValue = object2.getTotalDefaultAmount();
        int addAttributs = object2.getSelectAttributs();
        int endPrice = object2.getTotalAmountAfterSelectAttributs();
        assertEquals(defaultValue + addAttributs, endPrice, "Сумма заказа с добавленным ингридиентом не валидна");

        object2.addressOrder(city, street, house, entrance, door, floor, flat, comment)
                .endOrder();

        // проверка отображения заказа в корзине
        String elementOrderComparison = object2.orderComparison();
        assertEquals("Ветчина и сыр", elementOrderComparison, "Заказанной пиццы нет в корзине");
    }
}
