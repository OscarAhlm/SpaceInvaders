package spaceInvaders;



import javax.swing.*;
import java.awt.*;

public class SpaceInvaders extends JFrame {
    private StartScreen startScreen;
    private PlayArea gameScreen;
    private HighScoreScreen highScoreScreen;
    private Driver driver;
    private JPanel mainPanel;
    private CardLayout cl;
    private String[] scores;

    public SpaceInvaders() {
        initGame();
    }

    private void initGame() {
        mainPanel = new JPanel();
        cl = new CardLayout();
        mainPanel.setLayout(cl);
        driver = new Driver();
        startScreen = new StartScreen(this);
        gameScreen = new PlayArea(this);
        highScoreScreen = new HighScoreScreen(this, driver.getNames(),  driver.getScores());
        scores = driver.getScores();
        mainPanel.add(startScreen, "StartScreen");
        mainPanel.add(gameScreen, "GameScreen");
        mainPanel.add(highScoreScreen, "HighScoreScreen");
        add(mainPanel);
        pack();
        setTitle("SpaceInvaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void newGame() {
        gameScreen = new PlayArea(this);
        mainPanel.add(gameScreen, "GameScreen");
        cl.show(mainPanel, "GameScreen");
        gameScreen.requestFocus();
    }

    public void homeScreen() {
        cl.show(mainPanel, "StartScreen");
        startScreen.requestFocus();
    }

    public void highScoreScreen() {
        cl.show(mainPanel, "HighScoreScreen");
        highScoreScreen.requestFocus();
    }

    public void checkForNewHighScore(int score) {
        String name;
        try {
            if(score > Integer.parseInt(scores[9])) {
                name = JOptionPane.showInputDialog("You just set a new high score. Enter your name for eternal glory!", null);
                if(name.length() != 3) {
                    name = JOptionPane.showInputDialog("A true legend has three characters in his name, try again!", null);
                }
                driver.setNewScore("" + name, String.format("%03d", score));
                highScoreScreen.updateScores(driver.getNames(), driver.getScores());
            }
        } catch(NullPointerException e) {
            name = JOptionPane.showInputDialog("A true legend has three characters in his name, try again!", null);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders();
        });
    }

}
