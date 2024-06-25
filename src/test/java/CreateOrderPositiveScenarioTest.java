import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandexScooter.OrderFirstFormPage;
import yandexScooter.OrderSecondFormPage;
import yandexScooter.YaScooterMainPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class CreateOrderPositiveScenarioTest {

    //точка входа (одна из кнопок создания заказа)
    int createOrderButtonId;
    //тестовые данные
    String name;
    String surName;
    String adress;
    String metroStation;
    String phone;
    String deliveryDate;
    String rentDuration;
    int colourId;
    String deliveryComment;

    public CreateOrderPositiveScenarioTest(int createOrderButtonId, String name, String surName, String adress,String metroStation, String phone, String deliveryDate,
                                                     String rentDuration, int colourId, String deliveryComment) {
        this.createOrderButtonId = createOrderButtonId;
        this.name = name;
        this.surName = surName;
        this.adress = adress ;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate ;
        this.rentDuration = rentDuration;
        this.colourId = colourId;
        this.deliveryComment = deliveryComment ;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        //тестовые данные
        return new Object[][] {
                //(int createOrderButtonId, name,  surName,  adress, metroStation,  phone,  deliveryDate,rentDuration,  colourId, deliveryComment)
                {0, "Иван","Иванов","Москва, Ленина 69","Бульвар Рокоссовского","89063166338","12122024","четверо суток", 0, "Какая-то информация для курьера"},
                {0, "Пётр","Оченьдлиннаяфамилия","Нью-йорк, Красная площадь 36","Преображенская площадь","89173166338","11.06.2024","сутки", 1, null},
                {1, "Иван","Иванов","Москва, Ленина 69","Бульвар Рокоссовского","89063166338","12122024","четверо суток", 0, "Какая-то информация для курьера"},
                {1, "Пётр","Оченьдлиннаяфамилия","Нью-йорк, Красная площадь 36","Преображенская площадь","89173166338","11.06.2024","сутки", 1, null},
        };
    }


    @Test
    public void orderPositiveScenario() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        YaScooterMainPage objMainPage = new YaScooterMainPage(driver);
        //закрываем панель оповещения о куках
        objMainPage.closeCookiesNotification();
        //Запускаем создание заказа кнопкой в углу
        objMainPage.clickOrderButtonById(createOrderButtonId);
        OrderFirstFormPage objFirstForm = new OrderFirstFormPage(driver);
        //Заполняем первую форму
        objFirstForm.enterName(name);
        objFirstForm.enterSurName(surName);
        objFirstForm.enterAdress(adress);
        objFirstForm.enterPhone(phone);
        objFirstForm.choseMetroStation(metroStation);
        //Переход на вторую форму
        objFirstForm.clickNextButton();
        OrderSecondFormPage objSecondFormPage = new OrderSecondFormPage(driver);

        //Заполняем вторую форму
        objSecondFormPage.enterDeliveryDate(deliveryDate);
        objSecondFormPage.choseRentDuration(rentDuration);
        objSecondFormPage.setColourById(colourId);
        objSecondFormPage.enterDeliveryComment(deliveryComment);
        //Нажимаем Заказать
        objSecondFormPage.clickCreateOrder();
        //Подтверждаем создание заказа
        objSecondFormPage.clickConfirmOrder();

        //Проверяем что отображается сообщение об оформлении заказа
        assertTrue("Сообщение об оформлении заказа отображается некорректно", objSecondFormPage.orderResultIsDisplayed());
        //Убеждаемся что в итоговом сообщении указано, что заказ оформлен
        assertTrue("Результат в сообщении не успешный",  objSecondFormPage.getOrderResultText().contains("Заказ оформлен"));
        driver.quit();
    }


}
