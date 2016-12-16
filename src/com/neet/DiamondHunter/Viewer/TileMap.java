package com.neet.DiamondHunter.Viewer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Created by ANTOINE J. FEGHALI on 17/12/2016.
 */
public class TileMap {

    private Image tileset;
    private WritableImage crop;
    private int tileSize = 16;
    private Tile[][] tiles;

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

}
