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

import entity.Entity;
import environment.World;
import java.util.ArrayList;

/**
 *
 * @author Reetoo
 */
public class Simulate {
    
    public static void main(String[] args){
        EntityScore score = new EntityScore();
        
        World w = new World();
        int[] loc = {200,200};
        
        Entity e1 = new Entity(loc, 500);
        e1.setName("Alpha");
        w.addObject(e1);
        
        int numTicks = 10000;
        
        for(int i=0; i<numTicks; i++){
            
            w.tick();
            if(w.getObjects().size()<1){
                break;
            }
        
        }
        
        ArrayList<Entity> graveyard = w.getGraveyard();
        
        System.out.println("Results :");
        for(Entity e: graveyard){
            System.out.println("Entity :"+e.getName()+", Score :"+e.getScore());
        }
        System.out.println("=============End of results=============");
        
    }
    
}
