package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{
    public static final String PROFILE_PAGE = MainPage.MAIN_PAGE_URL + "account/profile";

    public static final By exitButton = By.xpath("//button[text() = 'Выход']");
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    public void openProfilePage(){
        driver.get(PROFILE_PAGE);
    }

    public void exitButtonClick(){
        clickButton(exitButton);
    }
}


