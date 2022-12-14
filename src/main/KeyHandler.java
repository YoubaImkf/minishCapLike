package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean up, down, right, left;
    private final GamePanel gamePanel;
    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //title state

        if(gamePanel.gameState == gamePanel.titleState){ // Press Enter to Start the game

            if (keyCode == KeyEvent.VK_ENTER){
                gamePanel.stopMusic(); // stop precedent music

                gamePanel.playSoundEffect(3, -20.0f);

                gamePanel.gameState = gamePanel.playState;

                // wait sound effect I guess not the best way for sure ...
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                gamePanel.playMusic(1, -20.0f);

            }
            if (keyCode == KeyEvent.VK_ESCAPE){ System.exit(0); } // Quit
        }
        if(gamePanel.gameState == gamePanel.pauseState){
            if (keyCode == KeyEvent.VK_BACK_SPACE){ System.exit(0); } // Quit
        }

        if (keyCode == KeyEvent.VK_Z){
            up = true;
        }
        if (keyCode == KeyEvent.VK_S){
            down = true;
        }
        if (keyCode == KeyEvent.VK_Q){
            left = true;
        }
        if (keyCode == KeyEvent.VK_D){
            right = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE){
            if(gamePanel.gameState == gamePanel.playState){
                gamePanel.gameState = gamePanel.pauseState;
                gamePanel.stopMusic();
                gamePanel.playMusic(2, -20f);

            } else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.stopMusic();
                gamePanel.playMusic(1, -20f);
            }
        }

        if (keyCode == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }else if( checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_Z){
            up = false;
        }
        if (keyCode == KeyEvent.VK_S){
            down = false;
        }
        if (keyCode == KeyEvent.VK_Q){
            left = false;
        }
        if (keyCode == KeyEvent.VK_D){
            right = false;
        }
    }
}
