import org.jsoup.HttpStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertThrows;

public class ParserNewsUkrNewsTest {

    ParserNewsUkrNews parserNewsUkrNews = new ParserNewsUkrNews();


    @DisplayName("Test for checking correct quantity of files")
    @Test
    public void rightQuantityOfFiles() {

        assertThrows("Error in rightQuantityOfFiles(first assert)", IllegalArgumentException.class,
                () -> parserNewsUkrNews.getNews(7));

        assertThrows("Error in rightQuantityOfFiles(second assert)", IllegalArgumentException.class,
                () -> parserNewsUkrNews.getNews(-3));

        assertThrows("Error in rightQuantityOfFiles(third assert)", IllegalArgumentException.class,
                () -> parserNewsUkrNews.getNews(100));

    }


    @DisplayName("Test for verifying correct URL")
    @Test
    public void correctUrl() {

        assertThrows("Error in correctUrl (first assert)", HttpStatusException.class,
                () -> parserNewsUkrNews.getNewsFromLink("https://ukranews.com/news/something-incorrect"));

        assertThrows("Error in correctUrl (second assert)", HttpStatusException.class,
                () -> parserNewsUkrNews.getNewsFromLink("https://ukranews.com/news/ukrainian/something-incorrect"));
    }


    @DisplayName("Test for verifying correct Host")
    @Test
    public void correctHost() {

        assertThrows("Error in correctHost (first assert)", UnknownHostException.class,
                () -> parserNewsUkrNews.getNewsFromLink("https://something-strange"));

        assertThrows("Error in correctHost (second assert)", UnknownHostException.class,
                () -> parserNewsUkrNews.getNewsFromLink("https://ukranews/something-strange"));
    }
}
