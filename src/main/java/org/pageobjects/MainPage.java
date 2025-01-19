package org.pageobjects;

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

    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    public void openForgotPasswordPage(){
        driver.get(FORGOT_PASSWORD_PAGE);
    }

    public void enterToAccountButtonClick(){
        clickButton(enterToAccountButton);
    }

    public void enterToProfileButtonClick(){
        clickButton(enterToProfileButton);
    }

    public void constructorButtonClik(){
        clickButton(constructorButton);
    }

    public void logoButtonClick(){
        clickButton(logoButton);
    }

    public String getTextForCurrentTab(){
        return driver.findElement(currentTab).getText();
    }

    public void selectConstructorTab(String tabName){
        String currentTab = getTextForCurrentTab();
        if(!tabName.equals(currentTab))
            driver.findElement(By.xpath(String.format(constructorTab, tabName))).click();

    }


}
