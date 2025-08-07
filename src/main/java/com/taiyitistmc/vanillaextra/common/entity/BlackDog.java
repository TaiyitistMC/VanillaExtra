package com.taiyitistmc.vanillaextra.common.entity;

import com.taiyitistmc.vanillaextra.common.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public class BlackDog extends Wolf {

    public BlackDog(EntityType<? extends Wolf> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.30000001192092896).add(Attributes.MAX_HEALTH, 8.0).add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity);
    }

    public static boolean checkBlackDogSpawnRules(EntityType<BlackDog> blackDog, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.WOLVES_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
    }

    @Override
    public @Nullable BlackDog getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        BlackDog blackDog = ModEntities.BLACK_DOG.get().create(level);
        if (blackDog != null && otherParent instanceof Wolf wolf1) {
            if (this.random.nextBoolean()) {
                blackDog.setVariant(this.getVariant());
            } else {
                blackDog.setVariant(wolf1.getVariant());
            }

            if (this.isTame()) {
                blackDog.setOwnerUUID(this.getOwnerUUID());
                blackDog.setTame(true, true);
                if (this.random.nextBoolean()) {
                    blackDog.setCollarColor(this.getCollarColor());
                } else {
                    blackDog.setCollarColor(wolf1.getCollarColor());
                }
            }
        }

        return blackDog;
    }
}
