package com.github.diego7167.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction

class PureCrystalBlock(settings: Settings): HorizontalFacingBlock(settings) {
	init {
		super.setDefaultState(this.stateManager.defaultState.with(Properties.HORIZONTAL_FACING, Direction.NORTH))
	}

	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
		builder?.add(Properties.HORIZONTAL_FACING)
	}

	override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
		return this.defaultState.with(FACING, ctx.playerFacing)
	}
}