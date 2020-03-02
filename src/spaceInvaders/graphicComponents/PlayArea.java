package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;
import spaceInvaders.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayArea extends JPanel {
    private SpaceInvaders mainFrame;
    private PlayerShip playerShip;
    private List<Alien> aliens;
    private Shot shot;
    private Timer timer;
    private int score = 0;
    private int aliensKilled = 0;
    private int highScore = 0;
    private int alienDx = 1;
    private boolean levelBeaten;
    private boolean gameOver = false;
    private Random bombGenerator;

    public PlayArea(SpaceInvaders mainFrame) {
        this.mainFrame = mainFrame;
        initPlayArea();
    }

    private void initPlayArea() {
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new PlayerShipAdapter());
        bombGenerator = new Random();
        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
        initGame();
    }

    private void initGame() {
        playerShip = new PlayerShip();
        shot = new Shot();
        shot.kill();
        aliens = new ArrayList<Alien>();

        for(int i = 0; i < 4 ; i++) {
            for(int j = 0; j < 10; j++) {
                Alien alien = new Alien(Commons.ALIEN_STARTX + 20 * j, Commons.ALIEN_STARTY + 18 * i);
                aliens.add(alien);
            }
        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(playerShip.getImage(), playerShip.getX(), playerShip.getY(), this);
    }

    private void drawShot(Graphics g) {
        if(shot.isAlive()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawAliens(Graphics g) {
        for(Alien alien : aliens) {
            Bomb bomb = alien.getBomb();
            if(alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
                if(bomb.isVisible()) {
                    g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
                }
            }
            if(!alien.isAlive()) {
                alien.setVisible(false);
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 30, 20);
    }

    private void drawHighScore(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + highScore, 370, 20);
    }

    private void drawLevelBeat(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Congratulations! You've beaten this level.", 120, Commons.BOARD_HEIGHT/2);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game Over!", 200, Commons.BOARD_HEIGHT/2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawShot(g);
        drawAliens(g);
        drawScore(g);
        drawHighScore(g);
        if(levelBeaten) {
            drawLevelBeat(g);
        }
        if(gameOver) {
            drawGameOver(g);
        }
    }

    private void update() {
        playerShip.update();

        if(aliensKilled >= aliens.size()) {
            levelBeat();
        }

        if(shot.isAlive()) {
            if(shot.getY() <= 0) {
                shot.kill();
            }
            shot.setY(shot.getY() - 5);
        }

        boolean lowerAliens = false;
        for(Alien alien : aliens) {
            if(alien.isAlive() && alien.getBounds().intersects(playerShip.getBounds())) {
                gameOver();
            }
            else if((alien.getX() + Commons.ALIEN_WIDTH) >= Commons.BOARD_WIDTH) {
                alienDx = -1;
                lowerAliens = true;
                break;
            }
            else if(alien.getX() <= 0) {
                alienDx = 1;
                lowerAliens = true;
                break;
            }
        }

        for(Alien alien : aliens) {
            Bomb bomb = alien.getBomb();
            if(!alien.isAlive()) {
                continue;
            }
            if(bomb.isVisible()) {
                if(bomb.getBounds().intersects(playerShip.getBounds())) {
                    gameOver();
                }
                else if(bomb.getY() + Commons.BOMB_HEIGHT >= Commons.BOARD_HEIGHT) {
                    bomb.setVisible(false);
                }
                else {
                    bomb.move();
                }
            }
            if(bombGenerator.nextInt(1000) == 1 && !bomb.isVisible()) {
                alien.bomb();
            }

            if(shot.isAlive() && alien.getBounds().intersects(shot.getBounds())) {
                alien.kill();
                shot.kill();
                score++;
                aliensKilled++;
                continue;
            }
            if(lowerAliens) {
                alien.setY(alien.getY() + Commons.ALIEN_HEIGHT);
            }
            alien.setX(alien.getX() + alienDx);
        }
    }

    private void levelBeat() {
        levelBeaten = true;
        timer.stop();
    }

    private void gameOver() {
        playerShip.setImage(new ImageIcon("images/ShipExplosion.png"));
        gameOver = true;
        highScore = score;
        timer.stop();
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

            if(key == KeyEvent.VK_ESCAPE) {
                mainFrame.homeScreen();
            }

            if(key == KeyEvent.VK_SPACE) {
                if(gameOver) {
                    score = 0;
                    initPlayArea();
                    gameOver = false;
                }
                if(levelBeaten) {
                    initPlayArea();
                    levelBeaten = false;
                    aliensKilled = 0;
                }
                if(!shot.isAlive()) {
                    shot = new Shot(playerShip.getX());
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            playerShip.keyReleased(e);
        }
    }

}
