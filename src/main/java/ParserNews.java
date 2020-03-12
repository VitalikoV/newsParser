import org.jsoup.nodes.Document;

import java.io.IOException;

public interface ParserNews {

    void getNews(int quantityOfNews) throws IOException;

    Document getPage() throws IOException;

    void getNewsFromLink(String url) throws IOException;


    }
