package backend;

import java.util.regex.*;

/**
 * Clasa pentru verificarea comentariilor in codul sursa.
 */
public class CommentChecker {
    
    /**
     * Verifica prezenta comentariilor si cautarea unor cuvinte cheie specifice.
     *
     * @param code Codul sursa care va fi verificat.
     * @return Un raport despre comentariile gasite si cuvintele cheie continute.
     */
    public static String checkComments(String code) {
        StringBuilder result = new StringBuilder(); // StringBuilder pentru a construi raportul
        
        // Expresie regulata pentru a gasi comentarii (atat pe o singura linie cat si pe mai multe linii)
        String commentPattern = "(//.*?$|/\\*.*?\\*/)";
        Pattern pattern = Pattern.compile(commentPattern, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(code);

        boolean foundComment = false; // Indicator pentru a verifica daca au fost gasite comentarii
        
        // Cuvintele cheie care trebuie cautate in comentarii
        String[] keywords = {"TODO", "FIXME", "HACK", "BUG", "OPTIMIZE", "DEPRECATED", "REVIEW", "TEST", "NOTE"};
        
        // Verifica fiecare comentariu gasit in cod
        while (matcher.find()) {
            foundComment = true; // Marca comentariul ca fiind gasit
            String comment = matcher.group(); // Extrage comentariul
            
            // Verifica daca comentariul contine vreun cuvant cheie
            for (String keyword : keywords) {
                if (comment.contains(keyword)) {
                    result.append("Comentariu cu cuvantul cheie '").append(keyword)
                          .append("' gasit: ").append(comment).append("\n");
                }
            }
        }

        // Daca nu s-au gasit comentarii, adauga un mesaj corespunzator
        if (!foundComment) {
            result.append("Nu exista comentarii in cod.\n");
        }

        return result.toString(); // Returneaza raportul cu comentariile gasite
    }
}

