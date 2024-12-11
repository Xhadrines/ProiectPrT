public class TestRedundantConditions {
    public void checkRedundant() {
        boolean flag = true;

        if (flag == true) {
            System.out.println("Flag is true");
        }

        if (flag != false) {
            System.out.println("Flag is not false");
        }

        if (flag == false) {
            System.out.println("Flag is false");
        }

        if (flag != true) {
            System.out.println("Flag is not true");
        }
    }
}

