package com.github.diego7167.oceanic.mixin;

import com.github.diego7167.oceanic.Oceanic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(EnchantmentHelper.class)
public class TideRingMixin {
    @Inject(method = "hasAquaAffinity", at = @At("HEAD"), cancellable = true)
    private static void hasAquaAffinity(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = CuriosApi.getCuriosHelper()
            .findEquippedCurio(Oceanic.Companion.getTideRing(), entity).map(ImmutableTriple::getRight) // Not really sure what this means
            .orElse(ItemStack.EMPTY);
        if(!stack.isEmpty()) {
            cir.setReturnValue(!stack.isEmpty());
        }
    }
}
