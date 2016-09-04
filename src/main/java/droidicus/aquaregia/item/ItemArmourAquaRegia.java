package droidicus.aquaregia.item;

import droidicus.aquaregia.AquaRegia;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for this mod's armour items
 *
 * @author Choonster
 */
public class ItemArmourAquaRegia extends ItemArmor {
	private static final Map<EntityEquipmentSlot, String> SLOT_NAMES;

	static {
		final Map<EntityEquipmentSlot, String> slotNames = new HashMap<>();
		slotNames.put(EntityEquipmentSlot.HEAD, "helmet");
		slotNames.put(EntityEquipmentSlot.CHEST, "chestplate");
		slotNames.put(EntityEquipmentSlot.LEGS, "leggings");
		slotNames.put(EntityEquipmentSlot.FEET, "boots");

		SLOT_NAMES = Maps.immutableEnumMap(slotNames);
	}


	public ItemArmourAquaRegia(final ArmorMaterial material, final EntityEquipmentSlot equipmentSlot, final String armourName) {
		super(material, -1, equipmentSlot);

		Preconditions.checkArgument(SLOT_NAMES.containsKey(equipmentSlot), "Invalid slot %s", equipmentSlot);

		ItemAquaRegia.setItemName(this, armourName + "_" + SLOT_NAMES.get(equipmentSlot));
		setCreativeTab(AquaRegia.creativeTab);
	}
}
