package droidicus.aquaregia.item;

import droidicus.aquaregia.capability.SimpleCapabilityProvider;
import droidicus.aquaregia.capability.hiddenblockrevealer.CapabilityHiddenBlockRevealer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * An item that reveals hidden blocks.
 *
 * @author Choonster
 */
public class ItemHiddenBlockRevealer extends ItemAquaRegia {
	public ItemHiddenBlockRevealer() {
		super("hidden_block_revealer");
		CapabilityHiddenBlockRevealer.RevealHiddenBlocksGetter.addToItem(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			final Boolean revealHiddenBlocks = CapabilityHiddenBlockRevealer.toggleRevealHiddenBlocks((EntityPlayerMP) playerIn, hand);

			if (revealHiddenBlocks != null) {
				final String message = revealHiddenBlocks ? "message.aquaregia:hidden_block_revealer.reveal" : "message.aquaregia:hidden_block_revealer.hide";
				playerIn.addChatComponentMessage(new TextComponentTranslation(message));
			}
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new SimpleCapabilityProvider<>(CapabilityHiddenBlockRevealer.HIDDEN_BLOCK_REVEALER_CAPABILITY, CapabilityHiddenBlockRevealer.DEFAULT_FACING);
	}
}
