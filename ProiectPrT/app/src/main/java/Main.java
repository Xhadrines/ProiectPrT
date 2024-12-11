import javax.swing.*;

import frontend.MainFrame;

/**
 * Clasa care contine punctul de intrare al aplicatiei.
 */
public class Main {
    
    /**
     * Punctul de intrare al aplicatiei.
     * 
     * @param args Argumentele din linia de comanda (nefolosit).
     */
    public static void main(String[] args) {
        
        // Ruleaza codul pe thread-ul principal Swing
        SwingUtilities.invokeLater(new Runnable() {
            
            /**
             * Creeaza si afiseaza fereastra principala.
             */
            @Override
            public void run() {
                // Creeaza fereastra principala
                MainFrame mainFrame = new MainFrame();
                
                // Face fereastra vizibila
                mainFrame.setVisible(true);
            }
        });
    }
}

