package d0mano.thalassite.world;

import d0mano.thalassite.Thalassite;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModWorldGeneration {

    // Points to configured feature we define in JSON (see below)
    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> THALASSITE_ORE_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                    Identifier.of(Thalassite.MOD_ID, "thalassite_ore"));

    public static void generateModWorldGen() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                THALASSITE_ORE_PLACED
        );
    }
}