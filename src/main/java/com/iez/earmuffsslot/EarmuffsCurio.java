package com.iez.earmuffsslot;

import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Curio behavior for the Immersive Engineering Ear Defenders.
 *
 * <p>The item is restricted to the "earmuffs" slot by the {@code curios:tag} validator on the
 * slot definition, which checks membership in the {@code curios:earmuffs} item tag.
 */
public class EarmuffsCurio implements ICurioItem {

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        // Allow right-clicking the item to auto-equip it into the earmuffs slot.
        return true;
    }
}