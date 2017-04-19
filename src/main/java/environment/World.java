/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import eobject.DropZone;
import eobject.Entity;
import eobject.EvoObject;
import eobject.Food;
import java.util.ArrayList;
import util.MathUtils;

/**
 *
 * @author General
 */


public class World {
    
    
    
    public static final int FOOD_COLLECTION = 0;
    public static final int RESOURCE_GATHERING = 1;

    public static int gameMode = 0;
    
    private ArrayList<EvoObject> eObjects;
    private ArrayList<Entity> graveyard;

    private int[] worldSize;
    
    public World(){
        eObjects = new ArrayList<>();
        graveyard = new ArrayList<>();
        worldSize = new int[2];
        worldSize[0] = 2000;
        worldSize[1] = 2000;
    }
    
    public void addObject(EvoObject e){
        eObjects.add(e);
    }
    
    public ArrayList<EvoObject> getObjects(){
        return eObjects;
    }
    public ArrayList<Entity> getGraveyard(){
        return graveyard;
    }
    public ArrayList<EvoObject> getObjectsInLine(double[] loc, double angle, float distance){
        
        double[] P1 = {loc[0], loc[1]};
        double[] P2 = {loc[0]+(distance*Math.sin(angle)),loc[1]+(distance*Math.cos(angle))};
        
        ArrayList<EvoObject> eObjs = new ArrayList<>();
        
        for(EvoObject eObj: getObjectsInRadius(loc, distance)){
            
            double x0 = eObj.getLoc()[0];
            double y0 = eObj.getLoc()[1];
            
            double dx = ((P2[1]-P1[1])*x0)-((P2[0]-P1[0])*y0)+(P2[0]*P1[1])-(P2[1]*P1[0]);
            
            if(dx<0) dx = dx*-1;
            
            dx = dx/Math.sqrt(((P2[1]-P1[1])*(P2[1]-P1[1]))+((P2[0]-P1[0])*(P2[0]-P1[0])));
            
            if(dx<(eObj.getRadius())){
                if(MathUtils.between(x0, P1[0], P2[0])||MathUtils.between(y0, P1[1], P2[1]))
                    eObjs.add(eObj);
            }
            
        }
        return eObjs;
        
    }
    public ArrayList<EvoObject> getObjectsInRadius(double[] loc, float radius){
        ArrayList<EvoObject> eObjs = new ArrayList<>();
        for(EvoObject eObj: eObjects){
            double[] tloc = eObj.getLoc();
            double distance = Math.sqrt(((loc[0]-tloc[0])*(loc[0]-tloc[0]))+((loc[1]-tloc[1])*(loc[1]-tloc[1])));
            if(distance<(eObj.getRadius()+radius)){
                eObjs.add(eObj);
            }
        }
        return eObjs;
    }
    public ArrayList<EvoObject> getObjectsInRadius(double[] loc, double radius){
        ArrayList<EvoObject> eObjs = new ArrayList<>();
        for(EvoObject eObj: eObjects){
            double[] tloc = eObj.getLoc();
            double distance = Math.sqrt(((loc[0]-tloc[0])*(loc[0]-tloc[0]))+((loc[1]-tloc[1])*(loc[1]-tloc[1])));
            if(distance<(eObj.getRadius()+radius)){
                eObjs.add(eObj);
            }
        }
        return eObjs;
    }
    public ArrayList<EvoObject> getObjectsInArea(int[] p1, int[] p2){
        ArrayList<EvoObject> eObjs = new ArrayList<>();
        int x = 0;
        for(EvoObject eObj : eObjects){
            x++;
            //System.out.print(x +" :");
            double[] loc = eObj.getLoc();
            double[] size = eObj.getSize();
            if(MathUtils.between(loc[0], p1[0], p2[0])){
                if(MathUtils.between(loc[1],p1[1],p2[1]))
                    eObjs.add(eObj);
                else {
                    if(loc[1]<p1[1]){
                        if(loc[1]+(size[1]/2)>p1[1]){
               //            System.out.println("bottom edge");
                            eObjs.add(eObj);
                        }
                    }
                    else{
                        if(loc[1]-(size[1]/2)<p2[1]){
                 //           System.out.println("top edge");
                            eObjs.add(eObj);
                        }
                    }
                }
            }
            else if(MathUtils.between(loc[1],p1[1],p2[1])){
                if(MathUtils.between(loc[0], p1[0], p2[0]))
                    eObjs.add(eObj);
                else {
                    if(loc[0]<p1[0]){
                        if(loc[0]+(size[0]/2)>p1[0]){
                            eObjs.add(eObj);
                   //         System.out.println("left edge");
                        }
                    }
                    else{
                        if(loc[0]-(size[0]/2)<p2[0]){
                            eObjs.add(eObj);
                     //       System.out.println("right edge");
                        }
                    }
                }
            }
            else{
                int[] corner = new int[2];
                if(loc[0]<=p1[0]){
                    //we know it's left
                    //System.out.print("left-");
                    corner[0] = p1[0];
                    
                    if(loc[1]>=p2[1]){
                        
                    //we know it's bottom left
                      //  System.out.println("bottom");
                        corner[1] = p2[1];
                    }
                    else{
                        //System.out.println("top");
                        corner[1] = p1[1];
                    //we know it's top left
                    }
                }
                else{
                    //we know it's right
                    //System.out.print("right-");
                    corner[0] = p2[0];
                    if(loc[1]>=p2[1]){
                        corner[1] = p2[1];
                      //  System.out.println("bottom");
                    //we know it's bottom right
                    }
                    else{
                        corner[1] = p1[1];
                        //System.out.println("top");
                    //we know it's top right
                    }
                    
                }
                double dX = corner[0]-loc[0];
                if(dX<0)
                    dX = dX*-1;
                
                double dY = corner[1]-loc[1];
                if(dY<0)
                    dY = dY*-1;
                //System.out.println(dX+","+dY);
                if(dX<size[0]/2&&dY<size[1]/2){
                    eObjs.add(eObj);
                }
                
                
            }
            
           
        }
        return eObjs;
        
    }
    public void tick(){
        ArrayList<EvoObject> bin = new ArrayList<>();
        for(EvoObject e: eObjects){
            if(e instanceof Food){
                ((Food) e).setInVision(false);
             
            }
            if(e instanceof Entity){
                Entity ent = (Entity) e;
                
                if(!ent.tick()){
                    bin.add(e);
                    graveyard.add(ent);
                    ent.setDead(true);
                }
                
                
                if(ent.eating && gameMode == FOOD_COLLECTION ){
                    
                    if(ent.isHoldingFood()){
                        if(ent.holding.isPoison()){
                            ent.modHunger(-ent.holding.getValue());
                        }
                        else{
                            ent.modHunger(ent.holding.getValue());
                        }
                        ent.destroyFood();
                        
                    }
                    else{
                        ent.modHunger(-25);
                    }
                    ent.eating = false;
                }
                
                ArrayList<EvoObject> eObjs = this.getObjectsInRadius(ent.getLoc(), ent.getRadius());
                
                for(EvoObject eb: eObjs){
                    if(eb instanceof Food){
                       Food f = (Food) eb;
                       
                       if(!ent.isHoldingFood()){
                            bin.add(eb);
                            ent.holdFood(f);
                       }
                      /* bin.add(eb);
                       if(!f.isPoison()){
                           ent.modHunger(f.getValue());
                       }
                       else{
                           ent.modHunger(-500);
                          
                       }*/
                    }

                    
                   
                }
                
                if(gameMode==FOOD_COLLECTION){
                    ent.modHunger(-1);
                    ent.modScore(1);
                }
               
                if(gameMode == RESOURCE_GATHERING){
                    DropZone dz = ent.getDropZone();
                    if(ent.dropping && ent.isHoldingFood()){
                        ent.modHunger(100);
                        double dx = ent.getDistanceToDropZone();
                        if(dx<dz.getRadius()){
                        
                            
                            ent.modScore(1000);
                            
                            
                        }
                        else{
                            ent.modScore((int)Math.round(dx/2));
                        }
                        ent.destroyFood();
                        ent.dropping = false;
                    }
                    else if(ent.dropping && !ent.isHoldingFood()){
                        ent.modHunger(-25);
                        ent.dropping = false;
                    }
                   

                    
                }
                if(ent.dropping ){
                    if(ent.isHoldingFood()){
                        ent.destroyFood();
                    }
                    else{
                        ent.modHunger(-25);
                    }
                        
                    ent.dropping = false;
                }
                ent.eating = false;
                ent.dropping = false;
            }
           
        }
        for(EvoObject e: bin){
            eObjects.remove(e);
        }
    }
    
    public String toString(){
        String s = "This world contains :\n";
        for(int i = 0; i<eObjects.size(); i++){
            if(eObjects.get(i) instanceof Entity){
                Entity ent = (Entity) eObjects.get(i);
                s = s+"Entity_"+i+": Hunger = "+ent.getHunger()+";\n";
            }
        }
        return s;
    }
}
