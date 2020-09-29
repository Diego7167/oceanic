package com.github.diego7167.oceanic.tools

import com.github.diego7167.oceanic.material.OceanicToolMaterial
import net.minecraft.item.PickaxeItem

class PrismaticPickaxe(
		attackDamage: Int,
		attackSpeed: Float,
		settings: Settings
): PickaxeItem(
		OceanicToolMaterial.PRISMATIC,
		attackDamage,
		attackSpeed,
		settings
)