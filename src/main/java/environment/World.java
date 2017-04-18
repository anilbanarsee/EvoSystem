/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

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
            int[] size = eObj.getSize();
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
                ArrayList<EvoObject> eObjs = this.getObjectsInRadius(ent.getLoc(), ent.getRadius());
                for(EvoObject eb: eObjs){
                    if(eb instanceof Food){
                       Food f = (Food) eb;
                       bin.add(eb);
                       if(!f.isPoison()){
                           ent.modHunger(500);
                           
                       }
                       else{
                           ent.modScore(-5000);
                           ent.modHunger(-500);
                       }
                    }
                }
                ent.modHunger(-1);
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
