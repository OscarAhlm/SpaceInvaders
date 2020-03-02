package spaceInvaders.graphicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MovingObject {
    private Image image;
    private boolean alive;
    private boolean visible;
    private int WIDTH;
    private int HEIGHT;
    private int x;
    private int y;
    private Rectangle rect;

    public MovingObject() {
        alive = true;
        visible = true;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean b) {
        this.visible = b;
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

    public void setWidthAndHeight(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void setBounds() {
        rect = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        setBounds();
        return rect;
    }

}
