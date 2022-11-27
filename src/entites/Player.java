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
        this.screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);  // the player screen ( is to center the player  )
        this.screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2); // does not change   ( because we take top left of image )
        solidArea = new Rectangle(13, 16, 32, 32); //. Object that simulate the solid shape of the player
        setDefaultValue();
        getPlayerImg();
    }

    public void setDefaultValue(){
        worldX = gamePanel.tileSize * 23;   // default playerX position
        worldY= gamePanel.tileSize * 32;   // default playerY position
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
    public void Update() { // called 60 time by sec
        // if key pressed then moving
        if (keyHandler.up || keyHandler.down || keyHandler.right || keyHandler.left) {
            // check direction
            if (keyHandler.up) {
                direction = "up";
            }
            if (keyHandler.down) {
                direction = "down";
            }
            if (keyHandler.left) {
                direction = "left";
            }
            if (keyHandler.right) {
                direction = "right";
            }

            // Check Collision
            collisonOn = false;
            gamePanel.collisionChecker.checkTile(this); // entity is the player that implement Entity

            // if collision true player move
            if (collisonOn) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
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
    }

    public void drawPlayer(Graphics2D graphics2D){ // Sprite changer

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
            case "left" -> {
                if (spriteNum == 1) {
                    img = left1;
                }
                if (spriteNum == 2) {
                    img = left2;
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
        }
        graphics2D.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null); // draw player image

    }
}
