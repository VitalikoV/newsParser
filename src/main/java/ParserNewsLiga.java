import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParserNewsLiga implements ParserNews {

    public static final String URL = "https://news.liga.net";

    @Override
    public void getNews(int quantityOfNews) throws IOException {
        int quantityOfShowedNews = 0;

        checkingQuantityOfFiles(quantityOfNews);
        Elements news = getHtmlNews();

        parseNews(quantityOfNews, quantityOfShowedNews, news);
    }

    private void checkingQuantityOfFiles(int quantityOfNews) {
        if (quantityOfNews < 1 || quantityOfNews > 6) {
            throw new IllegalArgumentException("quantity Of news Must be more than 1 and less or equals than 6");
        }
    }


    private Elements getHtmlNews() throws IOException {
        Document page = getPage();
        Element bodyBg = page.selectFirst("div[class=body-bg]");
        //take container with news, ads, and some useless for us information
        Element container = bodyBg.select("div[class=container]").get(1);
        //pick up all news
        return container.select("div[class=news-nth]");
    }

    private void parseNews(int quantityOfNews, int quantityOfShowedNews, Elements news) {
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


    private String getDate(Element story) {
        return story.select("div[class=news-nth-time]").text();
    }

    private String getTitle(Element story) {
        return story.select("div[class=news-nth-title]").text();
    }

    private String getAddress(Element story) {
        return story.select("div[class=news-nth-title] > a").attr("href");
    }

    private void OutputToConsole(String date, String everyNews, String address) {
        System.out.println();
        System.out.println(date);
        System.out.println(everyNews);
        System.out.println(address);
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
        Element content = page.selectFirst("div[id=news-text]");
        Elements paragraphs = content.select("p");

        System.out.println();
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }

    }
}
