package org.pageobjects;

import io.qameta.allure.Step;
import org.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends MainPage{
    public static final String LOGIN_URI = MAIN_PAGE_URL + "login";

    private final By emailInput = By.xpath("//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/parent::div/input");
    private final By enterButton = By.xpath("//button[text()='Войти']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By resetPasswordButton = By.xpath("//p[text()='Забыли пароль?']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill logon credentials")
    public void inputLoginCredentials(UserData userData){
        driver.findElement(emailInput).sendKeys(userData.getEmail());
        driver.findElement(passwordInput).sendKeys(userData.getPassword());
    }

    @Step("Click on enter button")
    public void enterButtonClick(){
        clickButton(enterButton);
    }

    @Step("Check if create order button is displayed")
    public boolean createOrderButtonIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Open login page")
    public void openLoginPage(){
        driver.get(LOGIN_URI);
    }

    @Step("Login to application")
    public void loginToApplication(UserData userData){
        openLoginPage();
        inputLoginCredentials(userData);
        enterButtonClick();
    }

    @Step("Get URL for current page")
    public String getCurrentPageIRL(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButton));
        return driver.getCurrentUrl();

    }


}
