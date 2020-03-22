import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParserNewsNYTimes implements ParserNews {

    public static final String URL = AddressToSites.NYTimes.getUrl();
    public static final String NYTIMES_COM = "https://www.nytimes.com";

    @Override
    public void getNews(int quantityOfNews) throws IOException {
        int quantityOfShowedNews = 0;

        checkingQuantityOfFiles(quantityOfNews);
        Elements stories = getHtmlNews();

        parseNews(quantityOfNews, quantityOfShowedNews, stories);

    }

    private void checkingQuantityOfFiles(int quantityOfNews) {
        if (quantityOfNews < 1 || quantityOfNews > 6) {
            throw new IllegalArgumentException("quantity Of news Must be more than 1 and less or equals than 6");
        }
    }

    private Elements getHtmlNews() throws IOException {
        Document page = getPage();
        Element container = page.selectFirst("section[id=collection-world]");
        Element news = container.selectFirst("ol[aria-live=polite]");
        return news.select("li[class=css-ye6x8s]");
    }


    private void parseNews(int quantityOfNews, int quantityOfShowedNews, Elements stories) {
        for (Element story : stories) {
            String title = story.selectFirst("div[class=css-1l4spti]").text();
            String address = story.selectFirst("div[class=css-1l4spti] > a").attr("href");

            OutputToConsole(title, address);

            if (title.equals("") || address.equals("")) continue;

            quantityOfShowedNews++;
            if (quantityOfShowedNews >= quantityOfNews) break;

        }
    }


    private void OutputToConsole(String title, String address) {
        System.out.println();
        System.out.println(title);
        System.out.println(NYTIMES_COM + address);
    }

    @Override
    public Document getPage() throws IOException {
        Document page = Jsoup.parse(new URL(URL), 10000);
        return page;
    }

    public Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 10000);
        return page;
    }

    @Override
    public void getNewsFromLink(String url) throws IOException {
        Document page = getPage(url);

        Elements paragraphs = page.select("p");

        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }

    }
}
