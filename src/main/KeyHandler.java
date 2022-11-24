package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean up, down, right, left;
    @Override
    public void keyTyped(KeyEvent e) {
       //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

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
