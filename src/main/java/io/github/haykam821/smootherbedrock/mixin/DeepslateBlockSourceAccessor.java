package io.github.haykam821.smootherbedrock.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.DeepslateBlockSource;

@Mixin(DeepslateBlockSource.class)
public interface DeepslateBlockSourceAccessor {
	@Accessor("field_35137")
	public BlockState getBelowState();

	@Accessor("field_35138")
	public BlockState getAboveState();

	@Accessor("field_35139")
	public int getMinY();

	@Accessor("field_35140")
	public int getMaxY();
}
