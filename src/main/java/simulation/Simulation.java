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
package simulation;

import eobject.Entity;
import environment.World;
import eobject.DropZone;
import eobject.EntityBrain;
import eobject.Eye;
import eobject.Food;
import eobject.Sensor;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.encog.ml.MLMethod;
import org.encog.ml.MLRegression;
import org.encog.neural.neat.NEATNetwork;

/**
 *
 * @author Reetoo
 */
public class Simulation {
    
    
    public static int simulate(MLRegression network){
        
        World w = new World();
        int[] loc = {200,200};
        
        Entity e1 = new Entity(loc, 25, 500, w);
        e1.setBrain(new EntityBrain(e1, network));
        e1.setName("Alpha");
        e1.hungerCap = 500;
        
        //e1.setupDefaultEyes(TrainTest.EYE_TARGETS);
        Eye eye = new Eye(e1, TrainTest.ENTITY_VIEW_DISTANCE, TrainTest.ENTITY_VIEW_DEGREE, 0, TrainTest.EYE_TARGETS);
        //Sensor s = new Sensor(e1, 0, 300, Food.class);
        e1.addEye(eye);
        //e1.addSensor(s);
        w.addObject(e1);
        int[] dzLoc = {0,50};
        DropZone dz = new DropZone(dzLoc,300);
        w.addObject(dz);
        e1.setDropZone(dz);
        
        int numFood = TrainTest.FOOD_COUNT;
        int numPoisonFood = TrainTest.POISON_COUNT;
        
        Random r = new Random();
        
        for(int i=0; i<numFood; i++){
            int[] fLoc = {loc[0]+(r.nextInt(1000)-200),loc[1]+(r.nextInt(1000)-200)};

            Food f = new Food(fLoc, 5, false, 500);
            f.setName("Food_"+i);
            w.addObject(f);
        }
        for(int i=0; i<numPoisonFood; i++){
            int[] fLoc = {loc[0]+(r.nextInt(1000)-200),loc[1]+(r.nextInt(1000)-200)};

            Food f = new Food(fLoc, 5, true, 500);
            f.setName("Food_"+i);
            w.addObject(f);
        }
        int numTicks;
        if(World.gameMode==World.FOOD_COLLECTION){
            numTicks = 30000;
        }
        else{
            numTicks = 2500;
        }
        int i=0;
        for(i=0; i<numTicks; i++){

            w.tick();
            if(e1.isDead()){
                break;
            }
            
            
        }
        
        
        return e1.getScore();
    }
    public static void main(String[] args){
        EntityScore score = new EntityScore();
        
        
        
    }
    
}
