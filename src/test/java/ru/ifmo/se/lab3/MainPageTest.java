package ru.ifmo.se.lab3;

import org.junit.Test;
import ru.ifmo.se.lab3.pages.MainPage;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MainPageTest extends BaseTest {
    private final static Logger logger = Logger.getLogger(MainPage.class.getName());

    @Test
    public void testNewsFeed() {
        var mainPage = new MainPage(driver);
        mainPage.open();

        var allNews = mainPage.getNews();
        assertEquals(25, allNews.size());

        for (var news : mainPage.getNews()) {
            assertFalse(news.header.isEmpty());
            if (news.rating==null) {
                var msg = String.format("news without rating: id=%s", news.id);
                logger.info(msg);
            }
        }
    }
}
