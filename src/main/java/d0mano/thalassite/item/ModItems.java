package d0mano.thalassite.item;

import d0mano.thalassite.Thalassite;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Function;


public class ModItems {

    public static final Item THALASSITE = registerItem("thalassite",Item::new);
    public static final TagKey<Item> REPAIRS_THALASSITE = TagKey.of(RegistryKeys.ITEM,Identifier.of(Thalassite.MOD_ID,"thalassite_repair"));
    public static final TagKey<EquipmentAsset> THALASSITE_ARMOR_MATERIAL_KEY = TagKey.of(RegistryKeys.ITEM,Identifier.of)

    // -- THALASSITE TOOL MATERIAL CREATION -- //
    public static final ToolMaterial THALASSITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            REPAIRS_THALASSITE
    );


    private static Item registerItem(String name){
        Identifier Item_ID = Identifier.of(Thalassite.MOD_ID, name);
        RegistryKey<Item> Item_KEY = RegistryKey.of(RegistryKeys.ITEM, Item_ID);
        Item Item = new Item(new Item.Settings().registryKey(Item_KEY));


    // -- TOOLS --//
    public static final Item THALASSITE_SWORD = registerItem("thalassite_sword",
            settings -> new Item(settings.sword(THALASSITE_TOOL_MATERIAL,5.5f,-2.4f)));
    public static final Item THALASSITE_PICKAXE = registerItem("thalassite_pickaxe",
            settings -> new Item(settings.pickaxe(THALASSITE_TOOL_MATERIAL,1.0f,-2.8f)));
    public static final Item THALASSITE_SHOVEL  = registerItem("thalassite_shovel",
            settings -> new Item(settings.shovel(THALASSITE_TOOL_MATERIAL,1.5f,-3.0f)));
    public static final Item THALASSITE_AXE = registerItem("thalassite_axe",
            settings -> new Item(settings.axe(THALASSITE_TOOL_MATERIAL, 6.5f, -3.0f)));
    public static final Item THALASSITE_HOE = registerItem("thalassite_hoe",
            settings -> new Item(settings.hoe(THALASSITE_TOOL_MATERIAL, -3.0f, 0.0f)));

    // -- ARMOR -- //





    private static Item registerItem(String name,Function<Item.Settings,Item> factory){
        Identifier item_ID = Identifier.of(Thalassite.MOD_ID, name);
        RegistryKey<Item> item_KEY = RegistryKey.of(RegistryKeys.ITEM, item_ID);
        Item.Settings settings = new Item.Settings().registryKey(item_KEY);

        Item item = factory.apply(settings);
        return Registry.register(Registries.ITEM, Identifier.of(Thalassite.MOD_ID,name), item);
    }
    public static void registerModItems(){
        Thalassite.LOGGER.info("Registering the mod Items for"+Thalassite.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->{
            entries.add(THALASSITE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->{
            entries.add(THALASSITE_PICKAXE);
            entries.add(THALASSITE_AXE);
            entries.add(THALASSITE_SHOVEL);
            entries.add(THALASSITE_HOE);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->{
            entries.add(THALASSITE_SWORD);
            entries.add(THALASSITE_AXE);
        });

    }
}
