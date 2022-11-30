package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends MyObject {
    GamePanel gamePanel;
    public OBJ_Key(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Key";
        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/dungun-key.png")));
            utilityTool.scaleImage(image1, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
