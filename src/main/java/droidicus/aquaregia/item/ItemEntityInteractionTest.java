package droidicus.aquaregia.item;

import droidicus.aquaregia.util.ItemStackUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * An item that records how many times it's used to right click an entity.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2451199-1-8-iteminteractionforentity-with-nbt-bug
 *
 * @author Choonster
 */
public class ItemEntityInteractionTest extends ItemAquaRegia {
	public ItemEntityInteractionTest() {
		super("entity_interaction_test");
	}

	private int getInteractCount(ItemStack stack) {
		return ItemStackUtils.getOrCreateTagCompound(stack).getInteger("Count");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.worldObj.isRemote) {
			final int count = getInteractCount(stack) + 1;
			stack.getTagCompound().setInteger("Count", count);

			playerIn.addChatComponentMessage(new TextComponentTranslation("message.aquaregia:entity_interact_count", count));
		}

		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.worldObj.isRemote) {
			final int count = getInteractCount(itemStackIn);

			playerIn.addChatComponentMessage(new TextComponentTranslation("message.aquaregia:entity_interact_count", count));
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
