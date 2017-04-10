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
    public static boolean between(int t, int x, int y){
        if(x<y){
            return (t<y && t>x);
        }
        else{
            return (t>y && t<x);
        }
    }
    
}
