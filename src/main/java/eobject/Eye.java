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

/**
 *
 * @author Reetoo
 */
public class Eye {
    
    double viewDistance;
    double splitAngle;
    double angle;
    
    double lastSeenAngle;
    double lastSeenDistance;
    
    Entity entity;
    
    
    private Eye(Entity entity, double viewDistance, double splitAngle, double angle){
        this.entity = entity;
        this.splitAngle = splitAngle;
        this.viewDistance = viewDistance;
        this.angle = angle;
     
    }


    public double[][] getVectors(){
       
        double[][][] vectors = new double[2][2][];
        
        double[] cntr = entity.getLoc();
        

        return null;
        
    }
    
    
    
}
