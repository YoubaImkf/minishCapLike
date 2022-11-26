package main;

import object.OBJ_Key;

public class AssertHandler {

    GamePanel gamePanel;

    public AssertHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        // set position of key
        gamePanel.obj[0] = new OBJ_Key();
        gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;
        // set position of key 2
        gamePanel.obj[1] = new OBJ_Key();
        gamePanel.obj[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 40 * gamePanel.tileSize;

    }
}
