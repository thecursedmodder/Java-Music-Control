package net.cursedmodder.javatriggers.triggers;

import net.cursedmodder.javatriggers.triggers.example.extendable.MobTrigger;
import net.cursedmodder.javatriggers.triggers.songs.Song;
import net.cursedmodder.javatriggers.triggers.example.TriggerSongs;

public class MobTriggerZombie extends MobTrigger {

    public MobTriggerZombie() {
        super(5,0, 0, true, 1F, 0.3F, TriggerSongs.combatSongs().toArray(new Song[0]), "minecraft:zombie");
    }


}
