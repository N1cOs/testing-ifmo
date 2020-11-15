package ru.ifmo.se.lab3;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    private static final String FIREFOX_DRIVER = "firefox";

    protected WebDriver driver;

    @Before
    public void createDriver() {
        var property = System.getProperty("lab3.driver");
        if (FIREFOX_DRIVER.equals(property)) {
            driver = DriverFactory.createDriver(DriverType.FIREFOX);
        } else {
            driver = DriverFactory.createDriver(DriverType.CHROME);
        }
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
