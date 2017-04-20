/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import environment.World;
import graphics.MainFrame;
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
    public final int fps = 60;
    public double timeFactor = 1;
    private final World world;
    private MainFrame mframe;
   
    
    public Processor(World w){
        world = w;
        mframe = null;
    }
    public void setMainFrame(MainFrame mframe){
        this.mframe = mframe;
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
                sleep(Math.round((1000-timeDiff)/(fps*timeFactor)));
                //System.out.println(timeFactor);

            } catch (InterruptedException ex) {
                Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            world.tick();
            if(mframe!=null){
                mframe.refresh();
            }
            //int[] vec = {1,1};
            
            //world.tick();
            //wpanel.moveViewport(vec);
            
            //System.out.println(i);
        }
        
    }
}

