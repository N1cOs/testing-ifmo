package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class SignInFragment extends AbstractFragment {
    private final static String TITLE = "ЯПлакалъ - Вход";

    private final static By LOGIN_INPUT_XPATH = By.xpath("//input[@name='UserName']");
    private final static By PASSWORD_INPUT_XPATH = By.xpath("//input[@name='PassWord']");
    private final static By CAPTCHA_XPATH = By.xpath("//script[starts-with(@src, 'https://www.google.com/recaptcha')]");

    public SignInFragment(WebDriver driver) {
        super(driver);
        waitForTitle(TITLE);
    }

    public boolean isLoginPresence() {
        return isElementPresence(LOGIN_INPUT_XPATH);
    }

    public boolean isPasswordPresence() {
        return isElementPresence(PASSWORD_INPUT_XPATH);
    }

    public boolean isCaptchaPresence() {
        return isElementPresence(CAPTCHA_XPATH);
    }

    public void signIn(String login, String password) {
        var loginElem = driver.findElement(LOGIN_INPUT_XPATH);
        loginElem.sendKeys(login);

        var passwordElem = driver.findElement(PASSWORD_INPUT_XPATH);
        passwordElem.sendKeys(password, Keys.ENTER);
    }

    public String getErrorText() {
        var errorElem = driver.findElement(By.xpath("//div[@class='tablepad']/span[@class='postcolor']"));
        return errorElem.getText();
    }

    private boolean isElementPresence(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException exc) {
            return false;
        }
    }

}
