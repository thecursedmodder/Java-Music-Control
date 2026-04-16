package net.cursedmodder.javatriggers.triggers.songs.layer;

import net.cursedmodder.javatriggers.triggers.songs.Song;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class LayeredSong extends Song {
    private final List<LayerCondition> layers = new ArrayList<>();

    public LayeredSong(String SongName, LayerCondition... layers) {
        super(SongName, true);
        this.layers.addAll(List.of(layers));
    }

    public List<LayerCondition> getLayers() {
        return layers;
    }

}
