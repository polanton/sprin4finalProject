package yandexScooter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By; //импортировали класс By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class YaScooterMainPage {

    private final WebDriver driver;

    public YaScooterMainPage(WebDriver driver){
        this.driver = driver;
    }

    //Вопросы о важном. Элемент вопрос.
    private final By importantQuestions = By.xpath(".//div[@class='accordion__button']");
    //Вопросы о важном. Элемент ответ
    private final By importantQuestionsAnswer = By.xpath(".//div[@class='accordion__panel']/p");
    //Кнопка "Заказать" в правом верхнем углу
    private final By topRightOrderButton = By.className("Button_Button__ra12g");
    //Кнопка "Заказать" в середине страницы
    private final By centerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопки Заказать
    private final By orderButtons = By.xpath(".//button[text()='Заказать']");
    //Кнопка закрытия панели с куки
    private final By closeCookiesNotificationButton = By.xpath(".//button[text()='да все привыкли']");

    public void waitUntilAccordionIsLoaded (){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(driver.findElements(importantQuestions).get(7)));
    }

    public void waitUntilAnswerIsVisible(int answerId){
       WebElement element = driver.findElements(importantQuestionsAnswer).get(answerId);
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToFAQ(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(importantQuestions).get(7));
    }

    public void clickQuestionByID (int questionID){
        driver.findElements(importantQuestions).get(questionID).click();
    }

    public String getQuestionTextByID (int questionID){
        return   driver.findElements(importantQuestions).get(questionID).getText();
    }

    public String getAnswerTextByID (int questionID){
        clickQuestionByID(questionID);
        waitUntilAnswerIsVisible(questionID);
        return driver.findElements(importantQuestionsAnswer).get(questionID).getText();
    }


    public int getQuestionsCount (){
        return driver.findElements(importantQuestions).size();
    }

    public void clickTopRightOrderButton(){
        driver.findElement(topRightOrderButton).click();
    }
    public void clickCenterOrderButton(){
        driver.findElement(centerOrderButton).click();
    }
    public void clickOrderButtonById(int orderButtonNumber){
        driver.findElements(orderButtons).get(orderButtonNumber).click();
    }
    public void closeCookiesNotification(){
        driver.findElement(closeCookiesNotificationButton).click();
    }
}
