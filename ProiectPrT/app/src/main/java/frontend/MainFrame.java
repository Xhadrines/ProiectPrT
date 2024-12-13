package frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import backend.FileUtils;
import backend.UnusedVariableChecker;
import backend.UnusedMethodOrClassChecker;
import backend.StyleChecker;
import backend.RedundantConditionChecker;
import backend.CommentChecker;

/**
 * Fereastra principala a aplicatiei care permite incarcarea, analiza si salvarea codului sursa.
 */
public class MainFrame extends JFrame {
    private JPanel buttonPanel, statusPanel;
    private JButton loadFileButton, saveFileButton, clearButton;
    private JComboBox<String> regularExpressionSelect;
    private JTextArea textArea;
    private JLabel statusLabel;

    /**
     * Constructorul ferestrei principale. Configureaza setarile initiale ale ferestrei.
     */
    public MainFrame() {
        setTitle("Analiza codului sursa - Sandru Alexandru");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
		
        setAlwaysOnTop(true);
		
        setLayout(new BorderLayout());
        
        initializeComponents(); // Initializeaza componentele
        addListeners(); // Adauga ascultatori pentru butoane si selectii
    }

    /**
     * Initializeaza toate componentele vizuale ale ferestrei.
     */
    private void initializeComponents() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        loadFileButton = new JButton("Incarca Fisierul");
        saveFileButton = new JButton("Salveaza Fisierul");
        clearButton = new JButton("Curata Ecranul");
        
        String[] regularExpressionOptions = {
            "Verifica variabile neutilizate", 
            "Verifica metode sau clase neutilizate", 
            "Verifica stilul de formatare", 
            "Verifica conditii redundante", 
            "Verifica comentarii"
        };
        regularExpressionSelect = new JComboBox<>(regularExpressionOptions);
        
        // Adauga butoanele si lista in panoul superior
        buttonPanel.add(loadFileButton);
        buttonPanel.add(regularExpressionSelect);
        buttonPanel.add(saveFileButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false); // Textul nu poate fi modificat de utilizator
        textArea.setText("Autor: Sandru Alexandru\n\n" +
                 "Instructiuni:\n" +
                 "1. Apasa pe 'Incarca Fisierul' pentru a incarca codul tau sursa.\n" +
                 "2. Alege una dintre optiunile din lista pentru a verifica:\n" +
                 "   - Variabile neutilizate\n" +
                 "   - Metode sau clase neutilizate\n" +
                 "   - Stilul de formatare\n" +
                 "   - Conditii redundante\n" +
                 "   - Comentarii inutile sau lipsa\n" +
                 "3. Apasa 'Salveaza Fisierul' pentru a salva rezultatele analizei. (optional)\n" +
                 "4. Apasa 'Curata Ecranul' pentru a sterge textul curent. (optional)\n\n"); // Text informativ
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));
        
        add(scrollPane, BorderLayout.CENTER);

        statusLabel = new JLabel("Status: Asteptand...");
        statusLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        statusPanel.add(statusLabel, BorderLayout.WEST);
        
        add(statusPanel, BorderLayout.SOUTH);
    }

    /**
     * Adauga actiuni pentru butoane si lista derulanta.
     */
    private void addListeners() {
        loadFileButton.addActionListener(e -> loadFile()); // Buton pentru incarcarea fisierului
        saveFileButton.addActionListener(e -> saveFile()); // Buton pentru salvarea fisierului
        clearButton.addActionListener(e -> textArea.setText("")); // Buton pentru curatarea textului
        
        regularExpressionSelect.addActionListener(e -> {
            String selectedOption = (String) regularExpressionSelect.getSelectedItem();
            handleSelectionAction(selectedOption); // Actiune pe baza selectiei
        });
    }

    /**
     * Incarca un fisier si il afiseaza in zona de text.
     */
    private void loadFile() {
        try {
            FileUtils.load(textArea); // Incarca fisierul in textArea
            statusLabel.setText("Status: Fisierul a fost incarcat cu succes.");
            statusLabel.setForeground(Color.BLUE);
        } catch (Exception ex) {
            statusLabel.setText("Status: Eroare la incarcarea fisierului.");
            statusLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Eroare la incarcarea fisierului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Salveaza continutul zonei de text intr-un fisier.
     */
    private void saveFile() {
        try {
            FileUtils.save(textArea); // Salveaza fisierul
            statusLabel.setText("Status: Fisierul a fost salvat cu succes.");
            statusLabel.setForeground(Color.BLUE);
        } catch (Exception ex) {
            statusLabel.setText("Status: Eroare la salvarea fisierului.");
            statusLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Eroare la salvarea fisierului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Proceseaza selectia utilizatorului si efectueaza analiza corespunzatoare.
     */
    private void handleSelectionAction(String selectedOption) {
        String codeContent = textArea.getText();
        String result = "";

        // Executa analiza pe baza optiunii selectate
        switch (selectedOption) {
            case "Verifica variabile neutilizate":
                result = UnusedVariableChecker.checkUnusedVariables(codeContent);
                break;
            case "Verifica metode sau clase neutilizate":
                result = UnusedMethodOrClassChecker.checkUnusedMethodsOrClasses(codeContent);
                break;
            case "Verifica stilul de formatare":
                result = StyleChecker.checkFormattingStyle(codeContent);
                break;
            case "Verifica conditii redundante":
                result = RedundantConditionChecker.checkRedundantConditions(codeContent);
                break;
            case "Verifica comentarii":
                result = CommentChecker.checkComments(codeContent);
                break;
            default:
                System.out.println("Ai selectat: " + selectedOption);
                return;
        }

        textArea.setText(result); // Afiseaza rezultatele in textArea
        statusLabel.setText("Status: Analiza completa.");
        statusLabel.setForeground(Color.BLUE);
    }
}

