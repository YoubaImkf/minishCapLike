package main;

import Tile.TileManager;
import entites.Player;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalSize = 16; // 16 X 16 tile
    final int scale = 3;

    public final int tileSize = originalSize * scale; // 48 X 48
    public final int maxScreenCol = 15; // 16
    public final int maxScreenRow = 10; // 12
    public final int screenWidth = tileSize * maxScreenCol;  // 720px
    public final int screenHeight = tileSize * maxScreenRow; // 480px
    KeyHandler keyHandler =  new KeyHandler(); // Object that store key press
    private Thread gameThread ; // Object Thread
    Player player = new Player(this, keyHandler); // Object Player
    TileManager tileManager = new TileManager(this); // Object


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // +performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void StartGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        int FPS = 60;
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0; // current time + 60img/sec
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >=1 ) {
                UpdateAnimation(); // Update position
                repaint(); // Clears the graphic on the panel and executes the paintComponent method to redraw the graphics on this pane
                delta--;
            }
        }
    }

    public void UpdateAnimation(){
        this.player.Update();
    }

    public void paintComponent(Graphics g){ // draw on JPanel

        super.paintComponent(g); // super = parent
        Graphics2D graphics2D = (Graphics2D)g; // sophisticated control over geometry, coordinate transformations, color management, and text layou
        tileManager.drawTiles(graphics2D );
        this.player.draw(graphics2D);
        graphics2D.dispose();
    }
}
