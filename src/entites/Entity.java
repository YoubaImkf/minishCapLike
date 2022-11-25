package entites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX; // position on map
    public int worldY; // position on map
    public int speed; // speed in pixel

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; // Object for different positions
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea; // Object that will represent the collision area of our player
    public boolean collisonOn = false;
}
