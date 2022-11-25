package entites;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize / 2);  // the player screen ( is to center the player  )
        this.screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize / 2); // does not change   ( because we take top left of image )
        solidArea = new Rectangle(8, 16, 32, 32);
//        solidArea.x = 9;
//        solidArea.y = 16;
//        solidArea.width = 32;
//        solidArea.height = 32;
        setDefaultValue();
        getPlayerImg();
    }

    public void setDefaultValue(){
        worldX = gamePanel.tileSize * 23;   // default playerX position
        worldY= gamePanel.tileSize * 21;   // default playerY position
        speed = 3; // px player will move
        direction =  "down"; // default player image direction
    }

    public void getPlayerImg(){

        try {
            up1    = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-up-not.png")));
            up2    = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-up-moving.png")));
            down1  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-down-not.png")));
            down2  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-down-moving.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-right-not.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-right-moving.png")));
            left1  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-left-not.png")));
            left2  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/link-left-moving.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Update(){ // called 60 time by sec
        // if key pressed then moving
        if(keyHandler.up || keyHandler.down || keyHandler.right || keyHandler.left) {

            if (keyHandler.up) {
                direction = "up";
                worldY -= speed;
            }
            if (keyHandler.down) {
                direction = "down";
                worldY += speed;
            }
            if (keyHandler.left) {
                direction = "left";
                worldX -= speed;
            }
            if (keyHandler.right) {
                direction = "right";
                worldX += speed;
            }

            collisonOn = false;
            gamePanel.collisionChecker.ckeckTile(this); // entity is the player that implement Entity

            spriteCounter++;
            if (spriteCounter > 10) { // regulate moving speed
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D){ // Sprite changer

        BufferedImage img = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    img = up1;
                }
                if (spriteNum == 2) {
                    img = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    img = down1;
                }
                if (spriteNum == 2) {
                    img = down2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    img = right1;
                }
                if (spriteNum == 2) {
                    img = right2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    img = left1;
                }
                if (spriteNum == 2) {
                    img = left2;
                }
            }
        }
        graphics2D.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null); // draw player image

    }
}
