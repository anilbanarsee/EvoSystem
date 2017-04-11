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
    
    int leftKey, upKey, rightKey, downKey, zInKey, zOutKey;
    
    int kcode;
    
    public MoveController(){
        
        up = false;
        down = false;
        left = false;
        right =  false;
        
        leftKey = 37;
        upKey = 38;
        rightKey = 39;
        downKey = 40;
        
        zInKey = 73;
        zOutKey = 79;
        
    }
    public MoveController(char lC, char uC, char rC, char dC){
        
        this();
        leftKey = KeyEvent.getExtendedKeyCodeForChar(lC);
        rightKey = KeyEvent.getExtendedKeyCodeForChar(rC);
        upKey = KeyEvent.getExtendedKeyCodeForChar(uC);
        downKey = KeyEvent.getExtendedKeyCodeForChar(dC);
        
    }
    
    public MoveController(char lC, char uC, char rC, char dC, char zIC, char zOC){
        
        this(lC, uC, rC, dC);
        zInKey = KeyEvent.getExtendedKeyCodeForChar(zIC);
        zOutKey = KeyEvent.getExtendedKeyCodeForChar(zOC);
        
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
    
        if(k == leftKey){
            left = true;
            kcode = k;
        }
        else if(k == upKey){
            up = true;
            kcode = k;
        }
        else if(k == rightKey){
            right = true;
            kcode = k;
        }
        else if(k == downKey){
            down = true;
            kcode = k;
        }
        else if(k == zInKey){
            zdown=true;
            kcode = k;
        }
        else if(k == zOutKey){
            
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
