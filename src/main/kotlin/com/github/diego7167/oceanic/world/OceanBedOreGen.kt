package com.github.diego7167.oceanic.world

import com.github.diego7167.oceanic.Oceanic
import com.github.diego7167.oceanic.util.Util
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
		val directions = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
		val randPos = Util.randChunkPos(pos!!.down(), random!!)
		var currentPos = world!!.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, randPos).down()

		val veinSize = random.nextInt(8) + 3 // Size of the vein (3 is min, 10 is max)
		val dirOrder = Array(veinSize) {random.nextInt(directions.size)} // Randomize vein shape using index

		for(i in 0 until veinSize) {
			if(world.getBlockState(currentPos) == Blocks.GRAVEL.defaultState || world.getBlockState(currentPos) == Oceanic.shinyGravel.defaultState) {
				world.setBlockState(currentPos, Oceanic.shinyGravel.defaultState, 3)

				currentPos = currentPos.offset(directions[dirOrder[i]])
			} else {
				return false
			}
		}
		return true
	}
}