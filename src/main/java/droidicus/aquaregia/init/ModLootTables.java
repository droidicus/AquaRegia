package droidicus.aquaregia.init;

import droidicus.aquaregia.AquaRegia;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Registers this mod's {@link LootTable}s.
 *
 * @author Choonster
 */
public class ModLootTables {
//	public static final ResourceLocation LOOT_TABLE_TEST = register("loot_table_test");

	/**
	 * Register a {@link LootTable} with the specified ID.
	 *
	 * @param id The ID of the LootTable without the aquaregia: prefix
	 * @return The ID of the LootTable
	 */
	private static ResourceLocation register(String id) {
		return LootTableList.register(new ResourceLocation(AquaRegia.MODID, id));
	}
}
