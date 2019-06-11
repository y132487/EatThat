package com.example.framework;

import android.media.SoundPool;

import com.example.module.Sound;

public class FrameSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public FrameSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
