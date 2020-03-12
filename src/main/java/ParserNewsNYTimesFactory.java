public class ParserNewsNYTimesFactory implements SiteFactory {
    @Override
    public ParserNews createSite() {
        return new ParserNewsNYTimes();
    }
}
