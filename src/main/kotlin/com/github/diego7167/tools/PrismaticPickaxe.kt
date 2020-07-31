package com.github.diego7167.tools

import com.github.diego7167.material.PrismaticTool
import net.minecraft.item.PickaxeItem

class PrismaticPickaxe(
		attackDamage: Int,
		attackSpeed: Float,
		settings: Settings
): PickaxeItem(
		PrismaticTool.PRISMATIC,
		attackDamage,
		attackSpeed,
		settings
)