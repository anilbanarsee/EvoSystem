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
        
        Substrate substrate = SubstrateFactory.factorSandwichSubstrate(4, 4);
        EntityScore score = new EntityScore();
        
        NEATPopulation pop = new NEATPopulation(substrate, 500);
        pop.setActivationCycles(4);
        pop.reset();
        EvolutionaryAlgorithm train = NEATUtil.constructNEATTrainer(pop, score);
        OriginalNEATSpeciation speciation = new OriginalNEATSpeciation();
        speciation.setCompatibilityThreshold(1);
        train.setSpeciation(speciation = new OriginalNEATSpeciation());
        
    
    }
    
}
