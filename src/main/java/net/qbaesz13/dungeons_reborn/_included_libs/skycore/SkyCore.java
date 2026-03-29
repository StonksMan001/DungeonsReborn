package net.qbaesz13.dungeons_reborn._included_libs.skycore;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.qbaesz13.dungeons_reborn.DungeonsReborn;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_BowItem;
import net.qbaesz13.dungeons_reborn._included_libs.skycore.items.SC_CrossbowItem;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class SkyCore {
    public static boolean wrapRangedWeaponHardcodedCallsIfPresent(ItemStack instance, Item item, Operation<Boolean> original) {
        if (item instanceof BowItem) {
            return original.call(instance, item) || instance.getItem() instanceof SC_BowItem;
        }
        if (item instanceof CrossbowItem) {
            return original.call(instance, item) || instance.getItem() instanceof SC_CrossbowItem;
        }
        return original.call(instance, item);
    }
    public static class RegistryPresets {
        public static ResourceKey<Enchantment> createEnchantmentResourceKey(String name) {
            return ResourceKey.create(Registries.ENCHANTMENT, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static ResourceKey<Level> createLevelResourceKey(String name) {
            return ResourceKey.create(Registries.DIMENSION, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name + ".json"));
        }
        public static ResourceKey<JukeboxSong> createJukeBlockSongResourceKey(String name) {
            return ResourceKey.create(Registries.JUKEBOX_SONG, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static ResourceKey<DimensionType> createDimensionTypeResourceKey(String name) {
            return ResourceKey.create(Registries.DIMENSION_TYPE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static ResourceKey<Biome> createBiomeResourceKey(String name) {
            return ResourceKey.create(Registries.BIOME, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeatureResourceKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static void registerJukeBlockSong(BootstrapContext<JukeboxSong> registry, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
            registry.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), (float)lengthInSeconds, comparatorOutput));
        }
        public static Item registerItemThatHasBlock(String name, Block block, Item.Properties properties) {
            return registerItem(name, settings_ -> new BlockItem(block, settings_), properties
                    .useItemDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name))));
        }
        public static Item registerItem(String name, Function<Item.Properties, Item> function) {
            return registerItem(name, ResourceKey.create(Registries.ITEM, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name)), function, new Item.Properties());
        }
        public static Item registerItem(String name, Function<Item.Properties, Item> function, Item.Properties properties) {
            return registerItem(name, ResourceKey.create(Registries.ITEM, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name)), function, properties);
        }
        private static Item registerItem(String name, ResourceKey<Item> key, Function<Item.Properties, Item> function, Item.Properties properties) {
            Item item = function.apply(properties.setId(key));
            var registry = BuiltInRegistries.ITEM;
            Identifier parentId = DungeonsReborn.identifierFromNamespaceDungeonsReborn(name);
            if (SkyCoreDataFixerAPI.ITEM_WITH_ALIAS.containsKey(parentId.toString())) {
                for (String alias : SkyCoreDataFixerAPI.ITEM_WITH_ALIAS.get(parentId.toString())) {
                    registry.addAlias(Identifier.parse(alias), parentId);
                }
            }
            return Registry.register(registry, key, item);
        }
        public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
            return registerBlock(name, ResourceKey.create(Registries.BLOCK, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name)), function, properties);
        }
        public static Block registerBlock(String name, ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
            Block block = function.apply(properties.setId(key));
            var registry = BuiltInRegistries.BLOCK;
            Identifier parentId = DungeonsReborn.identifierFromNamespaceDungeonsReborn(name);
            if (SkyCoreDataFixerAPI.BLOCKS_WITH_ALIAS.containsKey(parentId.toString())) {
                for (String alias : SkyCoreDataFixerAPI.BLOCKS_WITH_ALIAS.get(parentId.toString())) {
                    registry.addAlias(Identifier.parse(alias), parentId);
                }
            }
            return Registry.register(registry, key, block);
        }
        public static Block registerBlockAndItem(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
            Block block = registerBlock(name, function, properties);
            registerBlockItem(name, block, new Item.Properties());
            return block;
        }
        public static Item registerBlockItem(String name, Block block, Item.Properties properties) {
            return registerItem(name, properties_ -> new BlockItem(block, properties_), properties
                    .useBlockDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name))));
        }
        public static SoundEvent registerSoundEvent(String name) {
            Identifier identifier = DungeonsReborn.identifierFromNamespaceDungeonsReborn(name);
            return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
        }
        public static Holder.Reference<SoundEvent> registerSoundEventReference(String name) {
            Identifier identifier = DungeonsReborn.identifierFromNamespaceDungeonsReborn(name);
            return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
        }
        /*
        public static PoiType registerPoiType(String name, int ticket_count, int search_distance, Block... blocks) {
            return PoiTypes.register(DungeonsReborn.identifierFromNamespaceDungeonsReborn(name), ticket_count, search_distance, blocks);
        }
        */
        public static ResourceKey<PoiType> poiResourceKey(String name) {
            return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType<T> type) {
            return Registry.register(BuiltInRegistries.ENTITY_TYPE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name), type);
        }
        public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntityType(String name, BlockEntityType<T> type) {
            return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name), type);
        }
        public static <T extends AbstractContainerMenu> MenuType<T> registerScreenHandler(String name, MenuType<T> type) {
            return Registry.register(BuiltInRegistries.MENU, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name), type);
        }
        public static CreativeModeTab registerCreativeModeTab(String name, CreativeModeTab creativeModeTab) {
            return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name), creativeModeTab);
        }
        public static <T> DataComponentType<T> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> componentTypeBuilderOperator) {
            return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name),
                    componentTypeBuilderOperator.apply(DataComponentType.builder()).build());
        }
        public static TagKey<Block> createBlockTag(String name) {
            return TagKey.create(Registries.BLOCK, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static TagKey<Item> createItemTag(String name) {
            return TagKey.create(Registries.ITEM, DungeonsReborn.identifierFromNamespaceDungeonsReborn(name));
        }
        public static GameRule<Boolean> registerBooleanGameRule(String name, GameRuleCategory category, boolean defaultValue) {
            return registerGameRule(
                    name,
                    category,
                    GameRuleType.BOOL,
                    BoolArgumentType.bool(),
                    Codec.BOOL,
                    defaultValue,
                    FeatureFlagSet.of(),
                    GameRuleTypeVisitor::visitBoolean,
                    value -> value ? 1 : 0
            );
        }
        public static GameRule<Integer> registerCappedIntRule(String name, GameRuleCategory category, int defaultValue, int minValue, int maxValue) {
            return registerIntRule(name, category, defaultValue, minValue, maxValue, FeatureFlagSet.of());
        }
        public static GameRule<Integer> registerIntRule(String name, GameRuleCategory category, int defaultValue, int minValue, int maxValue) {
            return registerIntRule(name, category, defaultValue, minValue, maxValue, FeatureFlagSet.of());
        }

        public static GameRule<Integer> registerIntRule(
                String name, GameRuleCategory category, int defaultValue, int minValue, int maxValue, FeatureFlagSet requiredFeatures
        ) {
            return registerGameRule(
                    name,
                    category,
                    GameRuleType.INT,
                    IntegerArgumentType.integer(minValue, maxValue),
                    Codec.intRange(minValue, maxValue),
                    defaultValue,
                    requiredFeatures,
                    GameRuleTypeVisitor::visitInteger,
                    value -> value
            );
        }
        private static <T> GameRule<T> registerGameRule(
                final String id,
                final GameRuleCategory category,
                final GameRuleType typeHint,
                final ArgumentType<T> argumentType,
                final Codec<T> codec,
                final T defaultValue,
                final FeatureFlagSet requiredFeatures,
                final GameRules.VisitorCaller<T> visitorCaller,
                final ToIntFunction<T> commandResultFunction
        ) {
            return Registry.register(
                    BuiltInRegistries.GAME_RULE, "dr__" + id, new GameRule<>(category, typeHint, argumentType, visitorCaller, codec, commandResultFunction, defaultValue, requiredFeatures)
            );
        }
        public static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerConfiguredFeature(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }
    }
}
