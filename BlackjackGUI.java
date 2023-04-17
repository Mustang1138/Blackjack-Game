import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackjackGUI extends JFrame {
    private game game;

    // Constructor for the BlackjackGUI class
    public BlackjackGUI() {
        game game = new game();
        game.start();
        setTitle("Blackjack");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    // Method to initialize and add GUI components
    private void initComponents() {
        // Create panels
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        // Set panel layouts
        topPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new GridLayout(2, 1));
        bottomPanel.setLayout(new FlowLayout());

        // Add components to the top panel
        JLabel titleLabel = new JLabel("Blackjack");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        // Add components to the center panel
        JLabel playerLabel = new JLabel("Player's Hand:");
        JLabel dealerLabel = new JLabel("Dealer's Hand:");
        JTextArea playerHandArea = new JTextArea();
        JTextArea dealerHandArea = new JTextArea();
        JScrollPane playerHandScrollPane = new JScrollPane(playerHandArea);
        JScrollPane dealerHandScrollPane = new JScrollPane(dealerHandArea);
        playerHandArea.setEditable(false);
        dealerHandArea.setEditable(false);
        centerPanel.add(playerLabel);
        centerPanel.add(playerHandScrollPane);
        centerPanel.add(dealerLabel);
        centerPanel.add(dealerHandScrollPane);

        // Add components to the bottom panel
        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");
        JButton newGameButton = new JButton("New Game");
        bottomPanel.add(hitButton);
        bottomPanel.add(standButton);
        bottomPanel.add(newGameButton);

        // Add action listeners to the buttons
        hitButton.addActionListener(e -> {
            // Add game logic for Hit button
        });

        standButton.addActionListener(e -> {
            // Add game logic for Stand button
        });

        newGameButton.addActionListener(e -> {
            // Add game logic for New Game button
        });

        // Add panels to the main frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Main method to launch the Blackjack GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BlackjackGUI();
        });
    }
}