package net.stonksman01.dungeons_reborn._included_libs.skycore;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.poi.PointOfInterestType;
import net.stonksman01.dungeons_reborn.DungeonsReborn;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.stonksman01.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SkyCore {
    public static Identifier identifierOfDungeonsReborn(String id) {
        return Identifier.of(DungeonsReborn.MOD_ID, id);
    }
    public static boolean wrapRangedWeaponHardcodedCallsIfPresent(ItemStack instance, Item item, Operation<Boolean> original) {
        if (item instanceof BowItem) {
            return original.call(instance, item) || instance.getItem() instanceof SC_BowItem;
        }
        if (item instanceof CrossbowItem) {
            return original.call(instance, item) || instance.getItem() instanceof SC_CrossbowItem;
        }
        return original.call(instance, item);
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
        public static Item registerItemThatHasBlock(String name, Block block, Item.Settings settings) {
            return registerItem(name, settings_ -> new BlockItem(block, settings_), settings
                    .useItemPrefixedTranslationKey()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, identifierOfDungeonsReborn(name))));
        }
        public static Item registerItem(String name, Function<Item.Settings, Item> factory) {
            return registerItem(name, RegistryKey.of(RegistryKeys.ITEM, identifierOfDungeonsReborn(name)), factory, new Item.Settings());
        }
        public static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
            return registerItem(name, RegistryKey.of(RegistryKeys.ITEM, identifierOfDungeonsReborn(name)), factory, settings);
        }
        private static Item registerItem(String name, RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
            Item item = factory.apply(settings.registryKey(key));
            var registry = Registries.ITEM;
            Identifier parentId = identifierOfDungeonsReborn(name);
            if (SkyCoreDataFixerAPI.ITEM_WITH_ALIAS.containsKey(parentId.toString())) {
                for (String alias : SkyCoreDataFixerAPI.ITEM_WITH_ALIAS.get(parentId.toString())) {
                    registry.addAlias(Identifier.of(alias), parentId);
                }
            }
            return Registry.register(registry, key, item);
        }
        public static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
            return registerBlock(name, RegistryKey.of(RegistryKeys.BLOCK, identifierOfDungeonsReborn(name)), factory, settings);
        }
        public static Block registerBlock(String name, RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
            Block block = factory.apply(settings.registryKey(key));
            var registry = Registries.BLOCK;
            Identifier parentId = identifierOfDungeonsReborn(name);
            if (SkyCoreDataFixerAPI.BLOCKS_WITH_ALIAS.containsKey(parentId.toString())) {
                for (String alias : SkyCoreDataFixerAPI.BLOCKS_WITH_ALIAS.get(parentId.toString())) {
                    registry.addAlias(Identifier.of(alias), parentId);
                }
            }
            return Registry.register(registry, key, block);
        }
        public static Block registerBlockAndItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
            Block block = registerBlock(name, factory, settings);
            registerBlockItem(name, block, new Item.Settings());
            return block;
        }
        public static Item registerBlockItem(String name, Block block, Item.Settings settings) {
            return registerItem(name, settings_ -> new BlockItem(block, settings_), settings
                    .useBlockPrefixedTranslationKey()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, identifierOfDungeonsReborn(name))));
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
        public static <T extends GameRules.Rule<T>> GameRules.Key<T> registerGameRule(String id, GameRules.Category category, GameRules.Type<T> rule) {
            return GameRuleRegistry.register("dr_" + id, category, rule);
        }
        public static <FC extends FeatureConfig, F extends Feature<FC>> void registerConfiguredFeature(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }
    }
}
