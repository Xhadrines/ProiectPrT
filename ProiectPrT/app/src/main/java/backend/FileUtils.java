package backend;

import javax.swing.*;
import java.io.*;

/**
 * Clasa care ofera functionalitati pentru incarcarea si salvarea fisierelor.
 */
public class FileUtils {

    /**
     * Incarca un fisier Java si afiseaza continutul acestuia intr-o zona de text.
     * 
     * @param textArea Zona de text in care se va incarca continutul fisierului.
     */
    public static void load(JTextArea textArea) {
        // Deschide un dialog pentru a alege fisierul
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Java Files", "java"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Citeste fisierul si incarca continutul in zona de text
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString()); // Afiseaza continutul in textArea
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la citirea fisierului: " + ex.getMessage(),
                        "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Salveaza continutul unei zone de text intr-un fisier Java.
     * 
     * @param textArea Zona de text care contine datele ce trebuie salvate.
     */
    public static void save(JTextArea textArea) {
        // Deschide un dialog pentru a alege locatia unde sa salvezi fisierul
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Java Files", "java"));

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Adauga extensia .java daca nu este deja prezenta
            if (!selectedFile.getName().endsWith(".java")) {
                selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".java");
            }

            // Salveaza continutul textArea in fisier
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(textArea.getText()); // Scrie continutul in fisier
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la salvarea fisierului: " + ex.getMessage(),
                        "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

