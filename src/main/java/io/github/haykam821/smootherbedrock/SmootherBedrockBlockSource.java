package io.github.haykam821.smootherbedrock;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.BlockSource;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;

public class SmootherBedrockBlockSource implements BlockSource {
	private final BlockState state;
	private final int y;

	public SmootherBedrockBlockSource(BlockState state, int y) {
		this.state = state;
		this.y = y;
	}

	@Override
	public BlockState apply(ChunkNoiseSampler chunkNoiseSampler, int x, int y, int z) {
		return y == this.y ? this.state : null;
	}
}