package com.github.diego7167.oceanic.util

import com.github.diego7167.oceanic.Oceanic
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.StructureWorldAccess
import java.util.*

object Util {
	fun randChunkPos(pos: BlockPos, random: Random): BlockPos {
		val randomx = random.nextInt(16)
		val randomy = random.nextInt(16)

		return pos.south(randomx).west(randomy)
	}

	fun genDeepstoneCircle(pos: BlockPos, world: StructureWorldAccess, random: Random) {
		var row = pos
		val patters = arrayOf(
			arrayOf( // Small
				" ### ",
				"#####",
				"#####",
				"#####",
				" ### "
			),
			arrayOf( // Medium
				"  ###  ",
				" ##### ",
				"#######",
				"#######",
				"#######",
				" ##### ",
				"  ### "
			),
			arrayOf( // Large
				"  #####  ",
				" ####### ",
				"#########",
				"#########",
				"#########",
				"#########",
				"#########",
				" ####### ",
				"  #####  "
			)
		)
		val pattern = patters[random.nextInt(patters.size)]

		for(line in pattern) { // Get rows
			var col = row // Copy x position
			for(char in line.split("")) { // Get each character
				println("${world.getBlockState(col.up())} / ${world.getBlockState(col)} at ${pos}")
				if(char == "#"
					&& (world.getBlockState(col.up()) == Blocks.WATER.defaultState	// Generate if is under water
					|| !world.getBlockState(col.up()).isOpaque)						// Or translucent block
					&& world.getBlockState(col) != Blocks.WATER.defaultState		// Is not on water
					&& world.getBlockState(col).isOpaque							// Is on opaque block
					&& world.getBlockState(col.down()) != Blocks.WATER.defaultState	// And is not above water
					&& world.getBlockState(col.down()).isOpaque						// Or translucent block
				) {
					world.setBlockState(col, Oceanic.deepStone.defaultState, 3)
				}
				col = col.west()
			}
			row = row.south()
		}
	}
}