package spaceInvaders.graphicComponents;

import spaceInvaders.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class PlayerShip extends MovingObject{
//   private int x;
//   private int y;
   private int dx;
   private ImageIcon image;

   public PlayerShip() {
       setImage(new ImageIcon("images/PlayerShip.png"));
       setStartPos();
       setWidthAndHeight(Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT);
   }

   public void update() {
      setX(getX() + dx);
   }

   public void setStartPos() {
      setX(Commons.PLAYER_STARTX);
      setY(Commons.PLAYER_STARTY);
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
}
