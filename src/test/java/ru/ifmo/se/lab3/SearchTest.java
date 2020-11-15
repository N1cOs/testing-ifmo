package ru.ifmo.se.lab3;

import org.junit.Test;
import ru.ifmo.se.lab3.pages.MainPage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SearchTest extends BaseTest {
    @Test
    public void testPositive() {
        var mainPage = new MainPage(driver);
        mainPage.open();

        var search = mainPage.search("Java");
        var columns = search.getTableColumns();

        var expectedColumns = Arrays.asList("Тема", "Форум", "Автор", "Ответов", "Просмотров",
                "Оценка", "Обновление");
        assertEquals(expectedColumns, columns);
    }

    @Test
    public void testErrorOnShortSearchText() {
        // Minimum text length is 3 symbols
        var mainPage = new MainPage(driver);
        mainPage.open();

        var search = mainPage.search("42");
        assertEquals("Обнаружена ошибка:", search.getErrorText());
    }
}
