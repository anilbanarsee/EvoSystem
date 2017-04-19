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

import graphics.MoveController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 *
 * @author Reetoo
 */
public class EntityController implements KeyListener{
    
    int leftK,upK,rightK,downK,eatK, dropK;
    boolean left,up,right,down,eat, drop;
    
    public EntityController(char eat, char drop){

        leftK = 37;
        upK = 38;
        rightK = 39;
        downK = 40;
        eatK = KeyEvent.getExtendedKeyCodeForChar(eat);
        dropK = KeyEvent.getExtendedKeyCodeForChar(drop);
        

        
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.eat = false;
        this.drop = false;
        
    }
    public EntityController(char left, char up, char right, char down, char eat, char drop){
        leftK = KeyEvent.getExtendedKeyCodeForChar(left);
        upK = KeyEvent.getExtendedKeyCodeForChar(up);
        rightK = KeyEvent.getExtendedKeyCodeForChar(right);
        downK = KeyEvent.getExtendedKeyCodeForChar(down);
        eatK = KeyEvent.getExtendedKeyCodeForChar(eat);
        dropK = KeyEvent.getExtendedKeyCodeForChar(drop);
        
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.eat = false;
        this.drop = false;
        
    }
         
    
    
    @Override
    public void keyPressed(KeyEvent e) {       
        
        if(e.getKeyCode()==leftK){
            left = true;
        }
        else if(e.getKeyCode()==rightK){
            right = true;
        }
        else if(e.getKeyCode()==upK){
            up = true;
        }
        else if(e.getKeyCode()==downK){
            down = true;
        }
        else if(e.getKeyCode()==eatK){
            eat = true;
        }
        else if(e.getKeyCode()==dropK){
            drop = true;
        }
        
            
    }
    public int[] getVector(){
        int[] vec = new int[3];
        
        if(left){
            vec[0]--;
        }
        if(right){
            vec[0]++;
        }
        if(up){
            vec[1]--;
        }
        if(down){
            vec[1]++;
        }
        if(eat){
            vec[2]++;
        }
        if(drop){
            vec[2]--;
        }
        
        return vec;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

   @Override
    public void keyReleased(KeyEvent e) {       
        
        if(e.getKeyCode()==leftK){
            left = false;
        }
        else if(e.getKeyCode()==rightK){
            right = false;
        }
        else if(e.getKeyCode()==upK){
            up = false;
        }
        else if(e.getKeyCode()==downK){
            down = false;
        }
        else if(e.getKeyCode()==eatK){
            eat = false;
        }
        else if(e.getKeyCode()==dropK){
            drop = false;
        }
        
            
    }
    

   
    
    
}
