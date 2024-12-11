package backend;

import java.util.regex.*;

/**
 * Clasa pentru verificarea stilului de formatare al codului sursa.
 */
public class StyleChecker {

    /**
     * Verifica stilul de formatare al unui cod sursa, raportand erori comune.
     * 
     * @param code Codul sursa care va fi verificat.
     * @return Un raport care indica eventualele erori de stil sau un mesaj ca stilul respecta conventiile.
     */
    public static String checkFormattingStyle(String code) {
        StringBuilder report = new StringBuilder(); // Creeaza un obiect pentru raport

        // Verifica daca operatorii sunt separati de spatiu
        Pattern operatorsPattern = Pattern.compile("\\w[\\+\\-\\*/=<>!]+\\w");
        Matcher operatorsMatcher = operatorsPattern.matcher(code);
        if (operatorsMatcher.find()) {
            report.append("Operatori fara spatiu detectati. Adaugati spatii intre operatori.\n");
        }

        // Verifica indentarea (4 spatii pentru fiecare nivel de indentare)
        String[] lines = code.split("\\n"); // Imparte codul pe linii
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            // Verifica daca linia nu este goala si nu incepe cu 4 spatii
            if (!line.trim().isEmpty() && !line.startsWith("    ") && line.matches("^(\\s{4})*\\S.*$")) {
                report.append("Linia ").append(i + 1).append(": Indentare incorecta. Folositi multipli de 4 spatii.\n");
            }
        }

        // Verifica spatiile lipsa dupa cuvintele cheie
        String[] keywords = {"if", "for", "while", "switch", "catch"}; // Cuvintele cheie pentru care se verifica spatiile
        for (String keyword : keywords) {
            Pattern keywordsPattern = Pattern.compile("\\b" + keyword + "\\(");
            Matcher keywordsMatcher = keywordsPattern.matcher(code);
            while (keywordsMatcher.find()) {
                report.append("Lipsa spatiu detectata dupa cuvantul cheie '")
                      .append(keyword)
                      .append("'. Adaugati un spatiu dupa cuvintele cheie inainte de '('.\n");
            }
        }

        // Verifica daca operatorii nu sunt separati de spatiu fata de variabile
        Pattern operatorSpacingPattern = Pattern.compile("(\\w)[\\+\\-\\*/=<>!]+(\\w)");
        Matcher operatorSpacingMatcher = operatorSpacingPattern.matcher(code);
        if (operatorSpacingMatcher.find()) {
            report.append("Operatori intre variabile fara spatiu. Adaugati un spatiu intre operatori si variabile.\n");
        }

        // Returneaza raportul sau un mesaj ca stilul respecta conventiile
        return report.length() == 0 ? "Stilul de formatare respecta conventiile." : report.toString();
    }
}

