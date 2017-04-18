/*
 * Copyright (C) 2017 Reetoo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eobject;

import java.util.ArrayList;
import util.MathUtils;

/**
 *
 * @author Reetoo
 */
public class Eye {
    
    double viewDistance;
    double splitAngle;
    double angle;
    
    double[] lastSeenAngle;
    double[] lastSeenDistance;
    boolean[] isPoison;
    
    boolean canSee;
    
    public final int MAX_TARGETS;
    
    
    
    Entity entity;
    
    
    public Eye(Entity entity, double viewDistance, double splitAngle, double angle){
        
        MAX_TARGETS = 1;
        this.entity = entity;
        this.splitAngle = splitAngle;
        this.viewDistance = viewDistance;
        this.lastSeenAngle = new double[MAX_TARGETS];
        this.lastSeenDistance = new double[MAX_TARGETS];
        this.isPoison = new boolean[MAX_TARGETS];
        this.angle = angle;
        canSee = false;
     
    }
    public Eye(Entity entity, double viewDistance, double splitAngle, double angle, int maxtargets){
        MAX_TARGETS = maxtargets;
        this.entity = entity;
        this.splitAngle = splitAngle;
        this.viewDistance = viewDistance;
        this.lastSeenAngle = new double[MAX_TARGETS];
        this.lastSeenDistance = new double[MAX_TARGETS];
        this.isPoison = new boolean[MAX_TARGETS];
        this.angle = angle;
        canSee = false;
    }


    public double[][][] getVectors(){
       
        double[][][] vectors = new double[2][2][];
        
        double[] cntr = entity.getLoc();
        
        double a = angle-(splitAngle/2);
        for(int i=0; i<2; i++){
            
            double[] p = MathUtils.getEndPointOfVector(cntr, a, viewDistance);
            vectors[i][0] = cntr;
            vectors[i][1] = p;
            a += splitAngle;
            

        }     

        return vectors;
        
    }
    public double getViewDistance(){
        return this.viewDistance;
    }
    public double getSplitAngle(){
        return this.splitAngle;
    }
    public double[] getViewAngle(){
        return lastSeenAngle;
    }
    public double getAngle(){
        return this.angle;
    }
    public void detect(){
        
        ArrayList<EvoObject> eobjects = entity.getWorld().getObjectsInRadius(entity.getLoc(), viewDistance);
        
        Food[] closest = new Food[MAX_TARGETS];
        double[] closestDistance = new double[MAX_TARGETS];
        boolean[] poison = new boolean[MAX_TARGETS];
        for(int i=0; i<MAX_TARGETS; i++){
            closest[i] = null;
            closestDistance[i] = Double.POSITIVE_INFINITY;
        }
        
        for(EvoObject eobj: eobjects){
            if(eobj instanceof Food){
                Food f = (Food) eobj;
                double a = MathUtils.getAngleToNormal(entity.getLoc(), f.getLoc());
                if(MathUtils.between(a, angle-(splitAngle/2), angle+(splitAngle/2))||MathUtils.between(a+360, angle-(splitAngle/2), angle+(splitAngle/2))||MathUtils.between(a-360, angle-(splitAngle/2), angle+(splitAngle/2))){
                        
                    //f.setInVision(true);
                    double dx = MathUtils.getDistanceBetween(entity.getLoc(), f.getLoc());
                    if(dx<closestDistance[closestDistance.length-1]){
                        
                        for(int i=closestDistance.length-1; i>-1; i--){
                            
                            
                            if(closestDistance[i]<dx && i!=closestDistance.length-1){
                                closestDistance = MathUtils.insertIntoFixedArray(closestDistance, dx, i+1);
                                closest = MathUtils.insertIntoFixedArray(closest, f, i+1);

                            }
                            else if(i==0){
                                closestDistance = MathUtils.insertIntoFixedArray(closestDistance, dx, i);
                                closest = MathUtils.insertIntoFixedArray(closest, f, i);
                            }
                            
                        }

                    }
                }

            }
        }
        lastSeenDistance = closestDistance;
        boolean flag = false;
        for(int i=0; i<closest.length; i++){
            if(closest[i]==null){
                lastSeenAngle[i] = 0;
            }
            else{
                double normalAngle = MathUtils.getAngleToNormal(entity.getLoc(), closest[i].getLoc());
                lastSeenAngle[i] = MathUtils.getAngleToNormal(entity.getLoc(), closest[i].getLoc())-angle;
                if(lastSeenAngle[i]>(splitAngle/2)){
                    lastSeenAngle[i] -= 360;
                }
                else if(lastSeenAngle[i]<-(splitAngle/2)){
                    lastSeenAngle[i] += 360;
                }
                isPoison[i] = closest[i].isPoison;
                closest[i].setInVision(true);
                flag = true;
            }
            
        }
        
        canSee = flag;

        
    }
    public boolean getCanSee(){
        return canSee;
    }
    
    
    
}
