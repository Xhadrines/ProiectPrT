package backend;

import java.util.*;
import java.util.regex.*;

/**
 * Clasa pentru verificarea variabilelor neutilizate dintr-un cod sursa.
 */
public class UnusedVariableChecker {

    /**
     * Verifica variabilele declarate dar neutilizate intr-un cod sursa.
     * 
     * @param code Codul sursa in care se vor verifica variabilele neutilizate.
     * @return Un mesaj care indica variabilele neutilizate sau ca nu au fost gasite.
     */
    public static String checkUnusedVariables(String code) {
        // Inlatura comentariile (de o singura linie si multi-linie)
        code = code.replaceAll("//.*", "");
        code = code.replaceAll("/\\*.*?\\*/", "");

        // Pattern pentru identificarea variabilelor declarate
        String variablePattern = "\\b(?:int|double|String|char|boolean|long|float|short|byte|Object)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*(=|;)";
        Pattern pattern = Pattern.compile(variablePattern);
        Matcher matcher = pattern.matcher(code);

        List<String> declaredVariables = new ArrayList<>(); // Listeaza variabilele declarate
        while (matcher.find()) {
            declaredVariables.add(matcher.group(1)); // Adauga variabila in lista de variabile declarate
        }

        List<String> unusedVariables = new ArrayList<>(declaredVariables); // Copiaza lista de variabile pentru a le verifica

        // Verifica fiecare variabila pentru utilizare
        for (String variable : declaredVariables) {
            String usagePattern = "\\b" + Pattern.quote(variable) + "\\b"; // Pattern pentru verificarea utilizarii variabilei
            Pattern usageCheck = Pattern.compile(usagePattern);
            Matcher usageMatcher = usageCheck.matcher(code);

            int usageCount = 0;
            while (usageMatcher.find()) {
                usageCount++; // Creste contorul de utilizari
            }

            // Daca variabila este folosita, o elimina din lista de variabile neutilizate
            if (usageCount > 1) {
                unusedVariables.remove(variable);
            }
        }

        // Returneaza un mesaj in functie de variabilele neutilizate gasite
        if (unusedVariables.isEmpty()) {
            return "Nu au fost gasite variabile neutilizate.";
        } else {
            return "Variabile neutilizate gasite: " + String.join(", ", unusedVariables);
        }
    }
}

