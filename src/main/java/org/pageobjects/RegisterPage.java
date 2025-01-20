package org.pageobjects;


import io.qameta.allure.Step;
import org.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends MainPage{
    public static final String REGISTER_URI = MAIN_PAGE_URL + "register";

    private final By nameInput = By.xpath("//label[text()='Имя']/parent::div/input");
    private final By emailInput = By.xpath("//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/parent::div/input");

    private final By enterButton = By.xpath("//a[text()='Войти']");

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordErrorField = By.xpath("//div[@class='input__container']//p[text()='Некорректный пароль']");
    private final By resetPasswordButton = By.xpath("//p[text()='Забыли пароль?']");

    public RegisterPage(WebDriver driver){
        super(driver);
    }

    @Step("Open registration page")
    public void openRegisterPage() {
        driver.get(REGISTER_URI);
    }

    @Step("Fill registration form")
    public void fillRegisterForm(UserData userData){
        driver.findElement(nameInput).sendKeys(userData.getName());
        driver.findElement(emailInput).sendKeys(userData.getEmail());
        driver.findElement(passwordInput).sendKeys(userData.getPassword());
        driver.findElement(passwordInput).sendKeys(Keys.TAB);
    }

    @Step("Click registration button")
    public void clickRegistrationButton(){
        driver.findElement(registerButton).click();
    }

    @Step("Check error for short password")
    public boolean  checkPasswordErrorField(){
        return driver.findElement(passwordErrorField).isDisplayed();
    }



    @Step("Click on Enter button")
    public void enterButtonClick(){
        clickButton(enterButton);
    }

    @Step("Get URL for current page")
    public String getCurrentPageURL() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButton));
        return driver.getCurrentUrl();
    }

}
