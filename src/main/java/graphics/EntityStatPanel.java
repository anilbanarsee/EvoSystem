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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Reetoo
 */
public class EntityStatPanel extends JPanel {
    
    JLabel scoreHeadLabel, hungerHeadLabel, holdingHeadLabel, speciesHeadLabel;
    JLabel scoreValueLabel, hungerValueLabel, holdingValueLabel, speciesValueLabel;
    JLabel nothing;
    Entity entity;
    
    public EntityStatPanel(){
        initPanel();

        entity = null;
        refreshPanel();
    }
    public void setEntity(Entity entity){
        this.entity = entity;
        refreshPanel();
    }
    public final void initPanel(){
        MigLayout layout = new MigLayout("insets 5 5 5 5");
        
        JLabel title = new JLabel("STATS");
        this.scoreHeadLabel = new JLabel("Score :");
        this.hungerHeadLabel = new JLabel("Hunger :");
        this.holdingHeadLabel = new JLabel("Holding :");
        this.speciesHeadLabel = new JLabel("Species No.:");

        this.scoreValueLabel = new JLabel("50");
        this.hungerValueLabel = new JLabel("50");
        this.holdingValueLabel = new JLabel("50");
        this.speciesValueLabel = new JLabel("50");
       
        nothing = new JLabel("[NO ENTITY SELECTED]");
        this.setLayout(layout);
        //this.add(title, "wrap 25px");
        this.setBorder(
            BorderFactory.createTitledBorder("STATS"));

        
     
    }
    public void refreshPanel(){
        
        this.removeAll();
        if(this.entity!=null){
            
            this.scoreValueLabel.setText(entity.getScore()+"");
            this.hungerValueLabel.setText(entity.getHunger()+"");
            if(entity.isHoldingFood()){
                if(entity.holding.isPoison()){
                    this.holdingValueLabel.setText("Poison");
                }
                else{
                    this.holdingValueLabel.setText("Food"); 
                }
            }
            else{
                this.holdingValueLabel.setText("Nothing"); 
            }
            this.add(scoreHeadLabel, "pushx");
            this.add(scoreValueLabel, "wrap 15px");
            this.add(hungerHeadLabel, "");
            this.add(hungerValueLabel, "wrap 15px");
            this.add(holdingHeadLabel, "");
            this.add(holdingValueLabel, "wrap 15px");
            this.add(speciesHeadLabel,"");
            this.add(speciesValueLabel, "gapright 30");
            
        }
        else{
             this.add(nothing, "span,pushx,pushy,align center");
        }
        repaint();
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.add(new EntityStatPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
