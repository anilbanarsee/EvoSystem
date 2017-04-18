/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import eobject.Food;
import java.util.Arrays;

/**
 *
 * @author General
 */
public class MathUtils {
    
    public static void main(String[] args){
        
        double[] array = {1,2,3,4,5};
        System.out.println(Arrays.toString(insertIntoFixedArray(array, 7, 3)));

    }
    public static boolean betweenAngles(double test, double a1, double a2){
        
        double x = (test+a1)%360;
        return x<=a2;
        
    }
    public static Food[] insertIntoFixedArray(Food[] target, Food value, int index){
        
        Food[] newArray = new Food[target.length];
        int nIndex = 0;
        
        for(int i=0; i<newArray.length; i++){
            
            if(i==index){
                newArray[i] = value;
            }
            else{
                newArray[i] = target[nIndex];
                nIndex++;
            }
        }
        return newArray;
    }
            public static boolean[] insertIntoFixedArray(boolean[] target, boolean value, int index){
        
        boolean[] newArray = new boolean[target.length];
        int nIndex = 0;
        
        for(int i=0; i<newArray.length; i++){
            
            if(i==index){
                newArray[i] = value;
            }
            else{
                newArray[i] = target[nIndex];
                nIndex++;
            }
        }
        return newArray;
    }
        public static double[] insertIntoFixedArray(double[] target, double value, int index){
        
        double[] newArray = new double[target.length];
        int nIndex = 0;
        
        for(int i=0; i<newArray.length; i++){
            
            if(i==index){
                newArray[i] = value;
            }
            else{
                newArray[i] = target[nIndex];
                nIndex++;
            }
        }
        return newArray;
    }
    public static double[] getEndPointOfVector(double[] p, double angle, double length){
        
        double[] vec = new double[2];
        
        double dx = length*Math.sin(Math.toRadians(angle));
        double dy = length*Math.cos(Math.toRadians(angle));
        
        vec[0] = dx+p[0];
        vec[1] = dy+p[1];
        
        return vec;
    }
    public static double getAngleToNormal(double[] p1, double[] p2){
        
        double vecLength = getDistanceBetween(p1,p2);
        double dot = p2[1]-p1[1];
        double det = p2[0]-p1[0];
        
        double ans = Math.toDegrees(Math.atan2(det, dot));

        return ans;
        
    }
    public static double getDistanceBetween(double[] p1, double[] p2){
        
        
        double a = p1[0]-p2[0];
        double b = p1[1]-p2[1];
        
        return Math.sqrt((a*a)+(b*b));
        
    }
    public static boolean between(int t, int x, int y){
        if(x<y){
            return (t<y && t>x);
        }
        else{
            return (t>y && t<x);
        }
    }
    public static boolean between(double t, int x, int y){
        if(x<y){
            return (t<y && t>x);
        }
        else{
            return (t>y && t<x);
        }
    }
        public static boolean between(double t, double x, double y){
        if(x<y){
            return (t<y && t>x);
        }
        else{
            return (t>y && t<x);
        }
    }
    
    
}
