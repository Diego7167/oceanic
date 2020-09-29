package com.github.diego7167.oceanic.tools

import com.github.diego7167.oceanic.material.OceanicToolMaterial
import net.minecraft.item.AxeItem

class PrismaticAxe(
    attackDamage: Float,
    attackSpeed: Float,
    settings: Settings
): AxeItem(
    OceanicToolMaterial.PRISMATIC,
    attackDamage,
    attackSpeed,
    settings
)