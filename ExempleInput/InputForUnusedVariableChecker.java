public class InputForUnusedVariableChecker {
    public static void main(String[] args) {
        int a = 5;  // Variabila 'a' nu este utilizată
        double b = 10.5;  // Variabila 'b' nu este utilizată
        String name = "John";  // Variabila 'name' este utilizată
        boolean flag = true;  // Variabila 'flag' nu este utilizată

        // Variabile neutilizate
        long x = 1000L;  // Variabila 'x' nu este utilizată
        float y = 3.14f;  // Variabila 'y' nu este utilizată
        char letter = 'A';  // Variabila 'letter' este utilizată

        System.out.println("Hello, " + name);
        System.out.println("Letter: " + letter);
    }
}

