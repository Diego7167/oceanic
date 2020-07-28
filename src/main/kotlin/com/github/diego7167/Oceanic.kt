package com.github.diego7167

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

@Suppress("unused")
class Oceanic: ModInitializer {
    private val oceanicItemGroup: ItemGroup = FabricItemGroupBuilder.build(
            Identifier("oceanic", "general"),
            fun(): ItemStack = ItemStack(purePrismarine)
    )

    private val purePrismarine = Item(Item.Settings().group(oceanicItemGroup))

    override fun onInitialize() {
        println("Wield the wonders of the sea")

        // Register Items
        Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine"), purePrismarine)
    }
}