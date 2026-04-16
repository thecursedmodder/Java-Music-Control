package net.cursedmodder.javatriggers.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class ClientContext {
    private static final Minecraft mc = Minecraft.getInstance();
    /*General context that can be used in your triggers or other in other parts of your mod.
      Additionally, you won't have to worry about null values crashing the game. That's the point of the context.
     */
    public static boolean isBellowHealth(float health) {
        Player player = mc.player;
        return player != null && player.getHealth() < health;
    }

    public static boolean canSeeSky() {
        Player player = mc.player;
        return player != null && player.level().canSeeSky(player.getOnPos());
    }

    public static boolean isNight() {
        Level level = mc.level;
        if (level != null) {
           return getTimeInDay(level) >= 13000 && getTimeInDay(level) <= 23000;
        }
        return false;
    }

    public static int getTimeInDay(Level level){
        //System.out.println((int) (level.getDayTime() % 24000));
        return (int) (level.getDayTime() % 24000);
    }

    public static boolean isPassenger() {
        Player player = mc.player;
        if(player == null) return false;
        return player.isPassenger();
    }

    public static boolean isRaining() {
        Level level =  mc.level;
        if(level == null) return false;
        return level.isRaining();
    }

    public static boolean isInMenu() {
        Level level =  mc.level;
       return level == null;
    }

    public static boolean isDeadOrDying() {
        Player player = mc.player;
        if(player == null) return false;
        return player.isDeadOrDying();
    }

    public static List<Mob> getMobsInAreaRatio(float radius, float RatioY) {
        return getMobsInArea(radius, (int) (radius * RatioY));
    }

    public static List<Mob> getMobsInArea(float radius, float DistanceY) {
        Player player = mc.player;
        if(player == null) return new ArrayList<>();
        Level level = player.level();
        double diameter = radius * 2;
        AABB searchBox = new AABB(
                player.getX() - radius, player.getY() - DistanceY, player.getZ() - radius,  // min
                player.getX() + radius, player.getY() + DistanceY, player.getZ() + radius   // max
        );
        List<Entity> mobs = level.getEntities(player, searchBox);

        mobs.removeIf((mob) -> {
            double dx = mob.getX() - player.getX();
            double dz = mob.getZ() - player.getZ();
            return (dx * dx + dz * dz) > (radius * radius);
        });
        List<Mob> mobsFinal = new ArrayList<>();
        mobs.forEach((mob) -> {
            if(mob instanceof Mob mob1) mobsFinal.add(mob1);
        });
        return mobsFinal;
    }

    public static boolean isMobTargetingPlayer(Mob mob) {
        Player player = mc.player;
        if(player == null) return false;
        return mob.getTarget() == player;
    }

    public static boolean isMobTargetingAPlayer(Mob mob) {
        return mob.getTarget() instanceof Player;
    }

    public static boolean isPlayerInBiome(String biomeId) {
        Level level =  mc.level;
        Player player = mc.player;
        if (level != null && player != null) {
            Holder<Biome> biome = level.getBiome(player.blockPosition());
            ResourceLocation biomeName = level.registryAccess()
                    .registryOrThrow(Registries.BIOME)
                    .getKey(biome.value());
            if(biomeName == null) return false;
            return biomeName.toString().equals(biomeId);
        }
        return false;
    }

}
