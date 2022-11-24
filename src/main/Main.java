package main;

import javax.swing.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minish cap?");

        window.setIconImage(new ImageIcon("src/main/icons/zelda-icon.jpg").getImage());


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.StartGameThread();
    }
}