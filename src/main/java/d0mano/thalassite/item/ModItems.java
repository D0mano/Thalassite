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
    public static final RegistryKey<EquipmentAsset> THALASSITE_EQUIPMENT_ASSET = RegistryKey.of(
            RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")),
            Identifier.of(Thalassite.MOD_ID, "thalassite")
    );


    // -- THALASSITE TOOL MATERIAL CREATION -- //
    public static final ToolMaterial THALASSITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            REPAIRS_THALASSITE

    );

    // -- THALASSITE ARMOR MATERIAL CREATION -- //
    public static final ArmorMaterial THALASSITE_ARMOR_MATERIAL = new ArmorMaterial(
            15,
            Map.of(EquipmentType.HELMET , 4,
                    EquipmentType.CHESTPLATE,9,
                    EquipmentType.LEGGINGS,7,
                    EquipmentType.BOOTS,4

            ),
            18,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            3.0f,
            0.1f,
            REPAIRS_THALASSITE,
            THALASSITE_EQUIPMENT_ASSET
    );


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
    public static final Item THALASSITE_HELMET = registerItem("thalassite_helmet",
            settings -> new Item(settings.armor(THALASSITE_ARMOR_MATERIAL, EquipmentType.HELMET)));
    public static final Item THALASSITE_CHESTPLATE = registerItem("thalassite_chestplate",
            settings -> new Item(settings.armor(THALASSITE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)));
    public static final Item THALASSITE_LEGGINGS = registerItem("thalassite_leggings",
            settings -> new Item(settings.armor(THALASSITE_ARMOR_MATERIAL, EquipmentType.LEGGINGS)));
    public static final Item THALASSITE_BOOTS = registerItem("thalassite_boots",
            settings -> new Item(settings.armor(THALASSITE_ARMOR_MATERIAL, EquipmentType.BOOTS)));





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
            entries.add(THALASSITE_HELMET);
            entries.add(THALASSITE_CHESTPLATE);
            entries.add(THALASSITE_LEGGINGS);
            entries.add(THALASSITE_BOOTS);
        });

    }
}
