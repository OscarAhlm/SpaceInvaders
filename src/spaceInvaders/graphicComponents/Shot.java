package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;

import javax.swing.*;

public class Shot extends MovingObject {

    public Shot() {
    }

    public Shot(int x) {
        setImage(new ImageIcon("images/Shot.png"));
        setX(x + (Commons.PLAYER_WIDTH / 2 - 1));
        setY(Commons.PLAYER_STARTY);
        setWidthAndHeight(Commons.SHOT_WIDTH, Commons.SHOT_HEIGHT);
    }
}
