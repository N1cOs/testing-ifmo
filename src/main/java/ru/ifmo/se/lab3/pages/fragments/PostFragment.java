package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.ifmo.se.lab3.pages.elems.Post;

import java.util.Arrays;
import java.util.List;

public class PostFragment extends AbstractFragment {
    private static final By PREVIEW_TEXT_XPATH = By.xpath("//div[@class='tableborder']/div[@class='row1']/div[1]");

    public PostFragment(WebDriver driver) {
        super(driver);
        waitForTitle("Создание новой темы");
    }

    public PostBuilder builder() {
        return new PostBuilder();
    }

    public void writePost(Post post) {
        var titleElem = driver.findElement(By.xpath("//input[@name='TopicTitle']"));
        titleElem.sendKeys(post.title);

        var textElem = driver.findElement(By.xpath("//textarea[@id='Post']"));
        textElem.sendKeys(post.text);

        var tags = String.join(",", post.tags);
        var tagsElem = driver.findElement(By.xpath("//input[@id='tags']"));
        tagsElem.sendKeys(tags);
    }

    public void send() {
        var submitButton = driver.findElement(By.xpath("//input[@name='sub']"));
        submitButton.click();
    }

    public void preview() {
        var previewButton = driver.findElement(By.xpath("//input[@name='preview']"));
        previewButton.click();
        waitFor(PREVIEW_TEXT_XPATH);
    }

    public String getPreviewText() {
        var previewTextElem = driver.findElement(PREVIEW_TEXT_XPATH);
        return previewTextElem.getText();
    }

    public static class PostBuilder {
        String title;
        String text;
        List<String> tags;

        public PostBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public PostBuilder setTags(String... tags) {
            this.tags = Arrays.asList(tags);
            return this;
        }

        public Post build() {
            return new Post(title, text, tags);
        }
    }
}
