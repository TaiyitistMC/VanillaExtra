package com.taiyitistmc.vanillaextra;

import com.taiyitistmc.vanillaextra.common.entity.BlackDog;
import com.taiyitistmc.vanillaextra.common.entity.FriendlySkeleton;
import com.taiyitistmc.vanillaextra.common.entity.FriendlyZombie;
import com.taiyitistmc.vanillaextra.common.init.ModEntities;
import com.taiyitistmc.vanillaextra.common.init.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

import java.util.List;

@SuppressWarnings("removal")
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = VanillaExtra.MODID)
public class VanillaExtraEventHandler {

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        var entity = event.getEntity();
        var level = entity.level();
        var attacker = entity.getLastHurtByMob();
        if (attacker instanceof Player player) {
            var item = player.getMainHandItem();
            if (item.is(ModItems.PEACH_WOOD_SWORD)) {
                if (entity instanceof Zombie) {
                    FriendlyZombie zombie = FriendlyZombie.createZombie(level);
                    zombie.setPos(entity.position());
                    level.addFreshEntity(zombie);
                } else if (entity instanceof Skeleton) {
                    FriendlySkeleton skeleton = new FriendlySkeleton(ModEntities.FRIENDLY_SKELETON.get(), level);
                    skeleton.setPos(entity.position());
                    level.addFreshEntity(skeleton);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        var entity = event.getTarget();
        var player = event.getEntity();
        var item = player.getMainHandItem();
        boolean canHoldDogBlood = entity instanceof BlackDog && item.is(Items.BOWL);
        if (canHoldDogBlood) {
            if (!player.getAbilities().instabuild) {
                item.shrink(1);
            }
            player.getInventory().add(new ItemStack(ModItems.BLACK_DOG_BLOOD.get()));
        }
    }

    @SubscribeEvent
    public static void onEntitySpawn(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.BLACK_DOG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackDog::checkBlackDogSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(ModEntities.WILD_BOAR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        var registries = event.getRegistries();
        initEntitySimpleDropLootTable(event, EntityType.WOLF, ModItems.WOLF_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.HORSE, ModItems.HORSE_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.SQUID, ModItems.SQUID_RAW, registries);
        initEntitySimpleDropLootTable(event, EntityType.GLOW_SQUID, ModItems.SQUID_RAW, registries);
        initEntitySimpleDropLootTable(event, EntityType.BAT, ModItems.BAT_WING, registries);
        initEntitySimpleDropLootTable(event, EntityType.LLAMA, ModItems.LLAMA_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.VILLAGER, ModItems.HUMAN_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.PILLAGER, ModItems.HUMAN_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.PLAYER, ModItems.HUMAN_MEAT, registries);
        initEntitySimpleDropLootTable(event, EntityType.ENDERMAN, ModItems.ENDERMAN_MEAT, registries);
    }

    private static void initEntitySimpleDropLootTable(LootTableLoadEvent event, EntityType<?> entityType, ItemLike dropItem, HolderLookup.Provider registries) {
        if (event.getKey().equals(genEntityDrops(entityType))) {
            event.getTable().addPool(initEntitySimpleDropLootTable(dropItem, registries));
        }
    }

    private static LootPool initEntitySimpleDropLootTable(ItemLike dropItem, HolderLookup.Provider registries) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(dropItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SmeltItemFunction.smelted().when(shouldSmeltLoot(registries)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries,
                                        UniformGenerator.between(0.0F, 1.0F)))).build();
    }

    private static AnyOfCondition.Builder shouldSmeltLoot(HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(net.minecraft.advancements.critereon.EntityFlagsPredicate.Builder.flags().setOnFire(true))), LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().equipment(net.minecraft.advancements.critereon.EntityEquipmentPredicate.Builder.equipment().mainhand(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(registrylookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))))))));
    }

    private static ResourceKey<?> genEntityDrops(EntityType<?> type) {
        return type.getDefaultLootTable();
    }
}
