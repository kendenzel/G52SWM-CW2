package com.neet.DiamondHunter.Viewer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


public abstract class Item extends SaveMap{

    private int tileSize = 16;
    private Image tileset;
    private Tile[][] items;
    private Tile parseItem;
    private WritableImage crop;
    private ImageView test;



    public void loadItems(){
        tileset = new Image(getClass().getResourceAsStream("ViewerResources/items.gif"));
        int a = (int)(tileset.getHeight()/tileSize);
        int b = (int)(tileset.getWidth()/tileSize);
        items = new Tile[b][a];
        PixelReader pixelReader = tileset.getPixelReader();

        for(int y = 0 ; y < tileset.getHeight() ; y+=tileSize){
            for(int x = 0 ; x < tileset.getWidth() ; x+=tileSize){
                crop = new WritableImage(pixelReader, x, y,tileSize,tileSize);
                items[x/16][y/16] = new Tile(crop,Tile.ITEM);
            }
        }
    }


    public void drawAxe(int axeX, int axeY, GraphicsContext g){
        g.drawImage(items[1][1].getImage(),axeX*tileSize,axeY*tileSize);
        updateAxe(axeX,axeY);

    }
    public void drawShip(int shipX, int shipY, GraphicsContext g){
        g.drawImage(items[0][1].getImage(),shipX*tileSize,shipY*tileSize);
        updateShip(shipX,shipY);

    }


    public void itemManager(){
        loadItems();
    }

}
