package com.github.diego7167.oceanic.util

import net.minecraft.util.math.BlockPos
import java.util.*

object Util {
	fun randChunkPos(pos: BlockPos, random: Random): BlockPos {
		val randomx = random.nextInt(16)
		val randomy = random.nextInt(16)

		return pos.south(randomx).west(randomy)
	}
}