package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    private final By bodyTag = By.xpath("//div[@id = 'root']");
    public static final int WAIT_TIMEOUT_SEC = 3;

    protected void waitBeforeClick(int timeout, By element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitBeforeClick(int timeout, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getCurrentPageIRL(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOfElementLocated(bodyTag));
        return driver.getCurrentUrl();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void clickButton(By element){
        waitBeforeClick(WAIT_TIMEOUT_SEC, element);
        driver.findElement(element).click();
    }
}
