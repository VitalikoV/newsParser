import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class ParserNewsBBC implements ParserNews {


    private static final String URL = "https://www.bbc.com/ukrainian";
    private static final String BBC_COM = "https://www.bbc.com/";

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
        Element orb = page.selectFirst("div[id=orb-modules]");
        Element main = orb.selectFirst("div[role=main]");
        Element container = main.selectFirst("div[class=container]");
        Element column = container.selectFirst("div[class=column--secondary]");
        Element features = column.selectFirst("div[class=features-and-analysis__stories promo-unit-spacer]");
        return features.select("div[class=features-and-analysis__story]");
    }

    private void parseNews(int quantityOfNews, int quantityOfShowedNews, Elements stories) {
        for (Element story : stories) {
            String title = getTitle(story);
            String address = getAddress(story);

            if (title.equals("") || address.equals("")) continue;

            outputToConsole(title, address);

            quantityOfShowedNews++;
            if (quantityOfShowedNews >= quantityOfNews) break;

        }
    }

    private String getTitle(Element story) {
        return story.select("h3[class=bold-image-promo__title]").text();
    }

    private String getAddress(Element story) {
        return story.select("a[class=bold-image-promo]").attr("href");
    }

    private void outputToConsole(String title, String address) {
        System.out.println();
        System.out.println(title);
        System.out.println(BBC_COM + address);
    }


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

        Element content = page.selectFirst("div[class=story-body__inner]");
        Elements paragraphs = content.select("p");

        System.out.println();
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }

    }
}
