package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {
	@Redirect(method = {"createOverworldSurfaceRule", "createNetherSurfaceRule"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/surfacebuilder/MaterialRules;verticalGradient(Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;", ordinal = 0))
	private static MaterialRules.MaterialCondition adjustOverworldAndNetherBedrockFloor(String id, YOffset trueAtAndBelow, YOffset falseAtAndAbove) {
		return MaterialRules.verticalGradient(id, trueAtAndBelow, trueAtAndBelow);
	}

	@Redirect(method = "createNetherSurfaceRule", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/surfacebuilder/MaterialRules;verticalGradient(Ljava/lang/String;Lnet/minecraft/world/gen/YOffset;Lnet/minecraft/world/gen/YOffset;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialCondition;", ordinal = 1))
	private static MaterialRules.MaterialCondition adjustNetherBedrockRoof(String id, YOffset trueAtAndBelow, YOffset falseAtAndAbove) {
		return MaterialRules.verticalGradient(id, YOffsetAccessor.belowTop(1), trueAtAndBelow);
	}
}