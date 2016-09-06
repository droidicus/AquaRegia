package droidicus.aquaregia.tileentity;

import droidicus.aquaregia.inventory.itemhandler.ItemHandlerLoot;
import droidicus.aquaregia.util.IWorldContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;

/**
 * A {@link TileEntity} with a single {@link ItemHandlerLoot} inventory that generates its contents from a {@link LootTable} the first time it's accessed.
 *
 * @author Choonster
 */
public abstract class TileEntityItemHandlerLoot extends TileEntityItemHandler<ItemHandlerLoot> implements IWorldContainer {

	@Override
	public World getContainedWorld() {
		return getWorld();
	}
}
