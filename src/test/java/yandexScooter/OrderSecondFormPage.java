package yandexScooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderSecondFormPage {
    private final WebDriver driver;

    public OrderSecondFormPage(WebDriver driver){
        this.driver = driver;
    }
    //Поля второй формы
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentDurationDropDown = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourCheckboxes = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label");
    private final By deliveryComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка создания заказа
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //кнопка подтверждения создания заказа
    private final By confirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //элементы сообщения об оформлении заказа
    //заголовок результата
    private final By orderResultStatus = By.className("Order_ModalHeader__3FDaJ");
    //данные заказа
    private final By orderResultDetails = By.className("Order_Text__2broi");
    //кнопка перехода в отслеживание заказа
    private final By checkOrderStatusButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']");

    public void enterDeliveryDate(String deliveryDateInput){
        driver.findElement(deliveryDate).sendKeys(deliveryDateInput);
    }
    public void choseRentDuration (String rentDurationInput){
        //Select dropdown = new Select(driver.findElement(rentDurationDropDown));
        //dropdown.selectByValue(rentDurationInput) ;
        driver.findElement(rentDurationDropDown).click();
        driver.findElement(By.xpath(String.format(".//div[text()='%s']",rentDurationInput))).click();

    }
    public void enterDeliveryComment(String commentInput){
        if (commentInput != null) {driver.findElement(deliveryComment).sendKeys(commentInput);}
    }
    public void setColourById (int colourId){
        driver.findElements(colourCheckboxes).get(colourId).click();
    }

    public void clickCreateOrder(){
        driver.findElement(orderButton).click();
    }

    public void clickConfirmOrder(){
        driver.findElement(confirmOrderButton).click();
    }

    public boolean orderResultIsDisplayed(){
        return (!driver.findElements(orderResultStatus).isEmpty()
                && !driver.findElements(orderResultDetails).isEmpty()
                && !driver.findElements(checkOrderStatusButton).isEmpty()
                );
    }

    public String getOrderResultText(){
        return driver.findElement(orderResultStatus).getText();
    }
}
