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
import util.MathUtils;

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
    
    public boolean eating;
    public boolean dropping;
    
    final double TURNRATE = 3;
    final double POWER = 3;

    
    int score;
    
    EntityController controller;
    
    EntityBrain brain;
    
    ArrayList<Sensor> sensors;
    DropZone dropZone;
    ArrayList<Eye> eyes;
    
    public Food holding;
    
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
        eating = false;
        dropping = false;
    }
    public Entity(int[] loc, float radius, double h, World w){
        this(loc, radius, w);
        hunger = h;
    }
    public Entity(int[] loc, float radius, double h, World w, EntityBrain brain){
        this(loc, radius, h, w);
        this.brain = brain;
    }
    public void setDropZone(DropZone dz){
        this.dropZone = dz;
    }
    public DropZone getDropZone(){
        return dropZone;
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
    
    public double getDistanceToDropZone(){
        return MathUtils.getDistanceBetween(this.getLoc(), this.dropZone.getLoc());
    }
    public double getAngleToDropZone(){
        double d = angle-MathUtils.getAngleToNormal(this.getLoc(), this.dropZone.getLoc());
        if(d>180){
            d-=360;
        }
        else if(d<-180){
            d+=360;
        }
        return d;
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
    public void setEating(boolean b){
        eating = b;
    }
    public boolean isEating(){
        return eating;
    }
    public void destroyFood(){
        holding = null;
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
        for(int i=0; i<vec.length; i++){
            if(vec[i]>1){
                vec[i]=1;
            }
            else if(vec[i]<-1){
                vec[i]=-1;
            }
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
            
        
                
        double dx = -vec[1]*(POWER*Math.sin(Math.toRadians(angle)));
        double dy = -vec[1]*POWER*Math.cos(Math.toRadians(angle));
        
        //System.out.println(dx+","+dy+"(("+-vec[1]+"))");
        
        location[0] += dx;
        location[1] += dy;
        
        if(vec[2]==1){
           
            this.eating = true;
            
        }
        else if(vec[2] == -1){
            this.dropping = true;
        }
        
        
        //hunger--;
        //location[0]++;
        //location[1]--;
        return true;
        
    }

    public EntityBrain getBrain(){
        return brain;
    }
    public boolean isHoldingFood(){
        return holding!=null;
    }
    public void holdFood(Food food){
        if(food!=null){
            holding = food;
        }
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

    public void setController(EntityController entityControl) {
        controller = entityControl;
    }
}
