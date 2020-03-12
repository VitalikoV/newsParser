import java.io.IOException;
import java.util.Scanner;

public class Facade {

    public void startApplication() throws IOException {

        String inputForSite, inputForQuantity, readArticle;
        Scanner in = new Scanner(System.in);

        inputForSite = getNameOfSite(in);

        SiteFactory siteFactory = createSiteByName(inputForSite);
        ParserNews site = siteFactory.createSite();

        inputForQuantity = getQuantityOfArticles(in);

        site.getNews(Integer.parseInt(inputForQuantity));

        readArticle = getArticle(in);

        site.getNewsFromLink(readArticle);

    }



    private String getNameOfSite(Scanner in) {
        String inputForSite;
        System.out.println("""
                From which site you want to take news
                1. Liga;
                2. BBC;
                3. UkrNews;
                4. TheGuardian;
                5. NewYork Times?""");

        pointerForInput();
        inputForSite = in.nextLine();
        exitFromConsole(inputForSite);
        return inputForSite;
    }


    private String getQuantityOfArticles(Scanner in) {
        String inputForQuantity;
        System.out.println("how much news you want to see?");
        pointerForInput();
        inputForQuantity = in.nextLine();
        exitFromConsole(inputForQuantity);
        return inputForQuantity.trim();
    }

    private String getArticle(Scanner in) {
        String readArticle;
        System.out.println("\nMaybe you want to read article?(if yes - put URL, if no - (q + enter):\n");
        pointerForInput();
        readArticle = in.nextLine();
        exitFromConsole(readArticle);
        return readArticle;
    }

    static SiteFactory createSiteByName(String name) {

        if (isNameExist(name, "BBC") || isNameExist(name, "2")) {
            return new ParserNewsBBCFactory();
        } else if (isNameExist(name, "Liga") || isNameExist(name, "1")) {
            return new ParserNewsLigaFactory();
        } else if (isNameExist(name, "UkrNews") || isNameExist(name, "3")) {
            return new ParserNewsUkrNewsFactory();
        } else if (isNameExist(name, "TheGuardian") || isNameExist(name, "4")) {
            return new ParserNewsTheGuardianFactory();
        } else if (isNameExist(name, "NewYork Times") || isNameExist(name, "5")) {
            return new ParserNewsNYTimesFactory();
        } else {
            throw new RuntimeException(name + " - is unknown name");
        }

    }

    private static boolean isNameExist(String actualName, String expectedName) {
        return actualName.trim().equalsIgnoreCase(expectedName);
    }

    private void exitFromConsole(String inputForSite) {
        if (isNameExist(inputForSite, "q") || isNameExist(inputForSite, "quit")) {
            System.exit(0);
        }
    }

    private void pointerForInput() {
        System.out.print("-> ");
    }

}
