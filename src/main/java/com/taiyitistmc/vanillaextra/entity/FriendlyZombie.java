package com.taiyitistmc.vanillaextra.entity;

import com.taiyitistmc.vanillaextra.entity.ai.goal.FriendlyZombieFollowCaravanGoal;
import com.taiyitistmc.vanillaextra.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FriendlyZombie extends AbstractChestedHorse {

    @Nullable
    private FriendlyZombie caravanHead;
    @Nullable
    private FriendlyZombie caravanTail;

    public static FriendlyZombie createZombie(Level level) {
        return new FriendlyZombie(ModEntities.FRIENDLY_ZOMBIE.get(), level);
    }

    public FriendlyZombie(EntityType<? extends AbstractChestedHorse> entityType, Level level) {
        super(entityType, level);
        setTamed(true);
        this.setChest(true);
        this.createInventory();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0).add(Attributes.MOVEMENT_SPEED, 0.30000001192092896).add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.ARMOR, 2.0).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0, FriendlyZombie.class));
        this.goalSelector.addGoal(2, new FriendlyZombieFollowCaravanGoal(this, 2.0999999046325684));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.25, (itemStack) -> itemStack.is(Items.ROTTEN_FLESH), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.0));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.isFood(itemstack)) {
            int i = this.getAge();
            if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
                this.usePlayerItem(player, hand, itemstack);
                this.setInLove(player);
                return InteractionResult.SUCCESS;
            }

            if (this.isBaby()) {
                this.usePlayerItem(player, hand, itemstack);
                this.ageUp(getSpeedUpSecondsWhenFeeding(-i), true);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }
        }
        if (player.isSecondaryUseActive()) {
            this.openCustomInventoryScreen(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }else {
            return InteractionResult.SUCCESS;
        }
    }

    public void leaveCaravan() {
        if (this.caravanHead != null) {
            this.caravanHead.caravanTail = null;
        }

        this.caravanHead = null;
    }

    public void joinCaravan(FriendlyZombie caravanHead) {
        this.caravanHead = caravanHead;
        this.caravanHead.caravanTail = this;
    }

    public boolean hasCaravanTail() {
        return this.caravanTail != null;
    }

    public boolean inCaravan() {
        return this.caravanHead != null;
    }

    @Nullable
    public FriendlyZombie getCaravanHead() {
        return this.caravanHead;
    }

    @Override
    protected int getBaseExperienceReward() {
        if (this.isBaby()) {
            this.xpReward = (int)((double)this.xpReward * 2.5);
        }

        return super.getBaseExperienceReward();
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.ROTTEN_FLESH);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.FRIENDLY_ZOMBIE.get().create(serverLevel);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity);
    }

    @Override
    protected void dropEquipment() {
        if (this.hasChest()) {
            if (!this.level().isClientSide) {
                if (this.inventory != null) {
                    for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                        ItemStack itemstack = this.inventory.getItem(i);
                        if (!itemstack.isEmpty() && !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
                            this.spawnAtLocation(itemstack);
                        }
                    }
                }
            }

            this.setChest(false);
        }
    }
}
