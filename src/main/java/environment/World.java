/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import entity.Entity;
import entity.EvoObject;
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
    public ArrayList<EvoObject> getObjects(int[] p1, int[] p2){
        ArrayList<EvoObject> eObjs = new ArrayList<>();
        int x = 0;
        for(EvoObject eObj : eObjects){
            x++;
            //System.out.print(x +" :");
            int[] loc = eObj.getLoc();
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
                int dX = corner[0]-loc[0];
                if(dX<0)
                    dX = dX*-1;
                
                int dY = corner[1]-loc[1];
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
        for(EvoObject e: eObjects){
            
            if(e instanceof Entity){
                Entity ent = (Entity) e;
                if(!ent.tick()){
                    eObjects.remove(e);
                    graveyard.add(ent);
                    return;
                }
                if(ent.getLoc()[0]>100){
                    
                    ent.modHunger(-1);
                    
                }
            }
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
