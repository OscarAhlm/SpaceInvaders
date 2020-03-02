package spaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends JPanel {
    private JLabel newGameLabel;
    private JLabel highScoreLabel;
    private Font fontLabels;
    private ImageIcon header;
    private SpaceInvaders mainFrame;

    public StartScreen(SpaceInvaders mainFrame) {
        this.mainFrame = mainFrame;
        initScreen();
    }

    private void initScreen() {
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);
        header = new ImageIcon("images/Header.png");
        fontLabels = new Font("Sansserif", Font.BOLD, 24);
        newGameLabel = new JLabel("New Game");
        highScoreLabel = new JLabel("High Scores");
        newGameLabel.setFont(fontLabels);
        newGameLabel.setForeground(Color.WHITE);
        newGameLabel.addMouseListener(new LabelListener());
        highScoreLabel.setFont(fontLabels);
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.addMouseListener(new LabelListener());

        add(newGameLabel);
        add(highScoreLabel);
        newGameLabel.setLocation(190, 250);
        highScoreLabel.setLocation(180, 300);
        newGameLabel.setSize(200, 50);
        highScoreLabel.setSize(200, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(header.getImage(), 50, 30, this);
    }

    private class LabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == newGameLabel) {
                mainFrame.newGame();
            }

            if(e.getSource() == highScoreLabel) {
                mainFrame.highScoreScreen();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
