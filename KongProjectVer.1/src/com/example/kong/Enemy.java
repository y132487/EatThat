package com.example.kong;

public class Enemy {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    public int x, y;
    public int type;

    public Enemy(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
