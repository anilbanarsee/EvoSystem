/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import eobject.Entity;
import framework.Processor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author General
 */
public class MainFrame extends JFrame implements MouseListener{
    
    WorldPanel wpanel;
    BrainDisplayPanel brainDisplay;
    EntityStatPanel entityDisplay;
    WorldControlPanel wcp;
    Processor proc;
    
    public MainFrame(WorldPanel wpanel, Processor p){
        
        proc = p;

        this.wpanel = wpanel;
        
        initFrame();

    }
    public void focusWorldPanel(){
        wpanel.requestFocusInWindow();
    }
    public final void initFrame(){
        MigLayout layout = new MigLayout();
        this.setSize(1000,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        this.setLayout(layout);
        
        MoveController mc = new MoveController('a','w','d','s');
        wpanel.setMoveController(mc);

        brainDisplay =  new BrainDisplayPanel();
        entityDisplay = new EntityStatPanel();
        wcp = new WorldControlPanel(proc,this);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(
            BorderFactory.createTitledBorder("WORLD CONTROL"));
        
        this.add(wcp, "span 1 2,grow,width 20%");
        this.add(wpanel,"span 2 2,grow,push");
        
        this.add(brainDisplay,"wrap, width 20%, height 65%");
        this.add(entityDisplay,"height 35%, width 20%");
        
        
        wpanel.setBorder( BorderFactory.createTitledBorder("WORLD"));
        
        
       
        
        
        
        
        

        this.addMouseListener(this);
        this.addKeyListener(mc);
    }
    public void refresh(){
        entityDisplay.refreshPanel();
        validate();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX()-wpanel.getX()-10;
        int y = e.getY()-wpanel.getY()-30;

        if(x<0||y<0||x>wpanel.getWidth()||y>wpanel.getHeight())
            return;
        else
            requestFocusInWindow();
        
        
        Entity ent = wpanel.getEntity(x,y);
        
        if(ent!=null){
            entityDisplay.setEntity(ent);
            if(ent.getBrain()!=null){
                brainDisplay.setBrain(ent.getBrain());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
}
