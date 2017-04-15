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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.encog.ml.ea.train.EvolutionaryAlgorithm;
import org.encog.neural.hyperneat.substrate.Substrate;
import org.encog.neural.hyperneat.substrate.SubstrateFactory;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.NEATUtil;
import org.encog.neural.neat.training.species.OriginalNEATSpeciation;

/**
 *
 * @author Reetoo
 */
public class TrainTest {
    
    public static void main(String[] args){
        
        
        /*JFrame frame = new JFrame("Training Information");
        frame.setSize(300,300);
        frame.setVisible(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        JLabel iterationLabel = new JLabel();
        JLabel numSpeciesLabel = new JLabel();
        JLabel bestScoreLabel = new JLabel();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(iterationLabel,c);
        
        c.gridx = 0;
        c.gridy = 1;
        panel.add(numSpeciesLabel,c);
        
        c.gridy = 2;
        panel.add(bestScoreLabel,c);
        
        JButton startSimButton = new JButton("Start Simulation");
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 3;
        
        startSimButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                
            } 
        });
        
        panel.add(startSimButton,c);
        
        frame.add(panel);
        */
        
        Substrate substrate = SubstrateFactory.factorSandwichSubstrate(16, 4);
        System.out.println(substrate.getInputCount());
        System.out.println(substrate.getOutputCount());
        EntityScore score = new EntityScore();
        
        NEATPopulation pop = new NEATPopulation(substrate, 500);
        pop.setActivationCycles(4);
        pop.reset();
        EvolutionaryAlgorithm train = NEATUtil.constructNEATTrainer(pop, score);
        OriginalNEATSpeciation speciation = new OriginalNEATSpeciation();
        speciation.setCompatibilityThreshold(1);
        train.setSpeciation(speciation = new OriginalNEATSpeciation());
        
        
        while(true){
            
            train.iteration();
            
            System.out.println(train.getIteration());
           
            if((train.getIteration()%10)==0){
                
                System.out.println("ITERATION :"+train.getIteration());
                System.out.println("NUM SPECIES:"+pop.getSpecies().size());
                System.out.println("BEST SCORE:"+pop.getBestGenome().getScore());
                
        /*iterationLabel.setText("ITERATION :"+train.getIteration());
        numSpeciesLabel.setText("NUM SPECIES:"+pop.getSpecies().size());
        bestScoreLabel.setText("BEST SCORE:"+pop.getBestGenome().getScore());*/
       
                
            }
            
        }
        
        
        
        
    
    }
    
}
