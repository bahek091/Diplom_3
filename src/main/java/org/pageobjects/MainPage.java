package org.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String FORGOT_PASSWORD_PAGE = MAIN_PAGE_URL + "forgot-password";


    private final By enterToAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By enterToProfileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[contains(@class,'AppHeader_header__logo_')]/a");

    private final By currentTab = By.xpath(".//div[contains(@class,'tab_type_current')]/span");

    private final String constructorTab = ".//div[contains(@class,'tab_')]/span[text()='%s']";

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Open main page")
    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Open Forgot password page")
    public void openForgotPasswordPage(){
        driver.get(FORGOT_PASSWORD_PAGE);
    }

    @Step("Click on 'Enter to account' button")
    public void enterToAccountButtonClick(){
        clickButton(enterToAccountButton);
    }

    @Step("Click on 'Enter to profile' button")
    public void enterToProfileButtonClick(){
        clickButton(enterToProfileButton);
    }

    @Step("Click on Constructor button")
    public void constructorButtonClick(){
        clickButton(constructorButton);
    }

    @Step("Click on Logo")
    public void logoButtonClick(){
        clickButton(logoButton);
    }

    @Step("Get text for active tab")
    public String getTextForCurrentTab(){
        return driver.findElement(currentTab).getText();
    }

    @Step("Click on constructor tab")
    public void selectConstructorTab(String tabName){
        String currentTab = getTextForCurrentTab();
        if(!tabName.equals(currentTab))
            driver.findElement(By.xpath(String.format(constructorTab, tabName))).click();

    }


}
