package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.se.lab3.pages.MainPage;
import ru.ifmo.se.lab3.pages.fragments.SignInFragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignInTest extends BaseTest {
    private SignInFragment signInFragment;

    @Before
    public void setSignInFragment() {
        var mainPage = new MainPage(driver);
        mainPage.open();
        signInFragment = mainPage.goToSignIn();
    }

    @Test
    public void testSignInElements() {
        assertTrue(signInFragment.isLoginPresence());
        assertTrue(signInFragment.isPasswordPresence());
        assertTrue(signInFragment.isCaptchaPresence());
    }

    @Test
    public void testErrorOnMissingCaptcha() {
        signInFragment.signIn("test_login", "test_password");
        assertEquals("Проверка на бота не пройдена (missing-input-response)",
                signInFragment.getErrorText());
    }
}
