package backend;

import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru verificarea conditiilor redundante in codul sursa.
 */
public class RedundantConditionChecker {
    
    /**
     * Verifica daca exista conditii redundante in codul sursa.
     *
     * @param code Codul sursa care va fi verificat.
     * @return Un raport despre conditiile redundante gasite sau un mesaj ca nu s-au gasit astfel de conditii.
     */
    public static String checkRedundantConditions(String code) {
        List<String> results = new ArrayList<>(); // Lista pentru a stoca conditiile redundante gasite

        // Modele de expresii regulate pentru a gasi conditiile redundante
        String[] patterns = {
            "\\b\\w+\\s*==\\s*true\\b",   // Verifica expresii de tipul "variabila == true"
            "\\b\\w+\\s*!=\\s*false\\b",  // Verifica expresii de tipul "variabila != false"
            "\\b\\w+\\s*==\\s*false\\b",  // Verifica expresii de tipul "variabila == false"
            "\\b\\w+\\s*!=\\s*true\\b"    // Verifica expresii de tipul "variabila != true"
        };

        // Verifica fiecare tip de conditie redundanta definita in patterns
        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(code);
            
            // Adauga conditiile redundante gasite in lista results
            while (m.find()) {
                results.add("Conditie redundanta detectata: " + m.group());
            }
        }
        
        // Daca nu s-au gasit conditii redundante, returneaza un mesaj corespunzator
        if (results.isEmpty()) {
            return "Nu s-au detectat conditii redundante.";
        } else {
            // Altfel, returneaza raportul cu toate conditiile redundante
            return String.join("\n", results);
        }
    }
}

