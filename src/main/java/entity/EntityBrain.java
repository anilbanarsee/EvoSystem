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
package entity;

/**
 *
 * @author Reetoo
 */
public class EntityBrain {
    
    Entity entity;
    
    public EntityBrain(Entity e){
        entity = e;
    }
    
    public int[] getDecision(){
        int[] vector = new int[2];
        return null;
    }
    
    
}
