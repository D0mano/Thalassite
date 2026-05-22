package d0mano.thalassite;

import d0mano.thalassite.block.ModBlocks;
import d0mano.thalassite.item.ModItems;
import d0mano.thalassite.world.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Thalassite implements ModInitializer {
	public static final String MOD_ID = "thalassite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
        ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGen();
    }
}