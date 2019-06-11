package com.example.kong;

import java.util.ArrayList;
import java.util.List;

public class Head {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    
    public List<Bean> parts = new ArrayList<Bean>();
    public int direction;    
    
    public Head() {        
        direction = UP;
        parts.add(new Bean(5, 6));
        parts.add(new Bean(5, 7));
        parts.add(new Bean(5, 8));
    }
    
    public void turnLeft() {
        direction += 1;
        if(direction > RIGHT)
            direction = UP;
    }
    
    public void turnRight() {
        direction -= 1;
        if(direction < UP)
            direction = RIGHT;
    }
    
    public void eat() {
        Bean end = parts.get(parts.size()-1); 
        parts.add(new Bean(end.x, end.y));
    }
    
    public void advance() {
        Bean head = parts.get(0);               
        
        int len = parts.size() - 1;
        for(int i = len; i > 0; i--) {
            Bean before = parts.get(i-1);
            Bean part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(direction == UP)
            head.y -= 1;
        if(direction == LEFT)
            head.x -= 1;
        if(direction == DOWN)
            head.y += 1;
        if(direction == RIGHT)
            head.x += 1;
        
        if(head.x < 0)
            head.x = 9;
        if(head.x > 9)
            head.x = 0;
        if(head.y < 0)
            head.y = 12;
        if(head.y > 12)
            head.y = 0;
    }
    
    public boolean checkBitten() {
        int len = parts.size();
        Bean head = parts.get(0);
        for(int i = 1; i < len; i++) {
            Bean part = parts.get(i);
            if(part.x == head.x && part.y == head.y)
                return true;
        }        
        return false;
    }      
}
