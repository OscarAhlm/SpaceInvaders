package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayArea extends JPanel {
    private JLabel scoreLbl = new JLabel("Score: ");
    private JLabel highScoreLbl = new JLabel("High Score: ");
    private Font scoreFont = new Font("SansSerif", Font.BOLD, 14);
    private PlayerShip playerShip;
    private Shot shot;
    private Timer timer;
    private int score;

    public PlayArea() {
        initPlayArea();
    }

    public void initPlayArea() {
        setSize(new Dimension(500, 400));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new PlayerShipAdapter());
        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
        scoreLbl.setForeground(Color.WHITE);
        highScoreLbl.setForeground(Color.WHITE);
        scoreLbl.setFont(scoreFont);
        highScoreLbl.setFont(scoreFont);
        initGame();
    }

    private void initGame() {
        playerShip = new PlayerShip();
        shot = new Shot();
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(playerShip.getImage(), playerShip.getX(), playerShip.getY(), this);
    }

    private void drawShot(Graphics g) {
        if(shot.isAlive()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 30, 20);
    }

    private void drawHighScore(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Highscore: ", 370, 20);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawShot(g);
        drawScore(g);
        drawHighScore(g);
    }

    private void update() {
        playerShip.update();

        if(shot.isAlive()) {
            shot.setY(shot.getY() - 3);
        }
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class PlayerShipAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            playerShip.keyPressed(e);

            int key = e.getKeyCode();

            if(key == KeyEvent.VK_SPACE) {
                shot = new Shot(playerShip.getX());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            playerShip.keyReleased(e);
        }
    }

}
