public class ParserNewsBBCFactory implements SiteFactory {
    @Override
    public ParserNews createSite() {
        return new ParserNewsBBC();
    }
}
