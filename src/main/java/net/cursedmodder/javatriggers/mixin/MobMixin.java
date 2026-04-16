package net.cursedmodder.javatriggers.mixin;

import net.cursedmodder.javatriggers.network.TriggerMessages;
import net.cursedmodder.javatriggers.network.packets.MobTargetingReturn;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
    private Mob mob = (Mob) (Object) this;
    @Inject(method = "setTarget", at = @At("TAIL"))
    public void setTarget(LivingEntity livingEntity, CallbackInfo ci) {
        if(mob.level().isClientSide) return;
        ServerLevel level = (ServerLevel) mob.level();
        if(livingEntity != null) {
            TriggerMessages.sendToAll(new MobTargetingReturn(mob.getId(), livingEntity.getId()), level);
        } else TriggerMessages.sendToAll(new MobTargetingReturn(mob.getId(), -1), level);
    }
}
