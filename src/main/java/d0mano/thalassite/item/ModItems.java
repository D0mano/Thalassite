package d0mano.thalassite.item;

import d0mano.thalassite.Thalassite;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item THALASSITE_INGOT = registerItem("thalassite");


    private static Item registerItem(String name){
        Identifier Item_ID = Identifier.of(Thalassite.MOD_ID, name);
        RegistryKey<Item> Item_KEY = RegistryKey.of(RegistryKeys.ITEM, Item_ID);
        Item Item = new Item(new Item.Settings().registryKey(Item_KEY));

        return Registry.register(Registries.ITEM, Identifier.of(Thalassite.MOD_ID,name),Item);
    }
    public static void registerModItems(){
        Thalassite.LOGGER.info("Registering the mod Items for"+Thalassite.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->{
            entries.add(THALASSITE_INGOT);
        });

    }
}
