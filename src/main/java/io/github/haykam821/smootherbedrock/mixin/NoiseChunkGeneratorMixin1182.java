package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.haykam821.smootherbedrock.SmootherBedrockBlockSource;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@SuppressWarnings("target")
@Mixin(value = NoiseChunkGenerator.class, remap = false)
public class NoiseChunkGeneratorMixin1182 {
	@ModifyArg(method = "<init>(Lnet/minecraft/class_2378;Lnet/minecraft/class_1966;Lnet/minecraft/class_1966;JLjava/util/function/Supplier;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 0), index = 0)
	private Object adjustBedrockFloorMaxY(Object blockSource) {
		LayerTransitionBlockSourceAccessor accessor = (LayerTransitionBlockSourceAccessor) blockSource;
		return new SmootherBedrockBlockSource(accessor.getBelowState(), accessor.getMinY());
	}

	@ModifyArg(method = "<init>(Lnet/minecraft/class_2378;Lnet/minecraft/class_1966;Lnet/minecraft/class_1966;JLjava/util/function/Supplier;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;", ordinal = 1), index = 0)
	private Object adjustBedrockCeilingMinY(Object blockSource) {
		LayerTransitionBlockSourceAccessor accessor = (LayerTransitionBlockSourceAccessor) blockSource;
		return new SmootherBedrockBlockSource(accessor.getAboveState(), accessor.getMaxY());
	}
}