package net.cursedmodder.javatriggers.triggers.songs;

import net.cursedmodder.javatriggers.triggers.base.TriggerBase;

import java.util.List;

public class SongList {
    private final List<Song> Songs;
    private final TriggerBase LinkedTrigger;

    public SongList(List<Song> songs, TriggerBase trigger) {
        Songs = songs;
        LinkedTrigger = trigger;
    }

    public boolean isSongListForTrigger(TriggerBase trigger) {
        return LinkedTrigger == trigger;
    }

    public TriggerBase getLinkedTrigger() {
        return LinkedTrigger;
    }

    public List<Song> getSongs() {
        return Songs;
    }

}
