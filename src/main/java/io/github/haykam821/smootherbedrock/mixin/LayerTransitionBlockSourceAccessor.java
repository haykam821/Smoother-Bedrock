package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.LayerTransitionBlockSource;

@Mixin(LayerTransitionBlockSource.class)
public interface LayerTransitionBlockSourceAccessor {
	@Accessor("belowState")
	public BlockState getBelowState();

	@Accessor("aboveState")
	public BlockState getAboveState();

	@Accessor("minY")
	public int getMinY();

	@Accessor("maxY")
	public int getMaxY();
}
