package net.cursedmodder.javatriggers.triggers.songs;

import net.cursedmodder.javatriggers.triggers.base.TriggerBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class Song {
    private final String songName;
    public int playOnce;
    public volatile boolean hasPlayed;
    public int startTime;
    private TriggerBase trigger;
    protected final boolean layeredSong;
    protected float volume = 1;
    protected int fadeIn;
    protected int fadeOut;
    protected boolean mustFinish = false;
    public int weight;
    public boolean canSongPlay = true;


    public Song(String SongName) {
        songName = SongName;
        this.layeredSong = false;
    }

    protected Song(String SongName, boolean layeredSong) {
        songName = SongName;
        this.layeredSong = true;
    }

    public String getSongName() {
        return songName;
    }

    public float getVolume() {
        return volume;
    }

    public void resetHasPlayed() {
        if(playOnce > 1) return;
        hasPlayed = false;
    }

    public Song setStats(int fadeIn, int fadeOut, float volume) {
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.volume = volume;
        return this;
    }
    public Song setStats(int startTime, int fadeIn, int fadeOut, float volume) {
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.volume = volume;
        this.startTime = startTime;
        return this;
    }

    public Song AttachTrigger(TriggerBase trigger) {
        this.trigger = trigger;
        return this;
    }

    public TriggerBase getAttachedTrigger() {
        return  trigger;
    }

    public Song setPlayConditions(boolean mustFinish, int playOnce) {
        this.mustFinish = mustFinish;
        this.playOnce = playOnce;
        return this;
    }

    public int getFadeIn() { return fadeIn; }

    public int getFadeOut() { return fadeOut; }

    public boolean isLayeredSong() { return layeredSong; }

    public boolean mustFinish() { return mustFinish; }
}
