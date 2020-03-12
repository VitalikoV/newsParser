public class ParserNewsLigaFactory implements SiteFactory {
    @Override
    public ParserNews createSite() {
        return new ParserNewsLiga();
    }
}
