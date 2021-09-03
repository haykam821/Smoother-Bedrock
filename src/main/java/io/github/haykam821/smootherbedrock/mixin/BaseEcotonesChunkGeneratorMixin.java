package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Pseudo
@Mixin(targets = "supercoder79.ecotones.world.gen.BaseEcotonesChunkGenerator", remap = false)
public class BaseEcotonesChunkGeneratorMixin {
	@ModifyConstant(method = "buildBedrock", constant = @Constant(intValue = 4))
	private int changeFloorLoopInitialValue(int value) {
		return 0;
	}
}
