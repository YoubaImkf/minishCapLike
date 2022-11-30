package main;

import object.MyObject;
import object.OBJ_Heart;

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

    BufferedImage fullHeart, halfHeart, emptyHeart;
    Font arial_40;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //font ..
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        //HUD objects
        MyObject heart = new OBJ_Heart(gamePanel);
        fullHeart = heart.image1;
        halfHeart = heart.image2;
        emptyHeart = heart.image3;
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
            drawPlayerHp();
        }
        else if(gamePanel.gameState == gamePanel.pauseState){
            
            drawPauseScreen();
        }
    }

    private void drawPlayerHp() {
        
        int x = gamePanel.tileSize/2 - gamePanel.tileSize/5;
        int y = gamePanel.tileSize/2;
        int i = 0;
        // DRAW HP
        while(i < gamePanel.player.maxHp / 2){
            graphics2D.drawImage(emptyHeart, x, y, null);
            i++;
            x+=gamePanel.tileSize - (gamePanel.tileSize / 3) -  2; // distance between hearts
        }

        // reset
        x = gamePanel.tileSize/2 - gamePanel.tileSize/5;
        y = gamePanel.tileSize/2;
        i = 0;

        // draw current heart
        while(i < gamePanel.player.hp){
            graphics2D.drawImage(halfHeart, x, y, null);
            i++;
            if(i < gamePanel.player.hp){
                graphics2D.drawImage(fullHeart, x, y, null);
            }
            i++;
            x+=gamePanel.tileSize - (gamePanel.tileSize / 3) - 2;

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
