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
package graphics;

import eobject.Entity;
import eobject.EntityBrain;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.encog.neural.neat.NEATPopulation;
import org.encog.persist.EncogDirectoryPersistence;

/**
 *
 * @author Reetoo
 */
public class BrainDisplayPanel extends JPanel{
    
    EntityBrain brain;
    final double NODE_SIZE = 50;
    JLabel nothing;
    
    public BrainDisplayPanel(){
        brain = null;
        initPanel();
    }
    public void setBrain(EntityBrain brain){
        this.brain = brain;
    }
    public final void initPanel(){
        MigLayout layout = new MigLayout("insets 5 5 5 5");
        nothing = new JLabel("[NO ENTITY SELECTED]");
        this.setLayout(layout);
        JLabel title = new JLabel("BRAIN");
        this.setBorder(
            BorderFactory.createTitledBorder("BRAIN"));
        //this.add(title,"wrap 25px");
        if(brain==null){
            
             this.add(nothing, "span,pushx,pushy,align center,gap");
        }
        
    }
    public final void refreshPanel(){
        if(brain==null)
            nothing.setText("[NO ENTITY SELECTED]");
        else
            nothing.setText("");
    }
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        refreshPanel();
        
        
        
        if(brain==null){
            
        }
        else{
            
            int HEIGHT = this.getHeight();
            int WIDTH = this.getWidth();
            
            int numInputs = brain.getInputCount();            
            int numOutputs = brain.getOutputCount();
                       
            int inputLayerY = (int) Math.round(HEIGHT*0.2);
            int outputLayerY = (int) Math.round(HEIGHT*0.8);
            
            int inputILayerY = (int) Math.round(HEIGHT*0.15);
            int outputILayerY = (int) Math.round(HEIGHT*0.9);
            
            int inputLayerDX = (int) Math.round(WIDTH/(numInputs+1));
            int outputLayerDX = (int) Math.round(WIDTH/(numOutputs+1));
            
            int[] sSize = {(int) Math.round(inputLayerDX*0.65),(int) Math.round(inputLayerDX*0.65)};
            
            int[] midLoc = {(int) Math.round(WIDTH/2), (int) Math.round(HEIGHT/2)};
            
            double[] inputs = brain.lastInput;
            double[] outputs = brain.lastOutput;
            int scale = (int) Math.pow(10, 2);
    
            g2d.fill(new Ellipse2D.Double(midLoc[0]-(sSize[0]/2), midLoc[1]-(sSize[0]/2), sSize[0], sSize[1]));
            
            int[] sLoc = {inputLayerDX,inputLayerY};
            int[] sILoc = {inputLayerDX, inputILayerY};
            
            for(int i = 0; i<numInputs; i++){
                
               
                if(inputs[i]>0){
                    g2d.setColor(Color.red);
                }
                else if(inputs[i]<0){
                    g2d.setColor(Color.blue);
                }
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(sLoc[0], sLoc[1], midLoc[0], midLoc[1]);
                g2d.setStroke(new BasicStroke(0));
                g2d.setColor(Color.black);
                g2d.drawString(Math.round(scale *inputs[i])/scale+"", sILoc[0], sILoc[1]);
                
                Ellipse2D.Double circle = new Ellipse2D.Double(sLoc[0]-(sSize[0]/2), sLoc[1]-(sSize[0]/2), sSize[0], sSize[1]);
                g2d.fill(circle);
                
                sLoc[0] += inputLayerDX;
                sILoc[0] += inputLayerDX;
            }
            
            sLoc[0] = outputLayerDX;
            sLoc[1] = outputLayerY;
            
            sILoc[0] = outputLayerDX;
            sILoc[1] = outputILayerY;
            
            
            for(int i = 0; i<numOutputs; i++){
                
                
                
                if(outputs[i]>0){
                    g2d.setColor(Color.red);
                }
                else if(outputs[i]<0){
                    g2d.setColor(Color.blue);
                }
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(sLoc[0], sLoc[1], midLoc[0], midLoc[1]);
                g2d.setStroke(new BasicStroke(0));
                g2d.setColor(Color.black);
                g2d.drawString(Math.round(scale *outputs[i])/scale+"", sILoc[0], sILoc[1]);
                
                Ellipse2D.Double circle = new Ellipse2D.Double(sLoc[0]-(sSize[0]/2), sLoc[1]-(sSize[0]/2), sSize[0], sSize[1]);
                g2d.fill(circle);
                
                sLoc[0] += outputLayerDX;
                sILoc[0] += outputLayerDX;
                
            }
 
            

        }
        
        
    }
    public static void main(String[] args){
        
        BrainDisplayPanel bdp = new BrainDisplayPanel();
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.add(bdp);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        String filepath = "C:/tmp/best2.eg";
       
        NEATPopulation pop = (NEATPopulation) EncogDirectoryPersistence.loadObject(new File(filepath));
        
        
        int[] loc = {0,0};
        Entity e1 = new Entity(loc,0,null);
        EntityBrain brain = new EntityBrain(e1,pop);
        
        bdp.setBrain(brain);
        
        
    }
    
    
}
