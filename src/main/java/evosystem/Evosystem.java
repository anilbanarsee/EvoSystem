/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evosystem;

import entity.Entity;
import environment.World;
import framework.Processor;
import graphics.MoveController;
import graphics.WorldPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

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
        int[] loc = {500,500};
        //w.addEntity(new Entity(loc,20));
        loc[0] = 200;
        //w.addEntity(new Entity(loc,100));
        loc[1] = 600;
        w.addEntity(new Entity(loc,50));
        
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.add(wp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MoveController mc = new MoveController();
        wp.setMoveController(mc);
        frame.addKeyListener(mc);
        
        Thread mainThread = new Thread(new Processor(w, wp));
        mainThread.start();
        Thread panelThread = new Thread(wp);
        panelThread.start();
        System.out.println("-------------------TEST----------------------");
        
    }
    
}
