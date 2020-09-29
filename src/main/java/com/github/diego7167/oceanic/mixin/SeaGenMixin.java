package com.github.diego7167.oceanic.mixin;

import com.github.diego7167.oceanic.Oceanic;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class SeaGenMixin {
    @Inject(method = "addOceanStructures(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addOceanStructures(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, Oceanic.Companion.getDeepstoneGenConf());
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, Oceanic.Companion.getOceanBedOreGenConf());
    }
}