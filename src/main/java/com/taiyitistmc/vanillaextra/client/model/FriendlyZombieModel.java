package com.taiyitistmc.vanillaextra.client.model;

import com.taiyitistmc.vanillaextra.entity.FriendlyZombie;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlyZombieModel extends HumanoidModel<FriendlyZombie> {

    public FriendlyZombieModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(FriendlyZombie entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, false, this.attackTime, ageInTicks);
    }
}
