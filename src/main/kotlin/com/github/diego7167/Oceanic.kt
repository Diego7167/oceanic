package com.github.diego7167

import com.github.diego7167.blocks.PureCrystalBlock
import com.github.diego7167.blocks.PurePrismarineLantern
import com.github.diego7167.material.PrismaticTool
import com.github.diego7167.tools.PrismaticAxe
import com.github.diego7167.tools.PrismaticHoe
import com.github.diego7167.tools.PrismaticPickaxe
import com.github.diego7167.world.OceanBedOreGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Block
import net.minecraft.block.FallingBlock
import net.minecraft.block.Material
import net.minecraft.item.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import org.apache.logging.log4j.LogManager

@Suppress("unused")
class Oceanic: ModInitializer {
	private val logger = LogManager.getLogger()
	// Ores should be public and companions
	companion object Companion {
		// Item group
		val oceanicItemGroup: ItemGroup = FabricItemGroupBuilder.build(
			Identifier("oceanic", "general"),
			fun(): ItemStack = ItemStack(pureCrystal)
		)

		// Materials
		private val gravelLike: FabricBlockSettings = FabricBlockSettings.of(Material.SOIL)
			.hardness(.6f)
			.sounds(BlockSoundGroup.GRAVEL)
			.breakByTool(FabricToolTags.SHOVELS)
		private val stoneLike: FabricBlockSettings = FabricBlockSettings.of(Material.STONE)
			.hardness(.6f)
			.resistance(6f)
			.breakByTool(FabricToolTags.PICKAXES, 0)

		// Items
		val purePrismarine = Item(Item.Settings().group(oceanicItemGroup))
		val pureCrystal = Item(Item.Settings().group(oceanicItemGroup))
		val pureShard = Item(Item.Settings().group(oceanicItemGroup))
		// Tools
		val prismaticSword = SwordItem(PrismaticTool.PRISMATIC, 3, -2.2f, Item.Settings().group(oceanicItemGroup))
		val prismaticShovel = ShovelItem(PrismaticTool.PRISMATIC, 1.5f, -3.0f, Item.Settings().group(oceanicItemGroup))
		// Pickaxes, axes, and hoes are protected and need to be extended
		val prismaticPickaxe = PrismaticPickaxe(1, -2.8f, Item.Settings().group(oceanicItemGroup))
		val prismaticAxe = PrismaticAxe(7f, -3.2f, Item.Settings().group(oceanicItemGroup))
		val prismaticHoe = PrismaticHoe(7, -3.2f, Item.Settings().group(oceanicItemGroup))

		// Blocks
		val purePrismarineBlock = Block(stoneLike)
		val pureCrystalBlock = PureCrystalBlock(stoneLike.hardness(5f).breakByTool(FabricToolTags.PICKAXES, 2))
		val purePrismarineLantern = PurePrismarineLantern(FabricBlockSettings.of(Material.GLASS)
			.hardness(0.5f)
			.lightLevel(15)
			.sounds(BlockSoundGroup.GLASS)
			.breakByTool(FabricToolTags.PICKAXES)
		)
		val shinyGravel = FallingBlock(gravelLike)
	}

	// Init
	override fun onInitialize() {
		logger.info("Wield the wonders of the sea!")

		// Register Items
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine"), purePrismarine)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal"), pureCrystal)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_shard"), pureShard)
		// Tools
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_sword"), prismaticSword)
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_pickaxe"), prismaticPickaxe)
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_shovel"), prismaticShovel)
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_axe"), prismaticAxe)
		Registry.register(Registry.ITEM, Identifier("oceanic", "prismatic_hoe"), prismaticHoe)

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
		@Suppress("unused_variable")
		val oceanBedOreGen = Registry.register(
			Registry.FEATURE,
			Identifier("oceanic", "ocean_bed_ore_gen"),
			OceanBedOreGen(DefaultFeatureConfig.CODEC)
		)

		// Biomes
		// Biomes changed in 1.16.2 so I will wait on this feature
	}
}