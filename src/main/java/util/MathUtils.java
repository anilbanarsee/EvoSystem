/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author General
 */
public class MathUtils {
    
    public static void main(String[] args){
        System.out.println(between(5,10,2));
        System.out.println(between(5,2,10));
        System.out.println(between(2,3,10));
        System.out.println(between(2,10,3));
    }
    public static double[] getEndPointOfVector(double[] p, double angle, double length){
        
        double[] vec = new double[2];
        
        double dx = length*Math.sin(angle);
        double dy = length*Math.cos(angle);
        
        vec[0] += dx;
        vec[1] += dy;
        
        return vec;
    }
    public static double getAngleToNormal(double[] p1, double[] p2){
        
        double vecLength = getDistanceBetween(p1,p2);
        
        return Math.toDegrees(Math.acos(p2[1]/vecLength));
        
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
