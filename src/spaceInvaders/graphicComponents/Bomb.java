package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;

import javax.swing.*;

public class Bomb extends MovingObject {

    public Bomb(int x, int y) {
        setX(x);
        setY(y);
        setImage(new ImageIcon("images/Bomb.png"));
        setWidthAndHeight(Commons.BOMB_WIDTH, Commons.BOMB_HEIGHT);
    }

    public void move() {
        setY(getY() + 1);
    }
}
