package net.cursedmodder.javatriggers.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneralConversionUtil {
    //This will grow as more features are added
    public static String getEntityStringId(Entity entity) {
        if (entity == null) return "";

        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        return key != null ? key.toString() : "";
    }

}
