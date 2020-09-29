package com.github.diego7167.oceanic.material

import com.github.diego7167.oceanic.Oceanic
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import java.util.function.Supplier

enum class OceanicArmorMaterial(
	private val materialName: String,
	private val durabilityMultiplier: Int,
	private val armorValues: Array<Int>,
	private val materialEnchantability: Int,
	private val soundEvent: SoundEvent,
	private val materialToughness: Float,
	private val materialKnockbackResistance: Float,
	private val repairIngredient: Supplier<Ingredient>
): ArmorMaterial {
	PRISMATIC(
		"prismatic",
		5,
		arrayOf(1, 3, 2, 1),
		15,
		SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
		0.0f,
	0.0f,
		Supplier {
			return@Supplier Ingredient.ofItems(Oceanic.pureCrystal)
		}
	);

	private val baseDurability = arrayOf(13, 15, 16, 11)

	override fun getDurability(slot: EquipmentSlot?): Int {
		return baseDurability[slot!!.entitySlotId] * durabilityMultiplier
	}

	override fun getProtectionAmount(slot: EquipmentSlot?): Int {
		return armorValues[slot!!.entitySlotId]
	}

	override fun getEnchantability(): Int {
		return materialEnchantability
	}

	override fun getEquipSound(): SoundEvent {
		return soundEvent
	}

	override fun getRepairIngredient(): Ingredient {
		return repairIngredient.get()
	}

	@Environment(EnvType.CLIENT)
	override fun getName(): String {
		return materialName
	}

	override fun getToughness(): Float {
		return materialToughness
	}

	override fun getKnockbackResistance(): Float {
		return materialKnockbackResistance
	}
}