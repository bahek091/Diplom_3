package org.pageobjects;


import org.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends MainPage{
    public static final String REGISTER_URI = MAIN_PAGE_URL + "register";

    private final By nameInput = By.xpath("//label[text()='Имя']/parent::div/input");
    private final By emailInput = By.xpath("//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/parent::div/input");

    private final By enterButton = By.xpath("//a[text()='Войти']");

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordErrorField = By.xpath("//div[@class='input__container']//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver){
        super(driver);
    }

    public void openRegisterPage() {
        driver.get(REGISTER_URI);
    }

    public void fillRegisterForm(UserData userData){
        driver.findElement(nameInput).sendKeys(userData.getName());
        driver.findElement(emailInput).sendKeys(userData.getEmail());
        driver.findElement(passwordInput).sendKeys(userData.getPassword());
        driver.findElement(passwordInput).sendKeys(Keys.TAB);
    }

    public void clickRegistrationButton(){
        driver.findElement(registerButton).click();
    }

    public boolean  checkPasswordErrorField(){
        return driver.findElement(passwordErrorField).isDisplayed();
    }

    public String getCurrentPageURL(){
        return super.getCurrentPageIRL();
    }

    public void enterButtonClick(){
        clickButton(enterButton);
    }


}
