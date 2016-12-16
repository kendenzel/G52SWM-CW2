package com.neet.DiamondHunter.Viewer;

import javafx.scene.image.WritableImage;

public class Tile {

    private WritableImage subimage;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    public static final int ITEM = 2;
    private int type;

    Tile(WritableImage image, int type){
        this.subimage = image;
        this.type = type;
    }

    public WritableImage getImage() { return subimage; }

}