package com.github.diego7167.oceanic.world

import com.github.diego7167.oceanic.Oceanic
import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*

class OceanBedOreGen(config: Codec<DefaultFeatureConfig>): Feature<DefaultFeatureConfig>(config) {
	override fun generate(
		world: StructureWorldAccess?,
		generator: ChunkGenerator?,
		random: Random?,
		pos: BlockPos?,
		config: DefaultFeatureConfig?
	): Boolean {
		val topPos = world!!.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, pos)
		val directions = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN)
		var currentPos = topPos.down(1) // Go down into the floor

		val veinSize = random!!.nextInt(7) + 3 // Size of the vein (3 is minimum)
		val dirOrder = Array(veinSize) {random.nextInt(directions.size)} // Randomize vein shape using index

		println(world.getBlockState(currentPos))
		return if(world.getBlockState(currentPos) == Blocks.GRAVEL.defaultState) {
			for(i in 0 until veinSize) {
				world.setBlockState(currentPos, Oceanic.shinyGravel.defaultState, 3)
				println("Generated block at $currentPos")

				currentPos = currentPos.offset(directions[dirOrder[i]])
			}

			true
		} else {
			false
		}
	}
}