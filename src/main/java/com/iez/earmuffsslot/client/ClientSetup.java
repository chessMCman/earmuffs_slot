package com.iez.earmuffsslot.client;

import com.iez.earmuffsslot.EarmuffsSlotMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

/**
 * Client-only model layer and renderer registration for the earmuffs slot.
 */
public final class ClientSetup {

    private ClientSetup() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ClientSetup::registerLayerDefinitions);
        modEventBus.addListener(ClientSetup::onClientSetup);
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EarmuffsModel.LAYER, EarmuffsModel::createBodyLayer);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        IEIntegration.registerEarmuffGetter();
        event.enqueueWork(() -> BuiltInRegistries.ITEM
                .getOptional(EarmuffsSlotMod.IE_EARMUFFS)
                .ifPresent(item -> CuriosRendererRegistry.register(item, EarmuffsCurioRenderer::new)));
    }
}