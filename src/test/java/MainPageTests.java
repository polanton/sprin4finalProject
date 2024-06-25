import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandexScooter.YaScooterMainPage;
import static org.junit.Assert.assertEquals;

public class MainPageTests {
    //тестовые данные
    public static Object[][] questionAnswerTextList =
        {
                {0, "Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {3, "Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {7,"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };

    @Test
    public void faqTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        YaScooterMainPage objMainPage = new YaScooterMainPage(driver);
        objMainPage.waitUntilAccordionIsLoaded();
        objMainPage.closeCookiesNotification();
        objMainPage.scrollToFAQ();
        //Проверяем что вопросы/ответы из тестовых данных есть на странице
        for (Object[] objects : questionAnswerTextList) {
            assertEquals("Вопрос отличается от ожидаемого", objects[1],
                    objMainPage.getQuestionTextByID((Integer) objects[0]));
            assertEquals("Ответ отличается от ожидаемого", objects[2],
                    objMainPage.getAnswerTextByID((Integer) objects[0]));
        }
        //проверяем количество вопросов на странице
        assertEquals("Количество вопросов отличается от ожидаемого", 8, objMainPage.getQuestionsCount());

        driver.quit();
    }

}
