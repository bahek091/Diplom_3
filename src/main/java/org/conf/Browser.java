package org.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser{

    public WebDriver getWebDriver(String browserName){
        switch (browserName){
            case "chrome":
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver",
                        "./yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new RuntimeException("Browser undefined");
        }
    }
}
