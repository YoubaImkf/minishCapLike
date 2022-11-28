package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minish cap?"); // set title

        window.setIconImage(new ImageIcon("src/main/icons/The_Legend_of_Zelda_The_Minish_Cap_Logo.png").getImage()); // set icon


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null); // center the window
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.StartGameThread();
    }
}