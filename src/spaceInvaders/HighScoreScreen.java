package spaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HighScoreScreen extends JPanel {
    private SpaceInvaders mainFrame;
    private JLabel highScoreLabel;
    private JLabel headersLabel;
    private Font highScoreFont;
    private Font headerFont;
    private JLabel[] highScores;

    public HighScoreScreen(SpaceInvaders mainFrame, String[] names, String[] scores) {
        this.mainFrame = mainFrame;
        initScreen();
        initScores(names, scores);
    }

    private void initScreen() {
        setLayout(new GridLayout(12, 1));
        setBackground(Color.BLACK);
        addKeyListener(new KeyListener());
        highScoreFont = new Font("Sansserif", Font.BOLD, 24);
        highScoreLabel = new JLabel("High Scores");
        highScoreLabel.setFont(highScoreFont);
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        headerFont = new Font("Sansserif", Font.BOLD, 16);
        headersLabel = new JLabel("Name   Score");
        headersLabel.setFont(headerFont);
        headersLabel.setForeground(Color.WHITE);
        headersLabel.setHorizontalAlignment(JLabel.CENTER);
        add(highScoreLabel);
        add(headersLabel);
        highScores = new JLabel[10];
    }

    private void initScores(String[] names, String[] scores) {
        for(int i = 0; i < highScores.length; i++) {
            JLabel label = new JLabel(names[i] + "   " + scores[i]);
            highScores[i] = label;
            add(label);
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(JLabel.CENTER);
        }
    }

    public void updateScores(String[] names, String[] scores) {
        for(int i = 0; i < highScores.length; i++) {
            highScores[i].setText(names[i] + "   " + scores[i]);
        }
    }

    private class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                mainFrame.homeScreen();
            }
        }
    }
}
