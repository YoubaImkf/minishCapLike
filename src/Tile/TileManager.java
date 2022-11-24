package Tile;

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
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow]; // create map that store number in txt map file
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/water.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/wall-grass.png")));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/wall.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath){
        try{

            InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(mapFilePath)); // import txt file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // read content of file

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

                String line = bufferedReader.readLine(); // reading... next lines

                while(col < gamePanel.maxScreenCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxScreenCol){
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

        int col =  0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

            int tilesNum = mapTileNum[col][row]; // index (ex: if = 0 grass ...)

            graphics2D.drawImage(tile[tilesNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }


}
