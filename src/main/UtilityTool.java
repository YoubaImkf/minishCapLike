package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage originalImg, int width, int height){

        BufferedImage bufferedImage = new BufferedImage(width, height, originalImg.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(originalImg, 0, 0, width, height, null);
        graphics2D.dispose();

        return bufferedImage;
    }
}
