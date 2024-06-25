package yandexScooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderFirstFormPage {

    private final WebDriver driver;

    public OrderFirstFormPage(WebDriver driver){
        this.driver = driver;
    }
    //Поле Имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']") ;
    //Поле фамилия
    private final By surName = By.xpath(".//input[@placeholder='* Фамилия']") ;
    //Поле адрес
    private final By adress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']") ;
    //Поле метро
    private final By metroStationDropdown = By.xpath(".//div[@class='Order_Form__17u6u']//div[@class='select-search__value']");
    //поле телефон
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //методы для ввода значений в поля
    public void enterName(String nameInput){
        driver.findElement(name).sendKeys(nameInput);
    }
    public void enterSurName(String surNameInput){
        driver.findElement(surName).sendKeys(surNameInput);
    }
    public void enterAdress(String adressInput){
        driver.findElement(adress).sendKeys(adressInput);
    }
    //Выбор метро
    public void choseMetroStation(String metroStationName){
        //Select dropdown = new Select(driver.findElement(metroStationDropdown));
        //dropdown.selectByVisibleText(metroStationName); ;
        driver.findElement(metroStationDropdown).click();
        driver.findElement(By.xpath(String.format(".//div[text()='%s']/parent::button",metroStationName))).click();
    }
    //заполнение полей
    public void enterPhone(String phoneInput){
        driver.findElement(phone).sendKeys(phoneInput);
    }
    //нажатие Далее
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
}
