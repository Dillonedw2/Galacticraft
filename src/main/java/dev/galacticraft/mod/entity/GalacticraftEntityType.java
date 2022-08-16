/*
 * Copyright (c) 2019-2022 Team Galacticraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.mod.entity;

import dev.galacticraft.api.entity.attribute.GcApiEntityAttributes;
import dev.galacticraft.mod.Constant;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * @author <a href="https://github.com/TeamGalacticraft">TeamGalacticraft</a>
 */
public class GalacticraftEntityType {
    public static final EntityType<EvolvedZombieEntity> EVOLVED_ZOMBIE = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build();
    public static final EntityType<EvolvedCreeperEntity> EVOLVED_CREEPER =  FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedCreeperEntity::new).dimensions(EntityDimensions.scalable(0.65F, 1.8F)).build();
    public static final EntityType<EvolvedSkeletonEntity> EVOLVED_SKELETON = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.99F)).build();
    public static final EntityType<EvolvedSpiderEntity> EVOLVED_SPIDER = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedSpiderEntity::new).dimensions(EntityDimensions.fixed(1.4F, 0.9F)).build();
    public static final EntityType<EvolvedPillagerEntity> EVOLVED_PILLAGER = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedPillagerEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build();
    public static final EntityType<EvolvedEvokerEntity> EVOLVED_EVOKER = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedEvokerEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build();
    public static final EntityType<EvolvedVindicatorEntity> EVOLVED_VINDICATOR = FabricEntityTypeBuilder.create(MobCategory.MONSTER, EvolvedVindicatorEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build();
    public static final EntityType<BubbleEntity> BUBBLE = FabricEntityTypeBuilder.create(MobCategory.MISC, BubbleEntity::new).fireImmune().dimensions(EntityDimensions.fixed(0, 0)).disableSaving().disableSummon().build();
    public static final EntityType<RocketEntity> ROCKET = FabricEntityTypeBuilder.create(SpawnGroup.MISC, RocketEntity::new).trackable(32, 2, false).dimensions(EntityDimensions.fixed(2.3F, 5.25F)).build(); //PLAYER VALUES

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_ZOMBIE), EVOLVED_ZOMBIE);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_CREEPER), EVOLVED_CREEPER);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_SKELETON), EVOLVED_SKELETON);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_SPIDER), EVOLVED_SPIDER);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_PILLAGER), EVOLVED_PILLAGER);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_EVOKER), EVOLVED_EVOKER);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.EVOLVED_VINDICATOR), EVOLVED_VINDICATOR);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.BUBBLE), BUBBLE);
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constant.MOD_ID, Constant.Entity.ROCKET), ROCKET);

        FabricDefaultAttributeRegistry.register(EVOLVED_ZOMBIE, EvolvedZombieEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.MAX_HEALTH, 30.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_CREEPER, EvolvedCreeperEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_SKELETON, EvolvedSkeletonEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MAX_HEALTH, 25.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_SPIDER, EvolvedSpiderEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MAX_HEALTH, 22.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_PILLAGER, EvolvedPillagerEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MAX_HEALTH, 25.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_EVOKER, EvolvedEvokerEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MAX_HEALTH, 25.0D));
        FabricDefaultAttributeRegistry.register(EVOLVED_VINDICATOR, EvolvedVindicatorEntity.createAttributes().add(GcApiEntityAttributes.CAN_BREATHE_IN_SPACE, 1.0D).add(Attributes.MAX_HEALTH, 25.0D));
    }
}
