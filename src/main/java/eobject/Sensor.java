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

import environment.World;
import java.util.ArrayList;
import util.MathUtils;

/**
 *
 * @author Reetoo
 */
public class Sensor {
    
    double angle;
    float length;
    Entity entity;
    World world;
    Class lookingFor;
    
    public static double FADE_START = 5.0;
    public static double FADE_DECAY = 0.025;
    
    double distanceDetected;
    double detectFade;
    
    
    public Sensor(Entity e, double angle, float length, Class<? extends EvoObject> looking){
        this.entity = e;
        this.angle = angle;
        this.length = length;
        this.world = e.getWorld();
        this.lookingFor = looking;
        detectFade = 0;
        distanceDetected = 0;
    }
    public void tick(){
        detectFade -= FADE_DECAY;
        if(detectFade<=0){
            distanceDetected = 0;
            detectFade = 0;
        }
        
    }
    public double getFade(){
        return detectFade;
    }
    public double getDistanceDetected(){
        return distanceDetected;
    }
    public double[][] getCoordinates(){
        double[][] coords = new double[2][2];
        
        coords[0][0] = entity.getLoc()[0];
        coords[0][1] = entity.getLoc()[1];
        
        coords[1][0] = coords[0][0]+(length*Math.sin(Math.toRadians(angle)));
        coords[1][1] = coords[0][1]+(length*Math.cos(Math.toRadians(angle)));
        
        return coords;
    }
    public boolean detect(){
        
        int n = 0;
        
        ArrayList<EvoObject> objects = world.getObjectsInLine(entity.getLoc(), Math.toRadians(angle), length);
        
        for(EvoObject e: objects){
            
            if(lookingFor.isInstance(e)){
                
                detectFade = FADE_START;
                distanceDetected = MathUtils.getDistanceBetween(entity.getLoc(), e.getLoc());
                
            }
        }
        
        return true;
        
    }
    
    
    
}
