/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import graphics.MoveController;
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
    
    int score;
    
    MoveController controller;
    
    
    

    
    public Entity(int[] loc){
        super(loc);
        hunger = 100;
        score = 0;
    }
    public Entity(int[] loc, double h){
        super(loc);
        hunger = h;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getHunger(){
        return hunger;
    }
    public Action think(){
        return null;
    }
    public int getScore(){
        return score;
    }
    
    @Override
    public boolean tick(){
        if(hunger<=0){
            return false;
        }
        score++;
        if(controller!=null){
            int[] vec = controller.getVector();
            location[0] += vec[0];
            location[1] += vec[1];
        }
        else{
            Action action = think();
        }
        //hunger--;
        //location[0]++;
        //location[1]--;
        return true;
        
    }
    public void modHunger(int n){
        hunger += n;
    }
    @Override
    public String toString(){
        return "Entity with Hunger "+hunger+" and world location "+Arrays.toString(location);
    }

    public void setController(MoveController entityControl) {
        controller = entityControl;
    }
}
