package droidicus.aquaregia.item;

import droidicus.aquaregia.api.capability.maxhealth.IMaxHealth;
import droidicus.aquaregia.capability.maxhealth.CapabilityMaxHealth;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * An item that adds or removes max health from entities right clicked with it using the {@link IMaxHealth} capability.
 *
 * @author Choonster
 */
public class ItemMaxHealthSetter extends ItemAquaRegia {
	public ItemMaxHealthSetter() {
		super("max_health_setter_item");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.worldObj.isRemote) {
			final IMaxHealth maxHealth = CapabilityMaxHealth.getMaxHealth(target);

			final float healthToAdd = playerIn.isSneaking() ? -1.0f : 1.0f;
			maxHealth.addBonusMaxHealth(healthToAdd);

			playerIn.addChatMessage(new TextComponentTranslation("message.aquaregia:max_health.add", target.getDisplayName(), healthToAdd));
		}

		return true;
	}
}
