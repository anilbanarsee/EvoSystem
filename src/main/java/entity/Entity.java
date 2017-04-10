/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Arrays;

/**
 *
 * @author General
 */
public class Entity extends EvoObject {
    System sys;
    double mass;
    double power;
    
    double hunger;
    double maturity;
    
    double hungerCap;
    double matRate;
    
    public Entity(int[] loc){
        super(loc);
        hunger = 10;
    }
    public Entity(int[] loc, double h){
        super(loc);
        hunger = h;
    }
    public double getHunger(){
        return hunger;
    }
    public Action think(){
        return null;
    }
    @Override
    public boolean tick(){
        if(hunger<=0){
            return false;
        }
        //hunger--;
        location[0]++;
        //location[1]--;
        return true;
        
    }
    @Override
    public String toString(){
        return "Entity with Hunger "+hunger+" and world location "+Arrays.toString(location);
    }
}
