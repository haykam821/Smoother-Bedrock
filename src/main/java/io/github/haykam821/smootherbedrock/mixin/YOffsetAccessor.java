package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.gen.YOffset;

@Mixin(YOffset.class)
public interface YOffsetAccessor {
	@Invoker("belowTop")
	public static YOffset belowTop(int offset) {
		throw new AssertionError("YOffset accessor");
	}
}
