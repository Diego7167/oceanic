package com.github.diego7167.oceanic.util

import com.github.diego7167.oceanic.Oceanic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.StructureWorldAccess
import java.util.*

object Util {
	fun randChunkPos(pos: BlockPos, random: Random): BlockPos {
		val randomX = random.nextInt(16)
		val randomY = random.nextInt(16)

		return pos.south(randomX).west(randomY)
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
				if(char == "#"
					&& !world.getBlockState(col.up()).isOpaque						// Or translucent block
					&& world.getBlockState(col).isOpaque							// Is on an opaque block
					&& world.getBlockState(col.down()).isOpaque						// And is not above a translucent block (Water, kelp, etc.)
				) {
					world.setBlockState(col, Oceanic.deepStone.defaultState, 3)
				}
				col = col.west()
			}
			row = row.south()
		}
	}
}