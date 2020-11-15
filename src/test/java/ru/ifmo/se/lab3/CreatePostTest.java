package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.se.lab3.pages.MainPage;
import ru.ifmo.se.lab3.pages.fragments.PostFragment;

import static org.junit.Assert.assertEquals;

public class CreatePostTest extends BaseTest {
    private static final String SESSION_ID_PROPERTY = "lab3.session.id";
    private static final String DEFAULT_SESSION_ID = "6af3461ae0536608c98d53e804f3e57f";

    private PostFragment postFragment;

    @Before
    public void setPostFragment() {
        var mainPage = new MainPage(driver);
        mainPage.open(System.getProperty(SESSION_ID_PROPERTY, DEFAULT_SESSION_ID));
        postFragment = mainPage.writePost();
    }

    @Test
    public void testPreview() {
        var text = "Test text";
        var post = postFragment.builder()
                .setTitle("Test title")
                .setText(text)
                .setTags("t1", "t2")
                .build();
        postFragment.writePost(post);
        postFragment.preview();
        assertEquals(text, postFragment.getPreviewText());
    }
}
