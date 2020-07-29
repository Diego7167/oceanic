package com.github.diego7167

import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig

object Util {
	fun handleBiome(biome: Biome) {
		if(biome.category != Biome.Category.NETHER && biome.category != Biome.Category.THEEND) {
			biome.addFeature(
					GenerationStep.Feature.UNDERGROUND_ORES,
					Feature.ORE.configure(
							OreFeatureConfig(
									OreFeatureConfig.Target.NATURAL_STONE,
									Oceanic.shinyGravel.defaultState,
									3
							)
					).createDecoratedFeature(
							Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(
									2,
									0,
									0,
									50
							))
					)
			)
		}
	}
}