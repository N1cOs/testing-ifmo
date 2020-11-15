package ru.ifmo.se.lab3.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    private static final String DEFAULT_HOST = "https://www.yaplakal.com";
    private static final String HOST_PROPERTY = "lab3.host";

    private static final String SESSION_ID_COOKIE_NAME = "SID";

    protected final WebDriver driver;
    protected final String url;

    public AbstractPage(WebDriver driver, String host, String uri) {
        this.driver = driver;
        this.url = host + uri;
    }

    public AbstractPage(WebDriver driver, String uri) {
        this(driver, System.getProperty(HOST_PROPERTY, DEFAULT_HOST), uri);
    }

    public void open() {
        driver.get(url);
    }

    public void open(String sessionId) {
        open(new Cookie(SESSION_ID_COOKIE_NAME, sessionId));
    }

    public void open(Cookie... cookies) {
        driver.get(url);
        for (var cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        driver.get(url);
    }
}
