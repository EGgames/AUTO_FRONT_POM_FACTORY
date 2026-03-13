package com.miejemplo.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();

            if (Boolean.parseBoolean(ConfigReader.get("headless"))) {
                options.addArguments("--headless");
            }

            DRIVER.set(new FirefoxDriver(options));
            DRIVER.get().manage().window().maximize();
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
