/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author General
 */
public class EvoObject {
    protected int[] location;
    protected int[] size;
    
    public EvoObject(int[] loc){
        location = new int[2];
        location[0] = loc[0];
        location[1] = loc[1];
        size = new int[2];
        size[0] = 50;
        size[1] = 50;
    }
    public int[] getLoc(){
        return location;
    }
    public int[] getSize(){
        return size;
    }
    public boolean tick(){
        return true;
    }
}
