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
package eobject;

/**
 *
 * @author Reetoo
 */
public class Food extends EvoObject{

    boolean inVision;
    boolean isPoison;
    int foodValue;
    
    public Food(int[] loc, float radius, boolean isPoison, int foodValue) {
        super(loc, radius);
        this.isPoison = isPoison;
        this.foodValue = foodValue;
    }
    public int getValue(){
        return foodValue;
    }
    public void setInVision(boolean set){
        inVision = set;
    }
    public boolean isInVision(){
        return inVision;
    }
    public boolean isPoison(){
        return isPoison;
    }
    public String toString(){
        return name;
    }
    
    
}
