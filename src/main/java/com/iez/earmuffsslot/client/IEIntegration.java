package com.iez.earmuffsslot.client;

import blusunrize.immersiveengineering.common.items.EarmuffsItem;
import com.iez.earmuffsslot.EarmuffsSlotMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

/**
 * Bridges the Curios "earmuffs" slot into Immersive Engineering's existing ear-defender sound
 * dampening.
 *
 * <p>IE consults {@link EarmuffsItem#EARMUFF_GETTERS} every client tick to locate the ear defenders
 * a player is wearing, then drives its {@code SoundEngineMixin} / {@code EarmuffHandler} volume
 * reduction from that stack's own per-category configuration. Registering this getter makes a pair
 * of ear defenders worn in our Curios slot participate in that system unchanged, so the item's
 * noise-gate and per-category settings keep working exactly as configured in the workbench.
 */
public final class IEIntegration {

    private IEIntegration() {
    }

    public static void registerEarmuffGetter() {
        EarmuffsItem.EARMUFF_GETTERS.addGetter(IEIntegration::getFromCuriosSlot);
    }

    private static ItemStack getFromCuriosSlot(LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            return CuriosApi.getCuriosInventory(player)
                    .flatMap(handler -> handler.findCurio("earmuffs", 0))
                    .filter(result -> isEarDefenders(result.stack()))
                    .map(SlotResult::stack)
                    .orElse(ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    private static boolean isEarDefenders(ItemStack stack) {
        return !stack.isEmpty()
                && EarmuffsSlotMod.IE_EARMUFFS.equals(BuiltInRegistries.ITEM.getKey(stack.getItem()));
    }
}