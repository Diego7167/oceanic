package com.github.diego7167.oceanic.items

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Rarity

class TideRing(settings: Settings) : Item(settings) {
	override fun getRarity(stack: ItemStack?): Rarity {
		return Rarity.UNCOMMON
	}
}