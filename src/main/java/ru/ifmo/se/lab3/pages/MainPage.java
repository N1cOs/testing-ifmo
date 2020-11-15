package ru.ifmo.se.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.ifmo.se.lab3.pages.elems.News;
import ru.ifmo.se.lab3.pages.fragments.PostFragment;
import ru.ifmo.se.lab3.pages.fragments.SearchFragment;
import ru.ifmo.se.lab3.pages.fragments.SignInFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }


    public List<News> getNews() {
        var allNews = driver.findElements(By.xpath("//td[@class='newshead']"));

        var result = new ArrayList<News>(allNews.size());
        for (var news : allNews) {
            Integer rating;
            try {
                // find elem by css selector because div has two classes
                var ratingElem = news.findElement(By.cssSelector("div.rating-short-value > a"));
                rating = Integer.parseInt(ratingElem.getText());
            } catch (NoSuchElementException exc) {
                rating = null;
            }

            var headerElem = news.findElement(By.xpath("div/h2/a[1]"));
            var header = headerElem.getText();

            result.add(new News(news.getAttribute("id"), header, rating));
        }

        return result;
    }

    public SearchFragment search(String text) {
        var input = driver.findElement(By.xpath("//form[@name='search_form']/*/input[@type='text']"));
        input.sendKeys(text, Keys.ENTER);
        return new SearchFragment(driver);
    }

    public SignInFragment goToSignIn() {
        var signInElem = driver.findElement(By.xpath("//div[@id='welcome-box']/a[2]"));
        signInElem.click();
        return new SignInFragment(driver);
    }

    public PostFragment writePost() {
        var newPostElem = driver.findElement(By.xpath("//a[@id='new-post']"));
        newPostElem.click();
        return new PostFragment(driver);
    }
}
