package com.taiyitistmc.vanillaextra.entity.ai.goal;

import com.taiyitistmc.vanillaextra.entity.GroupFollowAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class GroupFollowAnimalCaravanGoal extends Goal {

    public final GroupFollowAnimal animal;
    private double speedModifier;
    private static final int CARAVAN_LIMIT = 8;
    private int distCheckCounter;

    public GroupFollowAnimalCaravanGoal(GroupFollowAnimal animal, double speedModifier) {
        this.animal = animal;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.animal.isLeashed() && !this.animal.inCaravan()) {
            List<Entity> list = this.animal.level().getEntities(this.animal, this.animal.getBoundingBox().inflate(9.0, 4.0, 9.0), (p_25505_) -> {
                EntityType<?> entitytype = p_25505_.getType();
                return entitytype == animal.getType();
            });
            GroupFollowAnimal animal1 = null;
            double d0 = Double.MAX_VALUE;
            Iterator<Entity> var5 = list.iterator();

            Entity entity1;
            GroupFollowAnimal animal2;
            double d2;
            while(var5.hasNext()) {
                entity1 = (Entity)var5.next();
                animal2 = (GroupFollowAnimal)entity1;
                if (animal2.inCaravan() && !animal2.hasCaravanTail()) {
                    d2 = this.animal.distanceToSqr(animal2);
                    if (!(d2 > d0)) {
                        d0 = d2;
                        animal1 = animal2;
                    }
                }
            }

            if (animal1 == null) {
                var5 = list.iterator();

                while(var5.hasNext()) {
                    entity1 = (Entity)var5.next();
                    animal2 = (GroupFollowAnimal)entity1;
                    if (animal2.isLeashed() && !animal2.hasCaravanTail()) {
                        d2 = this.animal.distanceToSqr(animal2);
                        if (!(d2 > d0)) {
                            d0 = d2;
                            animal1 = animal2;
                        }
                    }
                }
            }

            if (animal1 == null) {
                return false;
            } else if (d0 < 4.0) {
                return false;
            } else if (!animal1.isLeashed() && !this.firstIsLeashed(animal1, 1)) {
                return false;
            } else {
                this.animal.joinCaravan(animal1);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.animal.inCaravan() && this.animal.getCaravanHead().isAlive() && this.firstIsLeashed(this.animal, 0)) {
            double d0 = this.animal.distanceToSqr(this.animal.getCaravanHead());
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
        this.animal.leaveCaravan();
        this.speedModifier = 2.1;
    }

    @Override
    public void tick() {
        if (this.animal.inCaravan() && !(this.animal.getLeashHolder() instanceof LeashFenceKnotEntity)) {
            GroupFollowAnimal animal1 = this.animal.getCaravanHead();
            double d0 = (double)this.animal.distanceTo(animal1);
            float f = 2.0F;
            Vec3 vec3 = (new Vec3(animal1.getX() - this.animal.getX(), animal1.getY() - this.animal.getY(), animal1.getZ() - this.animal.getZ())).normalize().scale(Math.max(d0 - 2.0, 0.0));
            this.animal.getNavigation().moveTo(this.animal.getX() + vec3.x, this.animal.getY() + vec3.y, this.animal.getZ() + vec3.z, this.speedModifier);
        }

    }

    private boolean firstIsLeashed(GroupFollowAnimal animal, int leashedQueuePosition) {
        if (leashedQueuePosition > 8) {
            return false;
        } else if (animal.inCaravan()) {
            boolean var10000;
            if (animal.getCaravanHead().isLeashed()) {
                var10000 = true;
            } else {
                GroupFollowAnimal var10001 = animal.getCaravanHead();
                ++leashedQueuePosition;
                var10000 = this.firstIsLeashed(var10001, leashedQueuePosition);
            }

            return var10000;
        } else {
            return false;
        }
    }
}
