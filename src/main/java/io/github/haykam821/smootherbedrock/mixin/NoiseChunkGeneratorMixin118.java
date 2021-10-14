package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.BlockSource;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin118 {
	@ModifyArg(method = "<init>(Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/biome/source/BiomeSource;JLjava/util/function/Supplier;)V", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 0), index = 0)
	private Object adjustBedrockFloorMaxY(Object blockSource) {
		DeepslateBlockSourceAccessor accessor = (DeepslateBlockSourceAccessor) blockSource;
		return new SmootherBedrockBlockSource(accessor.getBelowState(), accessor.getMinY());
	}

	@ModifyArg(method = "<init>(Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/biome/source/BiomeSource;JLjava/util/function/Supplier;)V", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 1), index = 0)
	private Object adjustBedrockCeilingMinY(Object blockSource) {
		DeepslateBlockSourceAccessor accessor = (DeepslateBlockSourceAccessor) blockSource;
		return new SmootherBedrockBlockSource(accessor.getAboveState(), accessor.getMaxY());
	}

	private static class SmootherBedrockBlockSource implements BlockSource {
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
}