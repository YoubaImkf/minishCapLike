package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {

    private final GamePanel gamePanel;
    public  Tile[] tile;
    public int[][] mapTileNum; // map's data stored in
    public ArrayList<String> fileNames = new ArrayList<>();
    public ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        // Read Data map file
        InputStream inputStream = getClass().getResourceAsStream("/maps/link-house-data.txt");
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //Read info name and collision
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) { // if line not null add
                fileNames.add(line);
                collisionStatus.add(bufferedReader.readLine());
            }
            bufferedReader.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        // Initialize the tile array #Name
        this.tile = new Tile[fileNames.size()]; // number of tile
        getTileImages();

        // Get maw size map
        inputStream = getClass().getResourceAsStream("/maps/link-house.txt");
        assert inputStream != null;
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String line2 = bufferedReader.readLine();
            String[] maxTile = line2.split(" ");

            gamePanel.maxWorldCol = maxTile.length; // map size 100x100
            gamePanel.maxWorldRow = maxTile.length; // map size 100x100
            mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; // create map that store number in txt map file
            bufferedReader.close();

        }catch (IOException e){
            System.out.println("Exception ! ");;
        }
        loadMap("/maps/link-house.txt");

        /*loadMap("/maps/world01.txt");*/
    }

    public void getTileImages() {

        for (int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision;

            // Get file name
            fileName = fileNames.get(i);
            // Get collisions
            collision = collisionStatus.get(i).equals("true");
            setupImages(i, fileName, collision);
        }


 /*      before ...
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/south-hyrule-plant.png")));
            tile[4].collision = true;
*/
    }

    public void setupImages(int index, String imageName, boolean collision){

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house-split/" + imageName)));
//            tile[index].image = uTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
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
            if(worldX + gamePanel.tileSize  > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize  > gamePanel.player.worldY - gamePanel.player.screenY &&
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
