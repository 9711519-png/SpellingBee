import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UI {

    private JFrame frame;

    // UI components
    private JLabel titleImage;
    private JLabel timerLabel;
    private JLabel scoreLabel;
    private JLabel messageLabel;

    private JTextField inputField;

    private JButton submitButton;
    private JButton repeatButton;
    private JButton startButton;

    // Reference to GameController
    private GameController controller;

    public UI(GameController controller) {
        this.controller = controller;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("ImpossiSpell");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new CardLayout());

        JPanel titleScreen = buildTitleScreen();
        JPanel gameScreen = buildGameScreen();

        frame.add(titleScreen, "TITLE");
        frame.add(gameScreen, "GAME");

        frame.setVisible(true);
    }
  
    // title screen
    private JPanel buildTitleScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // load title image
        ImageIcon icon = new ImageIcon("images/ISTitle_.png");
        titleImage = new JLabel(icon);
        titleImage.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));

        startButton.addActionListener(e -> {
            showGameScreen();
            controller.startGame();
        });

        panel.add(titleImage, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        return panel;
    }

    // game screen
    private JPanel buildGameScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // timer + score
        JPanel topBar = new JPanel(new GridLayout(1, 2));
        timerLabel = new JLabel("Time: 10", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        topBar.add(timerLabel);
        topBar.add(scoreLabel);

        // input + buttons
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 1));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 22));

        submitButton = new JButton("Submit");
        repeatButton = new JButton("Repeat Word");

        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        repeatButton.setFont(new Font("Arial", Font.BOLD, 20));

        submitButton.addActionListener(e -> {
            String text = inputField.getText();
            controller.receiveInput(text);
            inputField.setText("");
        });

        repeatButton.addActionListener(e -> controller.repeatWord());

        center.add(inputField);
        center.add(submitButton);
        center.add(repeatButton);

        // message area
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 18));

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(messageLabel, BorderLayout.SOUTH);

        return panel;
    }

    // ui update methods
    public void updateTimer(int seconds) {
        timerLabel.setText("Time: " + seconds);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void showMessage(String msg) {
        messageLabel.setText(msg);
    }

    public void showGameScreen() {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "GAME");
    }
}

