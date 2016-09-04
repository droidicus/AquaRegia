package droidicus.aquaregia.item;

import droidicus.aquaregia.AquaRegia;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

/**
 * An axe that loses durability when used in crafting recipes
 *
 * @author Choonster
 */
public class ItemCuttingAxe extends ItemAxe {

	public ItemCuttingAxe(ToolMaterial material, String itemName) {
		super(material);
		ItemAquaRegia.setItemName(this, itemName);
		setCreativeTab(AquaRegia.creativeTab);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		stack = stack.copy();
		stack.attemptDamageItem(1, itemRand);
		return stack;
	}
}
