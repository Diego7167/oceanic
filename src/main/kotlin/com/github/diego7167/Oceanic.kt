package com.github.diego7167

import com.github.diego7167.blocks.PureCrystalBlock
import com.github.diego7167.blocks.PurePrismarineLantern
import com.github.diego7167.blocks.ShinyGravel
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

@Suppress("unused")
class Oceanic: ModInitializer {
	// Item group
	private val oceanicItemGroup: ItemGroup = FabricItemGroupBuilder.build(
		Identifier("oceanic", "general"),
		fun(): ItemStack = ItemStack(pureCrystal)
	)

	// Items
	private val purePrismarine = Item(Item.Settings().group(oceanicItemGroup))
	private val pureCrystal = Item(Item.Settings().group(oceanicItemGroup))
	private val pureShard = Item(Item.Settings().group(oceanicItemGroup))

	// Block
	private val purePrismarineBlock = Block(FabricBlockSettings.of(Material.STONE).hardness(5.0f))
	private val pureCrystalBlock = PureCrystalBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f))
	private val purePrismarineLantern = PurePrismarineLantern(FabricBlockSettings.of(Material.GLASS).hardness(0.5f).lightLevel(15).sounds(BlockSoundGroup.GLASS))
	// Ores should be public and companions
	companion object Ores {
		val shinyGravel = ShinyGravel(FabricBlockSettings.of(Material.SOIL).hardness(0.6f))
	}

	// Init
	override fun onInitialize() {
		println("Wield the wonders of the sea")

		// Register Items
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine"), purePrismarine)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal"), pureCrystal)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_shard"), pureShard)

		// Register Blocks
		Registry.register(Registry.BLOCK, Identifier("oceanic", "pure_prismarine_block"), purePrismarineBlock)
		Registry.register(Registry.BLOCK, Identifier("oceanic", "pure_crystal_block"), pureCrystalBlock)
		Registry.register(Registry.BLOCK, Identifier("oceanic", "pure_prismarine_lantern"), purePrismarineLantern)
		Registry.register(Registry.BLOCK, Identifier("oceanic", "shiny_gravel"), shinyGravel)
		// And ItemBlocks
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine_block"), BlockItem(purePrismarineBlock, Item.Settings().group(oceanicItemGroup)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal_block"), BlockItem(pureCrystalBlock, Item.Settings().group(oceanicItemGroup)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine_lantern"), BlockItem(purePrismarineLantern, Item.Settings().group(oceanicItemGroup)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "shiny_gravel"), BlockItem(shinyGravel, Item.Settings().group(oceanicItemGroup)))
	}
}