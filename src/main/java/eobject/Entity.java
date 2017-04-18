/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eobject;

import environment.World;
import graphics.MoveController;
import java.util.ArrayList;
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
    
    public double hungerCap;
    double matRate;
    
    double angle;
    
    boolean isDead;
    
    final double TURNRATE = 3;
    final double POWER = 3;

    
    int score;
    
    MoveController controller;
    
    EntityBrain brain;
    
    ArrayList<Sensor> sensors;
    ArrayList<Eye> eyes;
    
    World w;
    

    
    public Entity(int[] loc, float radius, World w){
        super(loc, radius);
        sensors = new ArrayList<>();
        eyes = new ArrayList<>();
        this.w = w;
        angle = 0;
        hunger = 100;
        hungerCap = Double.POSITIVE_INFINITY;
        score = 0;
        brain = null;
        isDead = false;
    }
    public Entity(int[] loc, float radius, double h, World w){
        this(loc, radius, w);
        hunger = h;
    }
    public Entity(int[] loc, float radius, double h, World w, EntityBrain brain){
        this(loc, radius, h, w);
        this.brain = brain;
    }
    
    public void setBrain(EntityBrain brain){
        this.brain = brain;
    }
    public void setupDefaultSensors(){
        
        for(int i=0; i<360; i+=45){
            Sensor s = new Sensor(this, i, 100, Food.class);
            sensors.add(s);
        }
        
    }
    public void setupDefaultEyes(){
        Eye eye = new Eye(this, 300, 50, 0);
        eyes.add(eye);
        
    }
    public void setupDefaultEyes(int max){
        Eye eye = new Eye(this, 300, 50, 0, max);
        eyes.add(eye);
        
    }
    public void addEye(Eye eye){
        eyes.add(eye);
    }
    public void addSensor(Sensor s){
        sensors.add(s);
    }


    public ArrayList<Eye> getEyes(){
        return eyes;
    }
    public void setDead(boolean b){
        isDead = b;
    }
    public boolean isDead(){
        return isDead;
    }
    public void detect(int n){
        
    }
    public World getWorld(){
        return w;
    }

    public double getAngle(){
        return angle;
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
    public void modScore(int mod){
        score = score+mod;
    }
    public ArrayList<Sensor> getSensors(){
        return sensors;
    }

    @Override
    public boolean tick(){
        if(hunger<=0){
            return false;
        }
        score++;
        
        for(Sensor s: sensors){
            s.tick();
            s.detect();
            
        }
        for(Eye e: eyes){
            e.detect();
        }
        int[] vec = new int[2];
        
        if(controller!=null){
            vec = controller.getVector();           
        }
        else if(brain != null){        
            vec = brain.makeDecision();        
        }
        
        angle += (vec[0]*TURNRATE);
        angle = angle%360;
        /*if(angle<0){
            angle = angle+360;
        }*/
            
        for(Sensor s: sensors){
            s.angle+=(vec[0]*TURNRATE);
            s.angle = s.angle%360;
            /*if(s.angle<0){
                s.angle = s.angle+360;
            }*/
        }
        for(Eye e: eyes){
            e.angle += (vec[0]*TURNRATE);
            e.angle = e.angle%360;
            /*if(e.angle<0){
                e.angle = e.angle+360;
            }*/
        }
            
        if(vec[1]!=0){
                
            double dx = -vec[1]*(POWER*Math.sin(Math.toRadians(angle)));
            double dy = -vec[1]*POWER*Math.cos(Math.toRadians(angle));
            location[0] += dx;
            location[1] += dy;
        }
        
        //hunger--;
        //location[0]++;
        //location[1]--;
        return true;
        
    }
    public void modHunger(int n){
        hunger += n;
        
        if(hunger>hungerCap){
            hunger = hungerCap;
        }
    }
    @Override
    public String toString(){
        return "Entity with Hunger "+hunger+" and world location "+Arrays.toString(location);
    }

    public void setController(MoveController entityControl) {
        controller = entityControl;
    }
}
