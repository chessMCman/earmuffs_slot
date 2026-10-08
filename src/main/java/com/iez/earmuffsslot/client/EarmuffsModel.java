package com.iez.earmuffsslot.client;

import com.iez.earmuffsslot.EarmuffsSlotMod;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * Simple headband + two ear cups rendered on the player's head. All geometry lives under the
 * humanoid "head" part, so {@code followBodyRotations} keeps it glued to the wearer's head.
 */
public class EarmuffsModel extends HumanoidModel<LivingEntity> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(EarmuffsSlotMod.MODID, "earmuffs"), "main");

    public EarmuffsModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        head.addOrReplaceChild("band",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.5F, -4.0F, 9.0F, 1.5F, 8.0F),
                PartPose.ZERO);
        head.addOrReplaceChild("left_cup",
                CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -6.5F, -2.5F, 2.0F, 4.0F, 5.0F),
                PartPose.ZERO);
        head.addOrReplaceChild("right_cup",
                CubeListBuilder.create().texOffs(0, 32).addBox(4.0F, -6.5F, -2.5F, 2.0F, 4.0F, 5.0F),
                PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 64);
    }
}