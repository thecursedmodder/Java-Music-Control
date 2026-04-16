package net.cursedmodder.javatriggers.audio.decoder;

import ddf.minim.UGen;

import static java.lang.Math.abs;

public class SmoothGlide extends UGen {
    private float currentValue;
    private float targetValue;
    private float step;
    private float glideTime; // seconds
    private float sampleRate;

    public SmoothGlide(float startValue, float glideTimeSeconds) {
        this.currentValue = startValue;
        this.targetValue = startValue;
        this.glideTime = glideTimeSeconds;
    }

    public void setValue(int glideTime, float volume) {
        setValue(volume);
        setGlideTime(glideTime);
    }

    public void setValue(float value) {
        this.targetValue = value;

        float samples = glideTime * sampleRate;
        if (samples <= 0) {
            currentValue = targetValue;
            step = 0;
        } else {
            step = (targetValue - currentValue) / samples;
        }
    }

    public void setGlideTime(float seconds) {
        this.glideTime = seconds;
    }

    @Override
    protected void sampleRateChanged() {
        sampleRate = sampleRate();
    }

    @Override
    protected void uGenerate(float[] channels) {
        // move toward target
        if (Math.abs(targetValue - currentValue) > Math.abs(step)) {
            currentValue += step;
        } else {
            currentValue = targetValue;
        }

        // output value to all channels
        for (int i = 0; i < channels.length; i++) {
            channels[i] = currentValue;
        }
    }
    // Get current value (useful for debugging or display)
    public float getValue() {
        return currentValue;
    }

    public void setImmediate(float volume) {
    }

    public float getTarget() {
        return targetValue;
    }

    public boolean fading() {
        return currentValue != targetValue;
    }

    public boolean fadingIn() {
        return this.targetValue > currentValue;
    }

    public boolean fadingOut() {
        return this.targetValue < currentValue;
    }

    public void silentSet(float volume) {
        this.targetValue = Math.max(0.0f, Math.min(1.0f, volume));
    }
}