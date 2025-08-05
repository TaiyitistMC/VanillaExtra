package com.taiyitistmc.vanillaextra.entity.ai.goal;

import com.taiyitistmc.vanillaextra.entity.FriendlyZombie;
import com.taiyitistmc.vanillaextra.init.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class FriendlyZombieFollowCaravanGoal extends Goal {

    public final FriendlyZombie zombie;
    private double speedModifier;
    private static final int CARAVAN_LIMIT = 8;
    private int distCheckCounter;

    public FriendlyZombieFollowCaravanGoal(FriendlyZombie zombie, double speedModifier) {
        this.zombie = zombie;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.zombie.isLeashed() && !this.zombie.inCaravan()) {
            List<Entity> list = this.zombie.level().getEntities(this.zombie, this.zombie.getBoundingBox().inflate(9.0, 4.0, 9.0), (p_25505_) -> {
                EntityType<?> entitytype = p_25505_.getType();
                return entitytype == ModEntities.FRIENDLY_ZOMBIE.get();
            });
            FriendlyZombie zombie1 = null;
            double d0 = Double.MAX_VALUE;
            Iterator<Entity> var5 = list.iterator();

            Entity entity1;
            FriendlyZombie zombie2;
            double d2;
            while(var5.hasNext()) {
                entity1 = (Entity)var5.next();
                zombie2 = (FriendlyZombie)entity1;
                if (zombie2.inCaravan() && !zombie2.hasCaravanTail()) {
                    d2 = this.zombie.distanceToSqr(zombie2);
                    if (!(d2 > d0)) {
                        d0 = d2;
                        zombie1 = zombie2;
                    }
                }
            }

            if (zombie1 == null) {
                var5 = list.iterator();

                while(var5.hasNext()) {
                    entity1 = (Entity)var5.next();
                    zombie2 = (FriendlyZombie)entity1;
                    if (zombie2.isLeashed() && !zombie2.hasCaravanTail()) {
                        d2 = this.zombie.distanceToSqr(zombie2);
                        if (!(d2 > d0)) {
                            d0 = d2;
                            zombie1 = zombie2;
                        }
                    }
                }
            }

            if (zombie1 == null) {
                return false;
            } else if (d0 < 4.0) {
                return false;
            } else if (!zombie1.isLeashed() && !this.firstIsLeashed(zombie1, 1)) {
                return false;
            } else {
                this.zombie.joinCaravan(zombie1);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.zombie.inCaravan() && this.zombie.getCaravanHead().isAlive() && this.firstIsLeashed(this.zombie, 0)) {
            double d0 = this.zombie.distanceToSqr(this.zombie.getCaravanHead());
            if (d0 > 676.0) {
                if (this.speedModifier <= 3.0) {
                    this.speedModifier *= 1.2;
                    this.distCheckCounter = reducedTickDelay(40);
                    return true;
                }

                if (this.distCheckCounter == 0) {
                    return false;
                }
            }

            if (this.distCheckCounter > 0) {
                --this.distCheckCounter;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void stop() {
        this.zombie.leaveCaravan();
        this.speedModifier = 2.1;
    }

    @Override
    public void tick() {
        if (this.zombie.inCaravan() && !(this.zombie.getLeashHolder() instanceof LeashFenceKnotEntity)) {
            FriendlyZombie zombie1 = this.zombie.getCaravanHead();
            double d0 = (double)this.zombie.distanceTo(zombie1);
            float f = 2.0F;
            Vec3 vec3 = (new Vec3(zombie1.getX() - this.zombie.getX(), zombie1.getY() - this.zombie.getY(), zombie1.getZ() - this.zombie.getZ())).normalize().scale(Math.max(d0 - 2.0, 0.0));
            this.zombie.getNavigation().moveTo(this.zombie.getX() + vec3.x, this.zombie.getY() + vec3.y, this.zombie.getZ() + vec3.z, this.speedModifier);
        }

    }

    private boolean firstIsLeashed(FriendlyZombie zombie, int leashedQueuePosition) {
        if (leashedQueuePosition > 8) {
            return false;
        } else if (zombie.inCaravan()) {
            boolean var10000;
            if (zombie.getCaravanHead().isLeashed()) {
                var10000 = true;
            } else {
                FriendlyZombie var10001 = zombie.getCaravanHead();
                ++leashedQueuePosition;
                var10000 = this.firstIsLeashed(var10001, leashedQueuePosition);
            }

            return var10000;
        } else {
            return false;
        }
    }
}
