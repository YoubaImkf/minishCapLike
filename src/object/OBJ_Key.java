package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends MyObject {

    public OBJ_Key() {
        name = "Key";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/dungun-key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
