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
    protected double[] size;
    protected float radius;
    protected String name = "";
    
    public EvoObject(int[] loc, float radius){
        location = new double[2];
        location[0] = loc[0];
        location[1] = loc[1];
        size = new double[2];
        size[0] = radius*2;
        size[1] = radius*2;
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
    public double[] getSize(){
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
