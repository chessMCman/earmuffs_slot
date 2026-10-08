package com.iez.earmuffsslot.client;

import com.iez.earmuffsslot.EarmuffsSlotMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

/**
 * Renders the earmuffs on the wearer's head while equipped in a Curios slot.
 */
public class EarmuffsCurioRenderer implements ICurioRenderer {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(EarmuffsSlotMod.MODID, "textures/models/earmuffs.png");

    private EarmuffsModel model;

    private EarmuffsModel model() {
        if (this.model == null) {
            this.model = new EarmuffsModel(
                    Minecraft.getInstance().getEntityModels().bakeLayer(EarmuffsModel.LAYER));
        }
        return this.model;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack, SlotContext slotContext, PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent, MultiBufferSource bufferSource,
            int light, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch) {

        EarmuffsModel model = model();
        LivingEntity entity = slotContext.entity();

        // Copy the wearer's body/head pose into our model so it tracks the head.
        ICurioRenderer.followBodyRotations(entity, model);

        poseStack.pushPose();
        model.head.render(poseStack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)),
                light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}