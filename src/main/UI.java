package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class UI implements ActionListener {

    public GamePanel gamePanel;
    private Graphics2D graphics2D;
    Timer timer = new Timer(20, this); // swing timer
    float alphaValueIn = 1f; // getInstance accept only float that why

    public String message = "";
    public boolean messageOn = false;
    public int messageCounter = 0;
    public boolean gameFinished =  false;
    public int commandNum = 0;
    Font arial_40;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //font ..
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void drawUI(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        //set font, color
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.WHITE);

        //Title state
        if(gamePanel.gameState == gamePanel.titleState){
            drawTitleScreen();
        }
        else if(gamePanel.gameState == gamePanel.playState){
            //
        }
        else if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
    }

    private void drawTitleScreen() {
        try {
            // Background Image
            BufferedImage background = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/main/icons/title-screen-minish-without-start.png")));
            Image image = background.getScaledInstance(gamePanel.screenWidth, gamePanel.screenHeight,Image.SCALE_DEFAULT);
            graphics2D.drawImage(image, 0, 0, null);
            loadBufferedImage();
            timer.start();

            // text
//            String text = "Just image.ENTER=start|ECHAP=pause";
//            graphics2D.setColor(Color.blue);
//
//            int x = GetXtoCenterText(text);
//            int y = gamePanel.tileSize*6;
//            graphics2D.drawString(text, x, y);

        }catch(Exception e){
            e.printStackTrace();
        }

        // set dynamic images ...


    }

    private void drawPauseScreen(){

        try {
            // Background Image
            BufferedImage background = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/main/icons/menu-minish.png")));
            Image image = background.getScaledInstance(gamePanel.screenWidth, gamePanel.screenHeight,Image.SCALE_DEFAULT);
            graphics2D.drawImage(image, 0, 0, null);

            // text
            String text = "Just an image...ECHAP=pause";
            graphics2D.setColor(Color.blue);

            int x = GetXtoCenterText(text);
            int y = gamePanel.tileSize * 6;
            graphics2D.drawString(text, x, y);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private int GetXtoCenterText(String text){
        int lenght = (int)graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();
        return gamePanel.screenWidth / 2 - lenght / 2; // display text in the center of the screen
    }

    private void loadBufferedImage(){
        try {
            BufferedImage  imageFadeIn = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/main/icons/press-start.png")));
            Image imageResized =  imageFadeIn .getScaledInstance(270, 49,Image.SCALE_DEFAULT);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValueIn));

            int x = gamePanel.screenWidth/ 2 - 270 / 2;
            int y =  (gamePanel.tileSize - 1) * 8;
            graphics2D.drawImage(imageResized, x, y, null);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    * Allow to fade in/out an image btw it sucks x)
    * */
    public void actionPerformed(ActionEvent e) { // Fade in fade out
        alphaValueIn -= 0.2f;

        if (alphaValueIn < 0) {
            alphaValueIn = 1; // repaint the image
            timer.stop();
        }

    }
}
