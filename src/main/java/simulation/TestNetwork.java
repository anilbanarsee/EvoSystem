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

import environment.World;
import eobject.DropZone;
import eobject.Entity;
import eobject.EntityBrain;
import eobject.Eye;
import eobject.Food;
import eobject.Sensor;
import framework.Processor;
import graphics.MainFrame;
import graphics.MoveController;
import graphics.WorldPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javax.swing.JFrame;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.PersistNEATPopulation;
import org.encog.persist.EncogDirectoryPersistence;

/**
 *
 * @author Reetoo
 */
public class TestNetwork {
    public static void main(String[] args) throws FileNotFoundException{
    
        String filepath = "C:/tmp/best2.eg";
       
        NEATPopulation pop = (NEATPopulation) EncogDirectoryPersistence.loadObject(new File(filepath));
        
        System.out.println(Simulation.simulate(pop));
      
        System.out.println("Output Count : "+((NEATNetwork) pop.getCODEC().decode(pop.getBestGenome())).getOutputCount());
        World w = new World();
        WorldPanel wp = new WorldPanel(w);
        wp.addComponentListener(wp);
        int[] loc = {500,500};
        //w.addEntity(new Entity(loc,20));
        loc[0] = 200;
        //w.addEntity(new Entity(loc,100));
        loc[1] = 200;
        
        Entity e1 = new Entity(loc,25,500, w);
        
        

        Eye eye = new Eye(e1, TrainTest.ENTITY_VIEW_DISTANCE, TrainTest.ENTITY_VIEW_DEGREE, 0, TrainTest.EYE_TARGETS);
        e1.addEye(eye);
        
        Sensor s = new Sensor(e1, 0, 300, Food.class);
        //e1.addSensor(s);
        
        //e1.setBrain(new EntityBrain(e1,(NEATNetwork) pop.getCODEC().decode(pop.getBestGenome())));
        e1.setBrain(new EntityBrain(e1,pop));
        e1.hungerCap = 500;
        
        int[] dzLoc = {0,50};
        DropZone dz = new DropZone(dzLoc,300);
        w.addObject(dz);
        
        Random r = new Random();
        
        int numFood = TrainTest.FOOD_COUNT;
        int numPoisonFood = TrainTest.POISON_COUNT;
        
        for(int i=0; i<numFood; i++){
            int[] fLoc = {loc[0]+(r.nextInt(1000)-200),loc[1]+(r.nextInt(1000)-200)};

            Food f = new Food(fLoc, 5, false, 500);
            f.setName("Food_"+i);
            w.addObject(f);
        }
        for(int i=0; i<numPoisonFood; i++){
            int[] fLoc = {loc[0]+(r.nextInt(1000)-200),loc[1]+(r.nextInt(1000)-200)};

            Food f = new Food(fLoc, 5, true, 500);
            f.setName("Food_"+i+" (P)");
            w.addObject(f);
        }
        /*for(int i=0; i< 2000; i = i+50){
            for(int j=0; j<2000; j=j+50){
                int[] fLoc = {i,j};
                Food f = new Food(fLoc, 5);
                f.setName("Food_"+i+","+j);
                w.addObject(f);
            }
        }*/
        
        e1.setDropZone(dz);
        w.addObject(e1);
        e1.setName("Entity_1");

        
        
        Processor p = new Processor(w);
        
        
        
        Thread mainThread = new Thread(p);
        
        MainFrame frame = new MainFrame(wp, p);
        
        p.setMainFrame(frame);
        
        mainThread.start();
        Thread panelThread = new Thread(wp);
        panelThread.start();
        System.out.println("-------------------TEST----------------------");
        
        while(!e1.isDead()){
            
        }
        System.out.println(e1.getScore());
    }
}
