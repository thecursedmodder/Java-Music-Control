package net.cursedmodder.javatriggers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue TPS;
    //public static final ForgeConfigSpec.BooleanValue DEBUG;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("General");
        TPS = builder
                .comment("Tick Delay for trigger switching")
                .comment("Its probably a good idea to turn this up for more relaxed mod-packs")
                .comment("The higher the delay the slower the music will respond to actions")
                .comment("ticks per second = 20/TPS")
                .defineInRange("tps", 1, 1, 10);
        builder.pop();

        SPEC = builder.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SPEC, "JavaTriggers-common.toml");
    }
}
