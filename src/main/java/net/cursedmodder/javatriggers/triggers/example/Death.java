package net.cursedmodder.javatriggers.triggers.example;

import net.cursedmodder.javatriggers.util.ClientContext;
import net.cursedmodder.javatriggers.triggers.base.TriggerBase;
import net.cursedmodder.javatriggers.triggers.songs.Song;
import net.cursedmodder.javatriggers.util.debug.watch.DebugUI;

public class Death extends TriggerBase {
    public Death() {
        super(4, 0, 100, true, 1F, 0.4F, TriggerSongs.deathSongs().toArray(new Song[0]));
        DebugUI.watch(this.getClass(), "state", this::triggerState);
        this.canForceInterrupted = true;
    }

    @Override
    public boolean canPlay() {
        return ClientContext.isDeadOrDying() && super.canPlay();
    }
}
