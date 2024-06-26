import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandexScooter.YaScooterMainPage;
import static org.junit.Assert.assertEquals;




@RunWith(Parameterized.class)
public class MainPageTests {
    int questionId;
    String questionText;
    String answerText;

    public MainPageTests (int questionId, String questionText, String answerText){
        this.questionId = questionId;
        this.questionText= questionText;
        this.answerText = answerText;
    }
    //тестовые данные
    @Parameterized.Parameters
    public static Object[][] getTestData() {
    return new Object[][]
        {
                //int questionId, String questionText, String answerText
                {0, "Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {3, "Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {7,"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    private static YaScooterMainPage objMainPage ;
    private static WebDriver driver ;

    @BeforeClass
    public static void openMainPage(){
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new YaScooterMainPage(driver);
        objMainPage.waitUntilAccordionIsLoaded();
        objMainPage.closeCookiesNotification();
        objMainPage.scrollToFAQ();

    }

    @Test
    public void faqTest() {
        //Проверка текста вопросов и ответов
        assertEquals("Вопрос отличается от ожидаемого", questionText, objMainPage.getQuestionTextByID(questionId));
        assertEquals("Ответ отличается от ожидаемого", answerText, objMainPage.getAnswerTextByID(questionId));
        //проверяем количество вопросов на странице
        assertEquals("Количество вопросов отличается от ожидаемого", 8, objMainPage.getQuestionsCount());
    }

    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }
}
