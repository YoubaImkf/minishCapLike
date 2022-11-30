package main;

import object.OBJ_Key;

public class ItemHandler {

    GamePanel gamePanel;

    public ItemHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        // set position of key
        gamePanel.obj[0] = new OBJ_Key(gamePanel);
        gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;
        // set position of key 2
        gamePanel.obj[1] = new OBJ_Key(gamePanel);
        gamePanel.obj[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 32 * gamePanel.tileSize;

    }
}
