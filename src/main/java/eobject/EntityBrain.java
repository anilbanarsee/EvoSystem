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
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.neat.NEATNetwork;

/**
 *
 * @author Reetoo
 */
public class EntityBrain {
    
    Entity entity;
    NEATNetwork network;
    
    public EntityBrain(Entity e, NEATNetwork net){
        entity = e;
        this.network = net;
    }
    
    public int[] makeDecision(){
        
        ArrayList<Sensor> sensors = entity.getSensors();
        
        MLData inputData = new BasicMLData((sensors.size()*2)*(sensors.size()*2));
        
        
        int index = 0;
        
        for(int i=0; i<sensors.size(); i++){
            
            for(int j=0; j<sensors.size(); j++){
                
                inputData.add(index, sensors.get(i).getFade());
                index++;
                inputData.add(index, sensors.get(i).getDistanceDetected());
                index++;
            }
            
        }
        
        /*for(Sensor s: sensors){
            
            
            inputData.setData(i,s.getFade());
            i++;
            inputData.setData(i,s.getDistanceDetected());
            i++;
        }*/
        
        
        MLData output = network.compute(inputData);
        
        int[] movement = new int[4];
        
        int counter = 0;
        index = 0;
        double v = 0;
        
        for(int i = 0; i<output.size(); i++){
            
            v = v+output.getData(i);
            
            if(counter==4){
                v = v/4; 
                movement[index] = (int) Math.round(v);
                counter = 0;
                v = 0;
                index++;
            }
            else{
                counter++;
            }
            
            
        
        }
        return movement;
        
    }
    
    
}
