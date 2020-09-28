package com.github.diego7167.oceanic.world

import com.github.diego7167.oceanic.Oceanic
import com.github.diego7167.oceanic.util.Util
import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*

class DeepStoneGen(config: Codec<DefaultFeatureConfig>): Feature<DefaultFeatureConfig>(config) {
	override fun generate(
		world: StructureWorldAccess?,
		chunkGenerator: ChunkGenerator?,
		random: Random?,
		pos: BlockPos?,
		config: DefaultFeatureConfig?
	): Boolean {
		fun setStone(pos: BlockPos, world: StructureWorldAccess) {
			if(world.getBlockState(pos) != Blocks.WATER.defaultState) {
				world.setBlockState(pos, Oceanic.deepStone.defaultState, 3)
			}
		}

		val randPos = Util.randChunkPos(pos!!, random!!)
		val currentPos = world!!.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, randPos).down()

		// Shiny new automation
		println("Deepstone at ${currentPos.x} ${currentPos.y} ${currentPos.z}")
		Util.genDeepstoneCircle(currentPos, world, random)

		return true
	}
}