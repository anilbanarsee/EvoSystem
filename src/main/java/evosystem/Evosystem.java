/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evosystem;

import eobject.Entity;
import environment.World;
import eobject.DropZone;
import eobject.EntityController;
import eobject.Food;
import framework.Processor;
import graphics.MainFrame;
import graphics.MoveController;
import graphics.WorldPanel;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import util.MathUtils;

/**
 *
 * @author General
 */
public class Evosystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        World w = new World();
        WorldPanel wp = new WorldPanel(w);
        wp.addComponentListener(wp);
        int[] loc = {500,500};
        //w.addEntity(new Entity(loc,20));
        loc[0] = 200;
        //w.addEntity(new Entity(loc,100));
        loc[1] = 200;
        
        Entity e1 = new Entity(loc,25,5000, w);
        e1.setupDefaultSensors();
        e1.setupDefaultEyes(2);
        
        Random r = new Random();
        
        int numFood = 30;
        int numPoisonFood = 10;
        boolean testing = false;
        
                int[] dzLoc = {0,50};
        DropZone dz = new DropZone(dzLoc,300);
        w.addObject(dz);
        
        if(!testing){
            
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
        }
        else{
            for(int i=0; i< 2000; i = i+50){
                for(int j=0; j<2000; j=j+50){
                    int[] fLoc = {i,j};
                    Food f = new Food(fLoc, 5, false, 500);
                    f.setName("Food_"+i+","+j);
                    w.addObject(f);
                }
            }
        }

        
        w.addObject(e1);
        e1.setDropZone(dz);
        e1.setName("Entity_1");
       /* JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.add(wp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */

        //MoveController mc = new MoveController('a','w','d','s');

        EntityController entityControl = new EntityController('v','b');
        //wp.setMoveController(mc);
        e1.setController(entityControl);
        
        
        //frame.addKeyListener(mc);
        
        Processor p = new Processor(w);
        
        
        
        Thread mainThread = new Thread(p);
        
        MainFrame frame = new MainFrame(wp, p);
        
        frame.addKeyListener(entityControl);
        
        p.setMainFrame(frame);
        
        mainThread.start();
        Thread panelThread = new Thread(wp);
        panelThread.start();
        System.out.println("-------------------TEST----------------------");
        
    }
    
}
