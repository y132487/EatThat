package com.example.kong;

import com.example.framework.FrameGame;
import com.example.module.Screen;

public class KongGame extends FrameGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}