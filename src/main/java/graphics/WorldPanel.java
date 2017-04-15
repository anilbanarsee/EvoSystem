/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import eobject.Entity;
import eobject.EvoObject;
import environment.World;
import eobject.Food;
import eobject.Sensor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Ellipse2D;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author General
 */
public class WorldPanel extends JPanel implements Runnable, ComponentListener{
    
    int[] vSize;
    int[] vLoc;
    int movePower = 2;
    int zoomPower = 5;
    public int WIDTH = 500;
    public int HEIGHT = 500;
    private MoveController mc;
    ArrayList<EvoObject> eObjects;
    World w;
    
    public WorldPanel(World w){
        eObjects = new ArrayList<EvoObject>();
        this.w = w;
        vSize = new int[2];
        vSize[0] = WIDTH;
        vSize[1] = HEIGHT;
        vLoc = new int[2];
        vLoc[0] = 0;
        vLoc[1] = 0;
    } 
    public double[] convertPointToViewport(double[] vec){
            
        double[] sLoc = new double[2];
        sLoc[0] = vec[0]-vLoc[0];
        sLoc[1] = vec[1]-vLoc[1];
        sLoc[0] = (int) Math.round(sLoc[0]*(this.getWidth()/(vSize[0]+0.0)));
        sLoc[1] = (int) Math.round(sLoc[1]*(this.getHeight()/(vSize[1]+0.0)));
        
        return sLoc;
    }
    public double[] convertVectorToViewport(double[] vec){
        double[] V = {vec[0], vec[1]};
        
        V[0] = (int) Math.round(V[0]*(this.getWidth()/(vSize[0]+0.0)));
        V[1] = (int) Math.round(V[1]*(this.getHeight()/(vSize[1]+0.0)));
        
        return V;
    }
    @Override
    public void paintComponent(Graphics g){
       // System.out.println(this.getWidth());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        for(EvoObject eObj : eObjects){
            double[] sLoc = convertPointToViewport(eObj.getLoc());
            
            double[] sSize = {50,50};
            sSize = convertVectorToViewport(sSize);
            
            //System.out.println(Arrays.toString(sSize));
            
            if(eObj instanceof Entity){
                Entity ent = (Entity) eObj;

                ArrayList<Sensor> sensors = ent.getSensors();
                
                for(Sensor s: sensors){
                    if(s.getFade()>0){
                        int red = (int) Math.round(155*(s.getFade()/Sensor.FADE_START));
                        g2d.setColor(new Color(100+red,100,100));
                    }
                    else{
                        g2d.setColor(Color.DARK_GRAY);
                    }
                    double[][] coords = s.getCoordinates();
                    double[] P1 = convertPointToViewport(coords[0]);
                    double[] P2 = convertPointToViewport(coords[1]);
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawLine((int) Math.round(P1[0]), (int) Math.round(P1[1]),(int)  Math.round(P2[0]),(int)  Math.round(P2[1]));
                    g2d.setStroke(new BasicStroke(1));
                    
                }
                g2d.setColor(Color.BLACK);
                Ellipse2D.Double circle = new Ellipse2D.Double(sLoc[0]-(sSize[0]/2), sLoc[1]-(sSize[0]/2), sSize[0], sSize[1]);
                g2d.fill(circle);
                g2d.drawString("Hunger :"+ent.getHunger(), (float) sLoc[0]+25,  (float) sLoc[1]-25);
                g2d.drawString("Angle :"+ent.getAngle(), (float)sLoc[0]+25, (float)sLoc[1]+25);
                circle = new Ellipse2D.Double((sLoc[0])+(50*Math.sin(Math.toRadians(ent.getAngle()))), (sLoc[1])+(50*Math.cos(Math.toRadians(ent.getAngle()))), 5, 5);
                g2d.fill(circle);
                
            }
            if(eObj instanceof Food){
                g2d.setColor(Color.BLUE);
                sSize[0] = 10; sSize[1] = 10;
                g2d.drawString(eObj.getName(), (float) sLoc[0]+10, (float) sLoc[1]-10);
                Ellipse2D.Double circle = new Ellipse2D.Double(sLoc[0]-(sSize[0]/2), sLoc[1]-(sSize[0]/2), sSize[0], sSize[1]);
                g2d.fill(circle);
                g2d.setColor(Color.DARK_GRAY);
            }

            
          //  System.out.println(Arrays.toString(sLoc)+" : "+eObj);
        }
       
    }

    public void setMoveController(MoveController mc){
        this.mc = mc;
    }
    @Override
    public void run(){
        int fps = 60;
        
        while(true){
            try {
                sleep(1000/fps);
            } catch (InterruptedException ex) {
                Logger.getLogger(WorldPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            int[] vec = mc.getVector();
            vec[0] = vec[0]*this.movePower;
            vec[1] = vec[1]*this.movePower;
            vec[2] = vec[2]*this.zoomPower;
            moveViewport(vec);
        }
    }
    public void moveViewport(int[] vec){
        int[] loc = vLoc;
        loc[0] += vec[0];
        loc[1] += vec[1];
        
        if(vec[2]<0){
            if(vSize[0]+vec[2]<0){
                vec[2] = -vSize[0]+2;
                
                if(vSize[1]<vSize[0])
                    vec[2] = -vSize[1]+2;
                
            }
            
            
        }
        
        vSize[0]+=vec[2];
        vSize[1]+=vec[2];
        loc[0] -= vec[2]/2;
        loc[1] -= vec[2]/2;
        
        
        
        setViewport(loc);
    }
    public void setViewport(int[] loc){
        
        vLoc = loc;
        int[] p1 = loc;
        int[] p2 = new int[2];
        p2[0] = p1[0]+vSize[0];
        p2[1] = p1[1]+vSize[1];
        
        eObjects = w.getObjectsInArea(p1, p2);
        
        
        repaint();
      
        
    }

    @Override
    public void componentResized(ComponentEvent e) {
        HEIGHT = e.getComponent().getHeight();
        WIDTH = e.getComponent().getWidth();
        vSize[0] = WIDTH;
        vSize[1] = HEIGHT;
    }

    @Override
    public void componentMoved(ComponentEvent e) {
          }

    @Override
    public void componentShown(ComponentEvent e) {
           }

    @Override
    public void componentHidden(ComponentEvent e) {
           }
    
    
}

