package io.github.haykam821.smootherbedrock.mixin;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import com.google.common.base.Predicates;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;

public class SmootherBedrockMixinConfigPlugin implements IMixinConfigPlugin {
	private static final String MIXIN_CLASS_PREFIX = "io.github.haykam821.smootherbedrock.mixin.";
	private static final String MIXIN_CLASS_1_16 = MIXIN_CLASS_PREFIX + "NoiseChunkGeneratorMixin116";
	private static final String MIXIN_CLASS_1_17 = MIXIN_CLASS_PREFIX + "NoiseChunkGeneratorMixin117";
	private static final String MIXIN_CLASS_1_18 = MIXIN_CLASS_PREFIX + "NoiseChunkGeneratorMixin118";
	private static final String ACCESSOR_CLASS_1_18 = MIXIN_CLASS_PREFIX + "LayerTransitionBlockSourceAccessor";
	private static final String MIXIN_CLASS_ECOTONES = MIXIN_CLASS_PREFIX + "BaseEcotonesChunkGeneratorMixin";

	private static final Predicate<Version> IS_1_17 = createVersionCompatibility(">=1.17-alpha.20.45.a");
	private static final Predicate<Version> IS_1_18 = createVersionCompatibility(">=1.18-alpha.21.40.a");
	private static final BooleanSupplier HAS_ECOTONES = createModCompatibility("ecotones");

	@Override
	public void onLoad(String mixinPackage) {
		return;
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClass, String mixinClass) {
		if (mixinClass.equals(MIXIN_CLASS_1_16)) {
			return !IS_1_17.test(getMinecraftVersion());
		} else if (mixinClass.equals(MIXIN_CLASS_1_17)) {
			return IS_1_17.test(getMinecraftVersion()) && !IS_1_18.test(getMinecraftVersion());
		} else if (mixinClass.equals(MIXIN_CLASS_1_18) || mixinClass.equals(ACCESSOR_CLASS_1_18)) {
			return IS_1_18.test(getMinecraftVersion());
		} else if (mixinClass.equals(MIXIN_CLASS_ECOTONES)) {
			return HAS_ECOTONES.getAsBoolean();
		}

		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		return;
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		return;
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		return;
	}

	private static Version getMinecraftVersion() {
		Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("minecraft");

		if (container.isPresent()) {
			Version version = container.get().getMetadata().getVersion();
			if (version instanceof SemanticVersion) {
				return version;
			}
		}

		return null;
	}

	private static Predicate<Version> createVersionCompatibility(String versionRange) {
		try {
			return VersionPredicate.parse(versionRange);
		} catch (VersionParsingException exception) {
			return Predicates.alwaysFalse();
		}
	}

	private static BooleanSupplier createModCompatibility(String id) {
		return () -> FabricLoader.getInstance().isModLoaded(id);
	}
}