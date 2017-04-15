/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eobject;

/**
 *
 * @author General
 */
public class EvoObject {
    protected double[] location;
    protected int[] size;
    protected float radius;
    protected String name = "";
    
    public EvoObject(int[] loc, float radius){
        location = new double[2];
        location[0] = loc[0];
        location[1] = loc[1];
        size = new int[2];
        size[0] = 50;
        size[1] = 50;
        this.radius = radius;
    }
        public void setName(String name){
        this.name = name;
    }
    public float getRadius(){
        return radius;
    }
    public double[] getLoc(){
        return location;
    }
    public int[] getSize(){
        return size;
    }
    public boolean tick(){
        return true;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return getName();
    }
}
