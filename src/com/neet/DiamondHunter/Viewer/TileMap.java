package com.neet.DiamondHunter.Viewer;

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
public class TileMap {

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

    public void tileMapManager() throws IOException {
        try{
            loadTile();
            loadMap();
        } catch (IOException e) {
            System.out.println("Map file not found!");
        }
    }

}
