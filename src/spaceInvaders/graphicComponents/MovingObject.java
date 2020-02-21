package spaceInvaders.graphicComponents;

import javax.swing.*;
import java.awt.*;

public class MovingObject {
    private Image image;
    private boolean alive;
    private int x;
    private int y;

    public MovingObject() {
        alive = true;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setImage(ImageIcon image) {
        this.image = image.getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
