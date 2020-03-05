package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;

import javax.swing.*;

public class Alien extends MovingObject {
    private Bomb bomb;

    public Alien(int x, int y) {
        setImage(new ImageIcon("images/Alien2.png"));
        setX(x);
        setY(y);
        setWidthAndHeight(Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT);
        bomb = new Bomb(x, y);
        bomb.setVisible(false);
    }

    @Override
    public void kill() {
        setImage(new ImageIcon("images/Explosion.png"));
        super.kill();
    }

    public void bomb() {
        bomb = new Bomb(getX() + Commons.ALIEN_WIDTH / 2, getY() + Commons.ALIEN_HEIGHT);
    }

    public Bomb getBomb() {
        return bomb;
    }
}
