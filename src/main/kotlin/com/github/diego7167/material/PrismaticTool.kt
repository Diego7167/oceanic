package com.github.diego7167.material

import com.github.diego7167.Oceanic
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Lazy
import java.util.function.Supplier

enum class PrismaticTool(
	private val itemMiningLevel: Int,
	private val itemDurability: Int,
	private val itemMiningSpeedMultiplier: Float,
	private val itemAttackDamage: Float,
	private val itemEnchantability: Int,
	itemRepairIngredientSupplier: Supplier<Ingredient>
): ToolMaterial {
	PRISMATIC(
		4,
		1750,
		8.5f,
		3.75f,
		18,
		Supplier {
			return@Supplier Ingredient.ofItems(Oceanic.pureCrystal)
		}
	);

	private val itemRepairIngredient = Lazy(itemRepairIngredientSupplier)

	override fun getDurability(): Int {
		return itemDurability
	}

	override fun getMiningSpeedMultiplier(): Float {
		return itemMiningSpeedMultiplier
	}

	override fun getAttackDamage(): Float {
		return itemAttackDamage
	}

	override fun getMiningLevel(): Int {
		return itemMiningLevel
	}

	override fun getEnchantability(): Int {
		return itemEnchantability
	}

	override fun getRepairIngredient(): Ingredient {
		return itemRepairIngredient.get()
	}
}