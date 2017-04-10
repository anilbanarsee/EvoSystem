/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import environment.World;
import graphics.WorldPanel;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author General
 */
public class Processor implements Runnable{
    private final int fps = 60;
    private final World world;
   
    
    public Processor(World w, WorldPanel wp){
        world = w;
       
    }

    @Override
    public void run() {
           
        long time = System.currentTimeMillis();
        int i = 0;
        while(true){
            i++;
            long newTime = System.currentTimeMillis();
            long timeDiff = newTime-time;
            if(timeDiff>1000)
                timeDiff = 1000;
            
            time = newTime;
            try {
                sleep((1000-timeDiff)/fps);
            } catch (InterruptedException ex) {
                Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            world.tick();
            //int[] vec = {1,1};
            
            //world.tick();
            //wpanel.moveViewport(vec);
            
            //System.out.println(i);
        }
        
    }
}

