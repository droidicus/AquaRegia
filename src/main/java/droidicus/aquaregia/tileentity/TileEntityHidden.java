package droidicus.aquaregia.tileentity;

import droidicus.aquaregia.block.BlockHidden;
import droidicus.aquaregia.capability.hiddenblockrevealer.HiddenBlockManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Runs client-side updates for {@link BlockHidden}.
 *
 * @author Choonster
 */
public class TileEntityHidden extends TileEntity implements ITickable {

	@Override
	public void update() {
		if (worldObj.isRemote) {
			HiddenBlockManager.refresh(worldObj, pos);
		}
	}
}
