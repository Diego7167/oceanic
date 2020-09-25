package com.github.diego7167.oceanic.tools

import com.github.diego7167.oceanic.material.PrismaticTool
import net.minecraft.item.HoeItem

class PrismaticHoe(
    attackDamage: Int,
    attackSpeed: Float,
    settings: Settings
): HoeItem(
    PrismaticTool.PRISMATIC,
    attackDamage,
    attackSpeed,
    settings
)