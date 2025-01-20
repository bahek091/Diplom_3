package org.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    private final By bodyTag = By.xpath("//div[@id = 'root']");
    public static final int WAIT_TIMEOUT_SEC = 3;

    protected void waitBeforeClick(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(BasePage.WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Get URL for current page")
    public String getCurrentPageIRL(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(bodyTag));
        return driver.getCurrentUrl();
    }

    public void clickButton(By element){
        waitBeforeClick(element);
        driver.findElement(element).click();
    }
}


