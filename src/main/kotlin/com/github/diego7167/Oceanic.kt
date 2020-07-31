package com.github.diego7167

import com.github.diego7167.blocks.PureCrystalBlock
import com.github.diego7167.blocks.PurePrismarineLantern
import com.github.diego7167.material.PrismaticTool
import com.github.diego7167.world.OceanBedOreGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Block
import net.minecraft.block.FallingBlock
import net.minecraft.block.Material
import net.minecraft.item.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.DefaultFeatureConfig

@Suppress("unused")
class Oceanic: ModInitializer {
	// Ores should be public and companions
	companion object Companion {
		// Item group
		val oceanicItemGroup: ItemGroup = FabricItemGroupBuilder.build(
			Identifier("oceanic", "general"),
			fun(): ItemStack = ItemStack(pureCrystal)
		)

		// Items
		val purePrismarine = Item(Item.Settings().group(oceanicItemGroup))
		val pureCrystal = Item(Item.Settings().group(oceanicItemGroup))
		val pureShard = Item(Item.Settings().group(oceanicItemGroup))
		// Tools
		val prismaticSword = SwordItem(PrismaticTool.PRISMATIC, 3, -1.8f, Item.Settings().group(oceanicItemGroup))

		// Block
		val purePrismarineBlock = Block(FabricBlockSettings.of(Material.STONE).hardness(5.0f))
		val pureCrystalBlock = PureCrystalBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0f))
		val purePrismarineLantern = PurePrismarineLantern(FabricBlockSettings.of(Material.GLASS)
			.hardness(0.5f)
			.lightLevel(15)
			.sounds(BlockSoundGroup.GLASS)
		)
		val shinyGravel = FallingBlock(FabricBlockSettings.of(Material.SOIL).hardness(0.6f).sounds(BlockSoundGroup.SAND))
	}

	// Init
	override fun onInitialize() {
		println("Wield the wonders of the sea")

		// Register Items
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine"), purePrismarine)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal"), pureCrystal)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_shard"), pureShard)
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_sword"), prismaticSword)

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

		// World gen
		// Features
		val oceanBedOreGen = Registry.register(
			Registry.FEATURE,
			Identifier("oceanic", "ocean_bed_ore_gen"),
			OceanBedOreGen(DefaultFeatureConfig.CODEC)
		)

		// Biomes
		Registry.BIOME.forEach {
			if (it.toString().contains(Regex("Deep.*Ocean"))) {
				it.addFeature(
					GenerationStep.Feature.RAW_GENERATION,
					oceanBedOreGen.configure(DefaultFeatureConfig())
						.createDecoratedFeature(
							Decorator.CHANCE_HEIGHTMAP.configure(ChanceDecoratorConfig(2))
						)
				)
			}
		}
	}
}