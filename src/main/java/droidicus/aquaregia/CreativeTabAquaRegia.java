package droidicus.aquaregia;

//import droidicus.aquaregia.util.SwordUpgrades;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class CreativeTabAquaRegia extends CreativeTabs {
//	private final ItemStack sword;

	public CreativeTabAquaRegia() {
		super(AquaRegia.MODID);
//		sword = SwordUpgrades.upgradeSword(Items.STONE_SWORD);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem() {
		return Items.STONE_SWORD;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayAllRelevantItems(List<ItemStack> items) {
//		items.add(sword.copy());
		super.displayAllRelevantItems(items);
	}
}
