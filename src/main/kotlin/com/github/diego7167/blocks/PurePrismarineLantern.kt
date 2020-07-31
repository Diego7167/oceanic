package com.github.diego7167.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class PurePrismarineLantern(settings: Settings): Block(settings.lightLevel(fun(state): Int = if(state.get(this.lit)) {15} else {0})) { // This part controls the light
	// Block state
	companion object Companion {
		@JvmStatic
		val lit: BooleanProperty = BooleanProperty.of("lit")
	}

	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
		builder?.add(lit)
	}

	init {
		super.setDefaultState(getStateManager().defaultState.with(lit, false))
	}

	override fun onUse(state: BlockState?, world: World?, pos: BlockPos?, player: PlayerEntity?, hand: Hand?, hit: BlockHitResult?): ActionResult {
		val newState = !state!!.get(lit) // The new state
		world?.setBlockState(pos, state.with(lit, newState))

		if(!world!!.isClient) {
			world.playSound(
				null,
				pos,
				if (newState) { // Turning on
					SoundEvents.BLOCK_CONDUIT_ACTIVATE
				} else {        // Turning off
					SoundEvents.BLOCK_BEACON_DEACTIVATE
				},
				SoundCategory.BLOCKS,
				0.5f,
				1.5f
			)
		}

		return ActionResult.SUCCESS
	}
}