package spaceInvaders;

import spaceInvaders.graphicComponents.PlayArea;

import javax.swing.*;
import java.awt.*;

public class SpaceInvaders extends JFrame {

    public SpaceInvaders() {
        initGame();
    }

    private void initGame() {
        add(new PlayArea());
        setSize(new Dimension(500, 400));
        setTitle("SpaceInvaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders();
        });
    }

}
