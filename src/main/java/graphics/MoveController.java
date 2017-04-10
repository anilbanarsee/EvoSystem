/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author General
 */
public class MoveController implements KeyListener{

    boolean up, down, left, right, zup, zdown;
    int kcode;
    
    public MoveController(){

        
        up = false;
        down = false;
        left = false;
        right =  false;
        
    }
    
    
    public int[] getVector(){
        int[] vec = new int[3];
        if(left)
            vec[0]--;
        if(right)
            vec[0]++;
        if(up)
            vec[1]--;
        if(down)
            vec[1]++;
        if(zup)
            vec[2]++;
        if(zdown)
            vec[2]--;
        return vec;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
      
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int k = ke.getKeyCode();
    
        if(k==37||k==65){
            left = true;
            kcode = k;
        }
        else if(k==38||k==87){
            up = true;
            kcode = k;
        }
        else if(k==39||k==68){
            right = true;
            kcode = k;
        }
        else if(k==40||k==83){
            down = true;
            kcode = k;
        }
        else if(k==73){
            zdown=true;
            kcode = k;
        }
        else if(k==79){
            
            zup = true;
            kcode = k;
        }
      
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int k = ke.getKeyCode();
    
        if(k==37||k==65){
            left = false;
            kcode = k;
        }
        else if(k==38||k==87){
            up = false;
            kcode = k;
        }
        else if(k==39||k==68){
            right = false;
            kcode = k;
        }
        else if(k==40||k==83){
            down = false;
            kcode = k;
        }
        else if(k==73){
            zdown=false;
            kcode = k;
        }
        else if(k==79){
            
            zup = false;
            kcode = k;
        }
    }
    
}
