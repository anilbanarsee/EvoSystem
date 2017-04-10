/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.util.ArrayList;

/**
 *
 * @author General
 * This class represents the shape an entity can take and deals with collision detection along with area calculation.
 */
public class Shape {
    
    private ArrayList<Integer[]> points;
    private int[] location;
    public Shape(){
        points = new ArrayList<>();
    }
    
}
