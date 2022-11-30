package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;



public class OBJ_Heart  extends MyObject {

    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        name = "Key";
        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/half-heart.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/empty-heart.png")));
            image1 = utilityTool.scaleImage(image1, gamePanel.tileSize-18, gamePanel.tileSize-21);
            image2 = utilityTool.scaleImage(image2, gamePanel.tileSize-18, gamePanel.tileSize-21);
            image3 = utilityTool.scaleImage(image3, gamePanel.tileSize-18, gamePanel.tileSize-21);
    } catch (
    IOException e) {
        e.printStackTrace();
    }
}
}
