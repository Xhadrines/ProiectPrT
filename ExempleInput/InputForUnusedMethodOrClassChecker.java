public class InputForUnusedMethodOrClassChecker {
    public static void main(String[] args) {
        Example e = new Example(); // Folosim metoda main() și clasa Example
        e.printHello();  // Folosim metoda printHello()
    }

    public void printHello() {
        System.out.println("Hello, World!");
    }

    public void unusedMethod() {
        // Nu este folosită
    }

    public void anotherUnusedMethod() {
        // Nu este folosită
    }
}
