package net.stonksman01.dungeons_reborn._included_libs;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.poi.PointOfInterestType;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SkyCore {
    public static Identifier identifierOfDungeonsReborn(String id) {
        return Identifier.of(DungeonsReborn.MOD_ID, id);
    }
    public static class BuiltinRegistries {
        public static RegistryKey<Enchantment> ofEnchantmentRegistry(String name) {
            return RegistryKey.of(RegistryKeys.ENCHANTMENT, identifierOfDungeonsReborn(name));
        }
        public static RegistryKey<World> ofMinecraftWorldRegistry(String name) {
            return RegistryKey.of(RegistryKeys.WORLD, identifierOfDungeonsReborn(name + ".json"));
        }
        public static RegistryKey<JukeboxSong> ofJukeBlockSongRegistry(String name) {
            return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, identifierOfDungeonsReborn(name));
        }
        public static RegistryKey<DimensionType> ofDimensionTypeRegistry(Identifier id) {
            return RegistryKey.of(RegistryKeys.DIMENSION_TYPE, id);
        }
        public static RegistryKey<DimensionType> ofDimensionTypeRegistry(String name) {
            return RegistryKey.of(RegistryKeys.DIMENSION_TYPE, identifierOfDungeonsReborn(name));
        }
        public static RegistryKey<Biome> ofBiomeRegistry(String name) {
            return RegistryKey.of(RegistryKeys.BIOME, identifierOfDungeonsReborn(name));
        }
        public static RegistryKey<ConfiguredFeature<?, ?>> ofConfiguredFeatureRegistry(String name) {
            return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, identifierOfDungeonsReborn(name));
        }
        public static <U extends Sensor<?>> SensorType<U> registerSensorType(String name, Supplier<U> factory) {
            return Registry.register(Registries.SENSOR_TYPE, identifierOfDungeonsReborn(name), new SensorType<>(factory));
        }
        public static void registerJukeBlockSong(Registerable<JukeboxSong> registry, RegistryKey<JukeboxSong> key, RegistryEntry.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
            registry.register(key, new JukeboxSong(soundEvent, Text.translatable(Util.createTranslationKey("jukebox_song", key.getValue())), (float)lengthInSeconds, comparatorOutput));
        }
        public static Item registerItemThatHasBlock(BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
            return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Registries.BLOCK.getId(blockItem.getBlock())), blockItem);
        }
        public static Item registerItem(String name, Item item) {
            return Registry.register(Registries.ITEM, identifierOfDungeonsReborn(name), item);
        }
        public static Block registerBlock(String name, Block block) {
            return Registry.register(Registries.BLOCK, identifierOfDungeonsReborn(name), block);
        }
        public static Block registerBlockAndItem(String name, Block block) {
            registerBlockItem(name, block);
            return registerBlock(name, block);
        }
        public static Item registerBlockItem(String name, Block block) {
            return Registry.register(Registries.ITEM, identifierOfDungeonsReborn(name), new BlockItem(block, new Item.Settings()));
        }
        public static SoundEvent registerSoundEvent(String name) {
            Identifier identifier = identifierOfDungeonsReborn(name);
            return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
        }
        public static RegistryEntry.Reference<SoundEvent> registerSoundEventReference(String name) {
            Identifier identifier = identifierOfDungeonsReborn(name);
            return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
        }
        public static PointOfInterestType registerPointOfInterest(String name, int ticket_count, int search_distance, Block... blocks) {
            return PointOfInterestHelper.register(identifierOfDungeonsReborn(name), ticket_count, search_distance, blocks);
        }
        public static RegistryKey<PointOfInterestType> poiRegistryKey(String name) {
            return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, identifierOfDungeonsReborn(name));
        }
        public static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType<T> type) {
            return Registry.register(Registries.ENTITY_TYPE, identifierOfDungeonsReborn(name), type);
        }
        public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntityType(String name, BlockEntityType<T> type) {
            return Registry.register(Registries.BLOCK_ENTITY_TYPE, identifierOfDungeonsReborn(name), type);
        }
        public static <T extends ScreenHandler> ScreenHandlerType<T> registerScreenHandler(String name, ScreenHandlerType<T> type) {
            return Registry.register(Registries.SCREEN_HANDLER, identifierOfDungeonsReborn(name), type);
        }
        public static ItemGroup registerItemGroup(String name, ItemGroup itemGroup) {
            return Registry.register(Registries.ITEM_GROUP, identifierOfDungeonsReborn(name), itemGroup);
        }
        public static <T> ComponentType<T> registerComponentType(String name, UnaryOperator<ComponentType.Builder<T>> componentTypeBuilderOperator) {
            return Registry.register(Registries.DATA_COMPONENT_TYPE, identifierOfDungeonsReborn(name),
                    componentTypeBuilderOperator.apply(ComponentType.builder()).build());
        }
        public static TagKey<Block> createBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, identifierOfDungeonsReborn(name));
        }
        public static TagKey<Item> createItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, identifierOfDungeonsReborn(name));
        }
        public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
            EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
            ArmorItem.Type[] armorItemTypes = ArmorItem.Type.values();

            for (ArmorItem.Type type : armorItemTypes) {
                enumMap.put(type, defense.get(type));
            }

            return Registry.registerReference(Registries.ARMOR_MATERIAL, identifierOfDungeonsReborn(name), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
        }
        public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
            List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(identifierOfDungeonsReborn(id)));
            return registerArmorMaterial(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
        }
        public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, RegistryEntry<ArmorMaterial> copyOf) {
            List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(identifierOfDungeonsReborn(id)));
            return registerArmorMaterial(id, (EnumMap<ArmorItem.Type, Integer>) copyOf.value().defense(), copyOf.value().enchantability(), copyOf.value().equipSound(), copyOf.value().toughness(), copyOf.value().knockbackResistance(), copyOf.value().repairIngredient(), list);
        }
        public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, RegistryEntry<ArmorMaterial> copyOf, Supplier<Ingredient> repairIngredient) {
            List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(identifierOfDungeonsReborn(id)));
            return registerArmorMaterial(id, (EnumMap<ArmorItem.Type, Integer>) copyOf.value().defense(), copyOf.value().enchantability(), copyOf.value().equipSound(), copyOf.value().toughness(), copyOf.value().knockbackResistance(), repairIngredient, list);
        }
        public static <FC extends FeatureConfig, F extends Feature<FC>> void registerConfiguredFeature(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }

    }
    public static class ToolAPI {
        public static class ShovelItem extends net.minecraft.item.ShovelItem {
            public ShovelItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
                super(toolMaterial, settings.attributeModifiers(net.minecraft.item.ShovelItem.createAttributeModifiers(toolMaterial, baseAttackDamage, attackSpeed)));
            }
        }
        public static class SwordItem extends net.minecraft.item.SwordItem {
            public SwordItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
                super(toolMaterial, settings.attributeModifiers(net.minecraft.item.ShovelItem.createAttributeModifiers(toolMaterial, baseAttackDamage, attackSpeed)));
            }
        }
        public static class AxeItem extends net.minecraft.item.AxeItem {
            public AxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
                super(toolMaterial, settings.attributeModifiers(net.minecraft.item.ShovelItem.createAttributeModifiers(toolMaterial, baseAttackDamage, attackSpeed)));
            }
        }
        public static class PickaxeItem extends net.minecraft.item.PickaxeItem {
            public PickaxeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
                super(toolMaterial, settings.attributeModifiers(net.minecraft.item.ShovelItem.createAttributeModifiers(toolMaterial, baseAttackDamage, attackSpeed)));
            }
        }
        public static class HoeItem extends net.minecraft.item.HoeItem {
            public HoeItem(ToolMaterial toolMaterial, float baseAttackDamage, float attackSpeed, Settings settings) {
                super(toolMaterial, settings.attributeModifiers(net.minecraft.item.ShovelItem.createAttributeModifiers(toolMaterial, baseAttackDamage, attackSpeed)));
            }
        }
    }
    public static class BlockEntityAPI {
        public static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> thisType, BlockEntityType<E> targetType, BlockEntityTicker<? super E> ticker) {
            return targetType == thisType ? (BlockEntityTicker<A>) ticker : null;
        }
    }
}
