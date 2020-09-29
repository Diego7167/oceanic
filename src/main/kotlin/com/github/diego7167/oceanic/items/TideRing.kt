package com.github.diego7167.oceanic.items

import com.github.diego7167.oceanic.util.Util
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Rarity
import net.minecraft.world.World

class TideRing(settings: Settings) : Item(settings) {
	override fun getRarity(stack: ItemStack?): Rarity {
		return Rarity.UNCOMMON
	}

	override fun appendTooltip(
		stack: ItemStack?,
		world: World?,
		tooltip: MutableList<Text>?,
		context: TooltipContext?
	) {
		val text = TranslatableText("item.oceanic.tide_ring.tooltip")
		Util.multilineTooltip(text.string, tooltip!!)
	}
}