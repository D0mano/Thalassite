package d0mano.thalassite.block;

import d0mano.thalassite.Thalassite;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    public static final Block THALASSITE_ORE = registerBlock("thalassite_ore",
            new Block(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                            Identifier.of(Thalassite.MOD_ID, "thalassite_ore")))
                    .hardness(1f)
                    .resistance(1f)
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 5)
                    .requiresTool()));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Thalassite.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of(Thalassite.MOD_ID, name));
        Item item = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, Identifier.of(Thalassite.MOD_ID, name), item);
    }

    public static void registerModBlocks() {
        Thalassite.LOGGER.info("Registering blocks for " + Thalassite.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(THALASSITE_ORE);
        });
    }
}