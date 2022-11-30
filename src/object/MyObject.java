package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyObject {

    public BufferedImage image1, image2, image3;
    public String name;
//    public boolean collision = false;
    public int worldX, worldY;
    UtilityTool utilityTool = new UtilityTool();
    public void drawObject(Graphics2D graphics2D, GamePanel gamePanel){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        // Condition to load image tiles based on player ratio screen (10 cuz of some bugs black bar)
        if(worldX + gamePanel.tileSize  > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize  > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            graphics2D.drawImage(image1, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }
}
