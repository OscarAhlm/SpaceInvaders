package spaceInvaders;

import spaceInvaders.graphicComponents.PlayArea;

import javax.swing.*;
import java.awt.*;

public class SpaceInvaders extends JFrame {
    private StartScreen startScreen;
    private PlayArea gameScreen;
    private HighScoreScreen highScoreScreen;
    private Driver driver;
    private JPanel mainPanel;
    private CardLayout cl;

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
        highScoreScreen = new HighScoreScreen(this, driver.getScores());
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

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders();
        });
    }

}
