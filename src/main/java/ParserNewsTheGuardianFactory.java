public class ParserNewsTheGuardianFactory implements SiteFactory {
    @Override
    public ParserNews createSite() {
        return new ParserNewsTheGuardian();
    }
}
