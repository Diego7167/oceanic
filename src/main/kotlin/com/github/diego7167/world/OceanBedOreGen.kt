package com.github.diego7167.world

import com.github.diego7167.Oceanic
import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*

class OceanBedOreGen(config: Codec<DefaultFeatureConfig>): Feature<DefaultFeatureConfig>(config) {
	override fun generate(
		world: ServerWorldAccess?,
		structureAccessor: StructureAccessor?,
		generator: ChunkGenerator?,
		random: Random?,
		pos: BlockPos?,
		config: DefaultFeatureConfig?
	): Boolean {
		val topPos = world!!.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, pos)
		val directions = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN)
		var currentPos = topPos.down() // Go down into the floor

		val veinSize = random!!.nextInt(7) + 3 // Size of the vein
		val dirOrder = Array(veinSize) {random.nextInt(directions.size)} // Randomize vein shape using index

		return if(world.getBlockState(currentPos) == Blocks.GRAVEL.defaultState) {
			for(i in 0 until veinSize) {
				world.setBlockState(currentPos, Oceanic.shinyGravel.defaultState, 3)

				currentPos = currentPos.offset(directions[dirOrder[i]])
			}

			true
		} else {
			false
		}
	}
}