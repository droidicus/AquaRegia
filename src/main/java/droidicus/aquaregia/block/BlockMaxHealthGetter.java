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
 * A block that tells players who right click it their current max health and the bonus max health provided by their {@link IMaxHealth}.
 *
 * @author Choonster
 */
public class BlockMaxHealthGetter extends BlockAquaRegia {
	public BlockMaxHealthGetter() {
		super(Material.IRON, "max_health_getter");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			final IMaxHealth maxHealth = CapabilityMaxHealth.getMaxHealth(playerIn);

			playerIn.addChatMessage(new TextComponentTranslation("message.aquaregia:max_health.get", playerIn.getDisplayName(), playerIn.getMaxHealth(), maxHealth.getBonusMaxHealth()));
		}

		return true;
	}
}
