package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(value = NoiseChunkGenerator.class, remap = false)
public class NoiseChunkGeneratorMixin116 {
	@ModifyConstant(method = "method_16412", constant = @Constant(intValue = 5))
	private int changeRoofLoopCount(int value) {
		return 1;
	}

	@ModifyConstant(method = "method_16412", constant = @Constant(intValue = 4, ordinal = 2))
	private int changeFloorLoopInitialValue(int value) {
		return 0;
	}
}