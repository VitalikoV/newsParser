public class ParserNewsUkrNewsFactory implements SiteFactory {
    @Override
    public ParserNews createSite() {
        return new ParserNewsUkrNews();
    }
}
