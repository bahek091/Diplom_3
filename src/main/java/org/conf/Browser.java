package org.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.module.Configuration;

public class Browser{

    public WebDriver getWebDriver(String browserName){
        switch (browserName){
            case "chrome":
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver",
                        "C:/Praktikum/yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new RuntimeException("Broser undefined");
        }
    }
}
