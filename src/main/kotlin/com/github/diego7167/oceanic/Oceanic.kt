package com.github.diego7167.oceanic

import com.github.diego7167.oceanic.blocks.PureCrystalBlock
import com.github.diego7167.oceanic.blocks.PurePrismarineLantern
import com.github.diego7167.oceanic.items.TideRing
import com.github.diego7167.oceanic.material.OceanicToolMaterial
import com.github.diego7167.oceanic.tools.PrismaticAxe
import com.github.diego7167.oceanic.tools.PrismaticHoe
import com.github.diego7167.oceanic.tools.PrismaticPickaxe
import com.github.diego7167.oceanic.world.DeepStoneGen
import com.github.diego7167.oceanic.world.OceanBedOreGen
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2
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
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import org.apache.logging.log4j.LogManager
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.CuriosComponent
import top.theillusivec4.curios.api.SlotTypeInfo
import top.theillusivec4.curios.api.SlotTypePreset
import top.theillusivec4.curios.api.type.component.ICurio

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
			.hardness(1.5f)
			.breakByTool(FabricToolTags.PICKAXES, 0)

		// Items
		val purePrismarine = Item(Item.Settings().group(oceanicItemGroup))
		val pureCrystal = Item(Item.Settings().group(oceanicItemGroup))
		val pureShard = Item(Item.Settings().group(oceanicItemGroup))
		val abyss_eye = Item(Item.Settings().group(oceanicItemGroup))
		val tideRing = TideRing(Item.Settings().maxCount(1).group(oceanicItemGroup))
		// Tools
		val prismaticSword = SwordItem(OceanicToolMaterial.PRISMATIC, 3, -2.2f, Item.Settings().group(oceanicItemGroup))
		val prismaticShovel = ShovelItem(OceanicToolMaterial.PRISMATIC, 1.5f, -3.0f, Item.Settings().group(oceanicItemGroup))
		// Pickaxes, axes, and hoes are protected and need to be extended
		val prismaticPickaxe = PrismaticPickaxe(1, -2.8f, Item.Settings().group(oceanicItemGroup))
		val prismaticAxe = PrismaticAxe(7f, -3.2f, Item.Settings().group(oceanicItemGroup))
		val prismaticHoe = PrismaticHoe(7, -3.2f, Item.Settings().group(oceanicItemGroup))

		// Blocks
		// Crafted
		val purePrismarineBlock = Block(stoneLike)
		val pureCrystalBlock = PureCrystalBlock(stoneLike.hardness(5f).breakByTool(FabricToolTags.PICKAXES, 2))
		val purePrismarineLantern = PurePrismarineLantern(FabricBlockSettings.of(Material.GLASS)
			.hardness(0.5f)
			.lightLevel(15)
			.sounds(BlockSoundGroup.GLASS)
			.breakByTool(FabricToolTags.PICKAXES)
		)
		// Natural
		val shinyGravel = FallingBlock(gravelLike)
		val deepstone = Block(stoneLike)

		// Features
		val oceanBedOreGen = OceanBedOreGen(DefaultFeatureConfig.CODEC)
		val oceanBedOreGenConf: ConfiguredFeature<*, *> = oceanBedOreGen.configure(FeatureConfig.DEFAULT)
			.decorate(Decorator.CHANCE.configure(ChanceDecoratorConfig(10)))
		val deepstoneGen = DeepStoneGen(DefaultFeatureConfig.CODEC)
		val deepstoneGenConf: ConfiguredFeature<*, *> = deepstoneGen.configure(FeatureConfig.DEFAULT)
			.decorate(Decorator.CHANCE.configure(ChanceDecoratorConfig(5)))
	}

	// Init
	override fun onInitialize() {
		logger.info("Wield the wonders of the sea!")

		// Register Items
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine"), purePrismarine)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal"), pureCrystal)
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_shard"), pureShard)
		Registry.register(Registry.ITEM, Identifier("oceanic", "abyss_eye"), abyss_eye)
		Registry.register(Registry.ITEM, Identifier("oceanic", "tide_ring"), tideRing)
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
		Registry.register(Registry.BLOCK, Identifier("oceanic", "deepstone"), deepstone)
		// And ItemBlocks
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine_block"), BlockItem(
			purePrismarineBlock, Item.Settings().group(
				oceanicItemGroup
			)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_crystal_block"), BlockItem(
			pureCrystalBlock, Item.Settings().group(
				oceanicItemGroup
			)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "pure_prismarine_lantern"), BlockItem(
			purePrismarineLantern, Item.Settings().group(oceanicItemGroup)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "shiny_gravel"), BlockItem(
			shinyGravel, Item.Settings().group(
				oceanicItemGroup
			)))
		Registry.register(Registry.ITEM, Identifier("oceanic", "deepstone"), BlockItem(
			deepstone, Item.Settings().group(
				oceanicItemGroup
			)))

		// Features
		Registry.register(Registry.FEATURE, Identifier("oceanic", "ocean_bed_ore_gen"), oceanBedOreGen)
		Registry.register(Registry.FEATURE, Identifier("oceanic", "deep_stone_gen"), deepstoneGen)
		// Configured features
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Identifier("oceanic", "ocean_bed_ore_gen"), oceanBedOreGenConf)
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Identifier("oceanic", "deep_stone_gen"), deepstoneGenConf)

		// Register curios
		CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.RING.infoBuilder.build()) // Rings

		// Add curio functionality
		ItemComponentCallbackV2.event(tideRing).register(
			ItemComponentCallbackV2 { _, _, componentContainer ->
				componentContainer[CuriosComponent.ITEM] = object : ICurio {
					override fun canRightClickEquip(): Boolean {
						return true
					}
				}
			}
		)
	}
}