package com.github.diego7167.tools

import com.github.diego7167.material.PrismaticTool
import net.minecraft.item.AxeItem
import net.minecraft.item.ToolMaterial

class PrismaticAxe(
    attackDamage: Float,
    attackSpeed: Float,
    settings: Settings
): AxeItem(
    PrismaticTool.PRISMATIC,
    attackDamage,
    attackSpeed,
    settings
)