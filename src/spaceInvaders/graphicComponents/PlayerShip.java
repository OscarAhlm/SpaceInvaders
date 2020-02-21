package spaceInvaders.graphicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class PlayerShip extends MovingObject{
   private final int WIDTH = 40;
   private final int HEIGHT = 30;
   private final int startX = 230;
   private final int startY = 330;
   private int x;
   private int y;
   private int dx;
   private ImageIcon image;

   public PlayerShip() {
       image = new ImageIcon("images/PlayerShip.png");
       setStartPos();
   }

   public void update() {
      x += dx;
   }

   public void setStartPos() {
      x = startX;
      y = startY;
   }

   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_LEFT) {
         dx = -2;
      }

      if(key == KeyEvent.VK_RIGHT) {
         dx = 2;
      }
   }

   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_LEFT) {
         dx = 0;
      }

      if(key == KeyEvent.VK_RIGHT) {
         dx = 0;
      }
   }

   public int getY() {
      return y;
   }

   public int getX() {
      return x;
   }

   public Image getImage() {
      return image.getImage();
   }

}
