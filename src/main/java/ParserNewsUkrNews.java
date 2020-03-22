import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParserNewsUkrNews implements ParserNews {

    public static final String URL = AddressToSites.UKRNEWS.getUrl();
    public static final String HTTPS_UKRNEWS = "https://ukranews.com";

    @Override
    public void getNews(int quantityOfNews) throws IOException {
        int quantityOfShowedNews = 0;

        checkingQuantityOfFiles(quantityOfNews);

        Elements news = getHtmlNews();

        parseNews(quantityOfNews, news, quantityOfShowedNews);

    }

    private void checkingQuantityOfFiles(int quantityOfNews) {
        if (quantityOfNews < 1 || quantityOfNews > 10) {
            throw new IllegalArgumentException("quantity Of news Must be more than 1 and less or equals than 6");
        }
    }

    private void parseNews(int quantityOfNews, Elements news, int quantityOfShowedNews) {
        for (Element story : news) {
            String date = getDate(story);
            String title = getTitle(story);
            String address = getAddress(story);

            if (date == null || title.equals("") || address.equals("")) continue;

            OutputToConsole(date, title, address);

            quantityOfShowedNews++;
            if (quantityOfShowedNews >= quantityOfNews) break;
        }
    }

    private Elements getHtmlNews() throws IOException {
        Document page = getPage();
        Element main = page.select("section[class=main-section]").get(0);
        Element articles = main.selectFirst("div[class=content]");
        Element content = articles.selectFirst("div[class=news__content]");
        return content.select("a[class=tape_news__item biger_font]");
    }

    private String getAddress(Element story) {
        return story.select("a[class=tape_news__item biger_font]").attr("href");
    }

    private String getTitle(Element story) {
        return story.select("div[class=text]").text();
    }

    private String getDate(Element story) {
        return story.select("span[class=news__time]").text();
    }

    private void OutputToConsole(String date, String title, String address) {
        System.out.println();
        System.out.println(date);
        System.out.println(title);
        System.out.println(HTTPS_UKRNEWS + address);
    }

    @Override
    public Document getPage() throws IOException {
        Document page = Jsoup.parse(new URL(URL), 10000);
        return page;
    }

    public Document getPage(String uri) throws IOException {
        Document page = Jsoup.parse(new URL(uri), 10000);
        return page;
    }

    @Override
    public void getNewsFromLink(String url) throws IOException {
        Document page = getPage(url);
        Elements paragraphs = page.select("p, h1");

        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }
    }
}
