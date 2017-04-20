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

import framework.Processor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Reetoo
 */
public class WorldControlPanel extends JPanel{
    

    JButton setSpeedButton;
    JTextField speedField;
    Processor proc;
    MainFrame mframe;
    
    public WorldControlPanel(Processor p, MainFrame mf){
        proc = p;
        mframe = mf;
        initPanel();
        
    }
    
    public final void initPanel(){
        this.setLayout(new MigLayout());
        
        setSpeedButton = new JButton("SET");
        setSpeedButton.addActionListener(new ActionListener(){
          
            @Override
            public void actionPerformed(ActionEvent e) {
                
                double d;
                try{
                    d = Double.parseDouble(speedField.getText());
                }
                catch(NumberFormatException ex){
                    mframe.requestFocusInWindow();
                    return;
                }
                System.out.println(d);
                proc.timeFactor = d;
                mframe.requestFocusInWindow();
                
            }
        });
        setSpeedButton.setFocusable(false);
        speedField = new JTextField();
        
        this.add(new JLabel("SPEED"));
        this.add(speedField,"pushx,grow 200");
        this.add(setSpeedButton,"wrap");
        this.add(new JLabel("GENERATION"));
        this.add(new JTextField(),"pushx,grow 200");
        this.add(new JButton("SET"),"wrap");
        this.add(new JLabel("AUTO-GEN"));
        this.add(new JTextField(),"pushx,grow 200");
        this.add(new JButton("SET"),"wrap");
    }
    public static void main(String[] args){
        WorldControlPanel bdp = new WorldControlPanel(null,null);
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.add(bdp);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
