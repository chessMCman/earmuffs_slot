package com.iez.earmuffsslot;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;

/**
 * Adds a dedicated Curios slot ("earmuffs") for Immersive Engineering's Ear Defenders
 * so they can be worn at the same time as a helmet.
 */
@Mod(EarmuffsSlotMod.MODID)
public class EarmuffsSlotMod {

    public static final String MODID = "earmuffs_slot";
    public static final Logger LOGGER = LogUtils.getLogger();

    /** Registry name of the Immersive Engineering Ear Defenders item. */
    public static final ResourceLocation IE_EARMUFFS =
            ResourceLocation.fromNamespaceAndPath("immersiveengineering", "earmuffs");

    public EarmuffsSlotMod(IEventBus modEventBus) {
        // The ear defenders are registered by IE (loaded before us), so they are available here.
        BuiltInRegistries.ITEM.getOptional(IE_EARMUFFS).ifPresentOrElse(
                item -> {
                    CuriosApi.registerCurio(item, new EarmuffsCurio());
                    LOGGER.info("Registered Immersive Engineering Ear Defenders in the 'earmuffs' Curios slot.");
                },
                () -> LOGGER.warn("Immersive Engineering Ear Defenders not found; the earmuffs slot will stay empty.")
        );

        if (FMLEnvironment.dist.isClient()) {
            com.iez.earmuffsslot.client.ClientSetup.register(modEventBus);
        }
    }
}