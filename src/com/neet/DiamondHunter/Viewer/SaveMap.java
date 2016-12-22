package com.neet.DiamondHunter.Viewer;

import java.io.*;

/**
 * Created by Imad Hussain Malik on 12/23/2016.
 */

public abstract class SaveMap {

    private static int axeX;
    private static int axeY;
    private static int shipX;
    private static int shipY;


    public void updateAxe(int X, int Y){
        axeX = X;
        axeY = Y;
    }

    public void updateShip(int X, int Y){
        shipX = X;
        shipY = Y;
    }

    public void saveMap(int[][] map,int numRows,int numCols){
        try (PrintStream out = new PrintStream(new FileOutputStream("Resources/Maps/testmap.map"))) {
            out.print(String.valueOf(axeX)+"\n"+String.valueOf(axeY)+"\n"
                    +String.valueOf(shipX)+"\n"+String.valueOf(shipY)+"\n"
                    +String.valueOf(numRows)+"\n"+String.valueOf(numCols)+"\n");

            for(int row = 0; row < numRows; row++) {
                for(int col = 0; col < numCols; col++) {
                    out.print(map[row][col]+" ");
                }
                out.print("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected int axeX(){
        return axeX;
    }

    protected int axeY(){
        return axeY;
    }

    protected int shipX(){
        return shipX;
    }

    protected int shipY(){
        return shipY;
    }

}
