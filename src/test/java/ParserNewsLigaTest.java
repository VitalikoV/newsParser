import org.jsoup.HttpStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertThrows;

public class ParserNewsLigaTest {
    ParserNewsLiga parserNewsLiga = new ParserNewsLiga();


    @DisplayName("Test for checking correct quantity of files")
    @Test
    public void rightQuantityOfFiles() {

        assertThrows("Error in rightQuantityOfFiles(first assert)", IllegalArgumentException.class,
                () -> parserNewsLiga.getNews(7));

        assertThrows("Error in rightQuantityOfFiles(second assert)", IllegalArgumentException.class,
                () -> parserNewsLiga.getNews(-3));

        assertThrows("Error in rightQuantityOfFiles(third assert)", IllegalArgumentException.class,
                () -> parserNewsLiga.getNews(100));

    }


    @DisplayName("Test for verifying correct URL")
    @Test
    public void correctUrl() {

        assertThrows("Error in correctUrl (first assert)", HttpStatusException.class,
                () -> parserNewsLiga.getNewsFromLink("https://news.liga.net//something-incorrect"));

        assertThrows("Error in correctUrl (second assert)", HttpStatusException.class,
                () -> parserNewsLiga.getNewsFromLink("https://news.liga.net//something-incorrect-second-case"));
    }


    @DisplayName("Test for verifying correct Host")
    @Test
    public void correctHost() {

        assertThrows("Error in correctHost (first assert)", UnknownHostException.class,
                () -> parserNewsLiga.getNewsFromLink("https://something-strange"));

        assertThrows("Error in correctHost (second assert)", UnknownHostException.class,
                () -> parserNewsLiga.getNewsFromLink("https://news.liga///something-strange"));
    }
}
