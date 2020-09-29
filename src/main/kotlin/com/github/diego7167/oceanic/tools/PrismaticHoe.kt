package com.github.diego7167.oceanic.tools

import com.github.diego7167.oceanic.material.OceanicToolMaterial
import net.minecraft.item.HoeItem

class PrismaticHoe(
    attackDamage: Int,
    attackSpeed: Float,
    settings: Settings
): HoeItem(
    OceanicToolMaterial.PRISMATIC,
    attackDamage,
    attackSpeed,
    settings
)