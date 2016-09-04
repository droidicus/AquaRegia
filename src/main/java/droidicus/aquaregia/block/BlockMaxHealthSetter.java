package droidicus.aquaregia.block;

import droidicus.aquaregia.api.capability.maxhealth.IMaxHealth;
import droidicus.aquaregia.capability.maxhealth.CapabilityMaxHealth;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * A block that adds or removes max health from players who right click it using the {@link IMaxHealth} capability.
 *
 * @author Choonster
 */
public class BlockMaxHealthSetter extends BlockAquaRegia {
	public BlockMaxHealthSetter() {
		super(Material.IRON, "max_health_setter");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			final IMaxHealth maxHealth = CapabilityMaxHealth.getMaxHealth(playerIn);

			final float healthToAdd = playerIn.isSneaking() ? -1.0f : 1.0f;
			maxHealth.addBonusMaxHealth(healthToAdd);

			playerIn.addChatMessage(new TextComponentTranslation("message.aquaregia:max_health.add", playerIn.getDisplayName(), healthToAdd));
		}

		return true;
	}
}
