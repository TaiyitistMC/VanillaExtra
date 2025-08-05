package com.taiyitistmc.vanillaextra.init;

import com.taiyitistmc.vanillaextra.VanillaExtra;
import com.taiyitistmc.vanillaextra.entity.BlackDog;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, VanillaExtra.MODID);

    public static final DeferredHolder<EntityType<? extends Entity>, EntityType<BlackDog>> BLACK_DOG =
            register("black_dog", EntityType.Builder.of(BlackDog::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.85F)
                    .eyeHeight(0.68F)
                    .passengerAttachments(new Vec3(0.0, 0.81875, -0.0625))
                    .clientTrackingRange(10));

    public static <E extends Entity> DeferredHolder<EntityType<? extends Entity>, EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> DeferredHolder<EntityType<? extends Entity>, EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(VanillaExtra.MODID + ":" + id);
        });
    }
}
