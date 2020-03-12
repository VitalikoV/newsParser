
public enum AddressToSites {
    LIGA("https://news.liga.net"), BBC("https://www.bbc.com/ukrainian"), UKRNEWS("https://ukranews.com/news"),
    THEGUARDIAN("https://www.theguardian.com/world"), NYTimes("https://www.nytimes.com/section/world");

    private String url;
    AddressToSites(String url) {
        this.url= url;
    }

    public String getUrl() {
        return url;
    }
}
