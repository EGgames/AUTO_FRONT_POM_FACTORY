package com.miejemplo.hooks;

import com.miejemplo.util.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {

    @Before(order = 1)
    public void setUp(Scenario scenario) {
        System.out.printf("%n▶  [INICIO] Escenario: \"%s\"%n", scenario.getName());
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png",
                        "screenshot-fallo-" + scenario.getName().replace(" ", "_"));
            } catch (Exception e) {
                System.err.println("  ⚠  No se pudo capturar el screenshot: " + e.getMessage());
            }
        }

        DriverManager.quitDriver();

        System.out.printf("⏹  [FIN]    Escenario: \"%s\" → %s%n%n",
                scenario.getName(),
                scenario.isFailed() ? "FALLIDO ✗" : "EXITOSO ✓");
    }
}
