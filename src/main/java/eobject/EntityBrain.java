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
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import simulation.TrainTest;
import static simulation.TrainTest.EYE_COUNT;
import static simulation.TrainTest.EYE_TARGETS;
import static simulation.TrainTest.SENSOR_COUNT;

/**
 *
 * @author Reetoo
 */
public class EntityBrain {
    
    Entity entity;
    MLRegression network;
    public double[] lastInput;
    public double[] lastOutput;
    int inputCount;
    int outputCount;
    
    public EntityBrain(Entity e, MLRegression net){
        entity = e;
        this.network = net;
        inputCount = net.getInputCount();
        outputCount = net.getOutputCount();
        lastInput = new double[inputCount];
        lastOutput = new double[outputCount];
    }
    
    public int getInputCount(){
        return inputCount;
    }
    public int getOutputCount(){
        return outputCount;
    }
    
    public int[] makeDecision(){
        
        ArrayList<Sensor> sensors = entity.getSensors();
        ArrayList<Eye> eyes = entity.getEyes();
        
        int numInputs = (SENSOR_COUNT*2)+(EYE_COUNT*EYE_TARGETS*3)+4;
        
        MLData inputData = new BasicMLData(numInputs);
        
        
        int index = 0;
        
        for(Sensor s: sensors){
            inputData.add(index,s.getFade());
            index++;
            inputData.add(index,s.getDistanceDetected());
            index++;
        }
        for(Eye e: eyes){
            double[] test = e.lastSeenAngle;
            for(int i=0; i<e.MAX_TARGETS; i++){
                inputData.add(index, e.lastSeenAngle[i]);
                index++;
                inputData.add(index, e.lastSeenDistance[i]);
                index++;
                if(e.isPoison[i]){
                    inputData.add(index, 2);
                }
                else{
                    inputData.add(index, 1);
                }
                index++;

            }
        }
        if(entity.isHoldingFood()){
            if(entity.holding.isPoison){
                inputData.add(index, 2);
            }
            else{
                inputData.add(index, 1);
            }
        }
        else{
            inputData.add(index, 0);
        }
        index++;
        inputData.add(index, entity.hunger);
        index++;
        inputData.add(index, entity.getAngleToDropZone());
        index++;
        inputData.add(index, entity.getDistanceToDropZone());
        index++;
        
       /* for(int i=0; i<numInputs; i++){
            
            for(int j=0; j<sensors.size(); j++){
                
                inputData.add(index, sensors.get(j).getFade());
                index++;
                inputData.add(index, sensors.get(j).getDistanceDetected());
                index++;
            }
            for(int j=0; j<eyes.size(); j++){
                inputData.add(index, eyes.get(j).lastSeenAngle);
                index++;
                inputData.add(index, eyes.get(j).lastSeenDistance);
                index++;
            }
            
        }*/
        
        
        /*for(Sensor s: sensors){
            
            
            inputData.setData(i,s.getFade());
            i++;
            inputData.setData(i,s.getDistanceDetected());
            i++;
        }*/
        
        lastInput = inputData.getData();
        
        MLData output = network.compute(inputData);
        
        
        int[] movement = new int[3];
        double[] data = output.getData();
        
        lastOutput = data;
        
        for(int i=0; i<movement.length; i++){
            movement[i] = (int) Math.round((data[i]-0.5)*2);
        }
        
        
        
        return movement;
        
    }
    
    
}
