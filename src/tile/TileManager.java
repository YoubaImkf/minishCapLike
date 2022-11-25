package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanel gamePanel;
    private final Tile[] tile;
    int[][] mapTileNum; // map's data stored in
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tile = new Tile[10]; // number of tile
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; // create map that store number in txt map file
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){

        try{
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/south-hyrule-plant.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/south-hyrule-grass-way.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall-grass.png")));
            tile[4].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[4].collision = true;

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath){
        try{

            InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(mapFilePath)); // import txt file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // read content of txt file

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){

                String line = bufferedReader.readLine(); // reading... next lines

                while(col < gamePanel.maxWorldCol){

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawTiles(Graphics2D graphics2D){

        int worldCol =  0;
        int worldRow = 0;


        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

            int tilesNum = mapTileNum[worldCol][worldRow]; // index (ex: if = 0 grass ...)
            int worldX = worldCol * gamePanel.tileSize; // check col
            int worldY = worldRow * gamePanel.tileSize; // check row
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            // Condition to load image tiles based on player ratio screen (10 cuz of some bugs black bar)
            if(worldX + gamePanel.tileSize + 10 > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize + 10 > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                graphics2D.drawImage(tile[tilesNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldCol++;

            if(worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }


}
