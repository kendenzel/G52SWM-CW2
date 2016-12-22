package com.neet.DiamondHunter.Viewer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ANTOINE J. FEGHALI on 17/12/2016.
 */
public class TileMap extends Item {

    private Image tileset;
    private WritableImage crop;
    private int tileSize = 16;
    private Tile[][] tiles;

    //Map
    private int numCols;
    private int numRows;
    private int width;
    private int height;
    private int[][] map;

    //Graphics
    public GraphicsContext g;

    private void loadTile(){
        tileset = new Image(getClass().getResourceAsStream("ViewerResources/testtileset.gif"));
        int a = (int)(tileset.getHeight()/tileSize);
        int b = (int)(tileset.getWidth()/tileSize);
        tiles = new Tile[b][a];
        PixelReader pixelReader = tileset.getPixelReader();

        for(int x = 0 ; x < tileset.getWidth() ; x+=tileSize){
            crop = new WritableImage(pixelReader, x , 0,tileSize,tileSize);
            tiles[x/16][0] = new Tile(crop,Tile.NORMAL);
        }

        for(int x = 0 ; x < tileset.getWidth() ; x+=tileSize){
            crop = new WritableImage(pixelReader, x, 16,tileSize,tileSize);
            tiles[x/16][1] = new Tile(crop,Tile.BLOCKED);
        }
    }

    private void loadMap() throws IOException {
        InputStream in = getClass().getResourceAsStream("ViewerResources/testmap.map");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        numCols = Integer.parseInt(reader.readLine());
        numRows = Integer.parseInt(reader.readLine());
        map = new int[numRows][numCols];
        width = numCols * tileSize;
        height = numRows * tileSize;

        String delims = "\\s+";
        for(int row = 0; row < numRows; row++) {
            String line = reader.readLine();
            String[] tokens = line.split(delims);
            for(int col = 0; col < numCols; col++) {
                map[row][col] = Integer.parseInt(tokens[col]);
            }
        }
    }

    private void drawMap(GraphicsContext g) throws IOException {
        int parse;
        for(int x = 0; x < numRows; x++) {
            for(int y = 0; y < numCols; y++) {
                parse = map[x][y];
                switch(parse){
                    case 1:
                        g.drawImage(tiles[1][0].getImage(), x*tileSize,y*tileSize);
                        break;

                    case 2:
                        g.drawImage(tiles[2][0].getImage(), x*tileSize,y*tileSize);
                        break;

                    case 3:
                        g.drawImage(tiles[3][0].getImage(), x*tileSize,y*tileSize);
                        break;

                    case 20:
                        g.drawImage(tiles[0][1].getImage(), x*tileSize,y*tileSize);
                        break;

                    case 21:
                        g.drawImage(tiles[1][1].getImage(), x*tileSize,y*tileSize);
                        break;

                    case 22:
                        g.drawImage(tiles[2][1].getImage(), x*tileSize,y*tileSize);
                        break;
                }
            }
        }
    }

    public boolean getMap(int x, int y) throws ArrayIndexOutOfBoundsException{
        boolean canDraw = true;
        try{
            if(map[x][y] >= 20){
                canDraw = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return canDraw;
    }

    public void tileMapManager(GraphicsContext g) throws IOException {
        try{
            loadTile();
            loadMap();
            drawMap(g);
        } catch (IOException e) {
            System.out.println("Map file not found!");
        }
    }

}
