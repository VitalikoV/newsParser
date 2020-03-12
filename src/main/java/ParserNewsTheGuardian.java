import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParserNewsTheGuardian implements ParserNews {
    public static final String URL = "https://www.theguardian.com/world";

    @Override
    public void getNews(int quantityOfNews) throws IOException {
        checkingQuantityOfFiles(quantityOfNews);
        int quantityOfShowedNews = 0;
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
        Element news = page.selectFirst("section[id=most-viewed-in-world-news]");
        Element container = news.selectFirst("div[class=fc-container__inner]");
        return container.select("div[class=most-popular__link]");
    }

    private void parseNews(int quantityOfNews, int quantityOfShowedNews, Elements stories) {
        for (Element story : stories) {
            String title = getTitle(story);
            String address = getAddress(story);

            OutputToConsole(title, address);

            if (title.equals("") || address.equals("")) continue;

            quantityOfShowedNews++;
            if (quantityOfShowedNews >= quantityOfNews) break;
        }
    }

    private String getTitle(Element story) {
        return story.select("h3[class=fc-item__title]").text();
    }

    private String getAddress(Element story) {
        return story.select("a[class=fc-item__link]").attr("href");
    }

    private void OutputToConsole(String title, String address) {
        System.out.println();
        System.out.println(title);
        System.out.println(address);
    }

    @Override
    public void getNewsFromLink(String url) throws IOException {
        Document page = getPage(url);

        Element content = page.selectFirst("div[class=l-side-margins]");
        Element article = content.selectFirst("article[id=article]");
        Elements paragraphs = article.select("p");

        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }

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
}
