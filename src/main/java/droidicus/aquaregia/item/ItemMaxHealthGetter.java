package droidicus.aquaregia.item;

import droidicus.aquaregia.api.capability.maxhealth.IMaxHealth;
import droidicus.aquaregia.capability.maxhealth.CapabilityMaxHealth;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * An item that tells the player the current max health and the bonus max health provided by the entity's {@link IMaxHealth} when right on an entity.
 *
 * @author Choonster
 */
public class ItemMaxHealthGetter extends ItemAquaRegia {
	public ItemMaxHealthGetter() {
		super("max_health_getter_item");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.worldObj.isRemote) {
			final IMaxHealth maxHealth = CapabilityMaxHealth.getMaxHealth(target);

			playerIn.addChatMessage(new TextComponentTranslation("message.aquaregia:max_health.get", target.getDisplayName(), target.getMaxHealth(), maxHealth.getBonusMaxHealth()));
		}

		return true;
	}
}
