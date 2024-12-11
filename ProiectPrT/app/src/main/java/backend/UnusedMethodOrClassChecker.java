package backend;

import java.util.*;
import java.util.regex.*;

/**
 * Clasa pentru verificarea metodelor si claselor neutilizate dintr-un cod sursa.
 */
public class UnusedMethodOrClassChecker {

    /**
     * Verifica metodele si clasele declarate dar neutilizate intr-un cod sursa.
     * 
     * @param code Codul sursa in care se vor verifica metodele si clasele neutilizate.
     * @return Un raport care indica metodele si clasele neutilizate sau ca nu au fost gasite.
     */
    public static String checkUnusedMethodsOrClasses(String code) {
        // Inlatura comentariile (de o singura linie si multi-linie)
        code = code.replaceAll("//.*", "");
        code = code.replaceAll("/\\*.*?\\*/", "");

        // Pattern pentru identificarea claselor
        String classPattern = "\\bclass\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\b";
        Pattern classRegex = Pattern.compile(classPattern);
        Matcher classMatcher = classRegex.matcher(code);

        List<String> declaredClasses = new ArrayList<>(); // Listeaza clasele declarate
        while (classMatcher.find()) {
            declaredClasses.add(classMatcher.group(1)); // Adauga clasa in lista de clase declarate
        }

        // Pattern pentru identificarea metodelor
        String methodPattern = "\\b(?:public|private|protected|static)?\\s*(?:void|int|double|String|char|boolean|long|float|short|byte|Object)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(";
        Pattern methodRegex = Pattern.compile(methodPattern);
        Matcher methodMatcher = methodRegex.matcher(code);

        List<String> declaredMethods = new ArrayList<>(); // Listeaza metodele declarate
        while (methodMatcher.find()) {
            declaredMethods.add(methodMatcher.group(1)); // Adauga metoda in lista de metode declarate
        }

        // Verifica clasele pentru a vedea daca sunt utilizate
        List<String> unusedClasses = new ArrayList<>(declaredClasses); // Copiaza lista de clase pentru a le verifica
        for (String className : declaredClasses) {
            String usagePattern = "\\b" + Pattern.quote(className) + "\\b"; // Pattern pentru verificarea utilizarii clasei
            Pattern usageCheck = Pattern.compile(usagePattern);
            Matcher usageMatcher = usageCheck.matcher(code);
            if (!usageMatcher.find()) {
                unusedClasses.remove(className); // Daca nu este folosita, o elimina din lista de clase neutilizate
            }
        }

        // Verifica metodele pentru a vedea daca sunt utilizate
        List<String> unusedMethods = new ArrayList<>(declaredMethods); // Copiaza lista de metode pentru a le verifica
        for (String methodName : declaredMethods) {
            String usagePattern = "\\b" + Pattern.quote(methodName) + "\\b"; // Pattern pentru verificarea utilizarii metodei
            Pattern usageCheck = Pattern.compile(usagePattern);
            Matcher usageMatcher = usageCheck.matcher(code);
            if (!usageMatcher.find()) {
                unusedMethods.remove(methodName); // Daca nu este folosita, o elimina din lista de metode neutilizate
            }
        }

        // Creeaza un raport despre metodele si clasele neutilizate
        StringBuilder report = new StringBuilder();
        if (unusedClasses.isEmpty() && unusedMethods.isEmpty()) {
            return "Nu au fost gasite metode sau clase neutilizate.";
        }
        if (!unusedClasses.isEmpty()) {
            report.append("Clase neutilizate gasite: ").append(String.join(", ", unusedClasses)).append("\n");
        }
        if (!unusedMethods.isEmpty()) {
            report.append("Metode neutilizate gasite: ").append(String.join(", ", unusedMethods));
        }
        return report.toString();
    }
}

