package droidicus.aquaregia.item;

//import droidicus.aquaregia.AquaRegia;
//import droidicus.aquaregia.init.ModLootTables;
//import droidicus.aquaregia.network.MessagePlayerReceivedLoot;
import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
//import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
//import net.minecraft.world.WorldServer;
//import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
//import net.minecraftforge.items.ItemHandlerHelper;

//import java.util.List;

/**
 * Gives the player random loot from a {@link LootTable} when they right click.
 * <p>
 * Test for this thread:
 * http://www.minecraftforge.net/forum/index.php/topic,37969.0.html
 *
 * @author Choonster
 */
public class ItemLootTableTest extends ItemAquaRegia {
	public ItemLootTableTest() {
		super("loot_table_test");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
//			final LootTable lootTable = worldIn.getLootTableManager().getLootTableFromLocation(ModLootTables.LOOT_TABLE_TEST);
//
//			final LootContext lootContext = new LootContext.Builder((WorldServer) worldIn).withPlayer(playerIn).build();
//
//			final List<ItemStack> itemStacks = lootTable.generateLootForPools(itemRand, lootContext);
//			for (ItemStack itemStack : itemStacks) {
//				ItemHandlerHelper.giveItemToPlayer(playerIn, itemStack);
//			}
//
//			playerIn.inventoryContainer.detectAndSendChanges();
//
//			if (itemStacks.size() > 0) {
//				AquaRegia.network.sendTo(new MessagePlayerReceivedLoot(itemStacks), (EntityPlayerMP) playerIn);
//			} else {
//				playerIn.addChatComponentMessage(new TextComponentTranslation("message.aquaregia:player_received_loot.noLoot"));
//			}
		}


		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
