package droidicus.aquaregia.item;

import droidicus.aquaregia.AquaRegia;
import net.minecraft.item.Item;

/**
 * A base class for this mod's items.
 *
 * @author Choonster
 */
public class ItemAquaRegia extends Item {
    public ItemAquaRegia(String itemName) {
        setItemName(this, itemName);
        setCreativeTab(AquaRegia.creativeTab);
    }

    /**
     * Set the registry name of {@code item} to {@code itemName} and the unlocalised name to the full registry name.
     *
     * @param item     The item
     * @param itemName The item's name
     */
    public static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }
}
