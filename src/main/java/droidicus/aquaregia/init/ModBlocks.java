package droidicus.aquaregia.init;

import droidicus.aquaregia.tileentity.*;
import droidicus.aquaregia.util.Constants;
import droidicus.aquaregia.block.BlockSlabAquaRegia;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class ModBlocks {

	public static final Set<Block> BLOCKS = new HashSet<>();

	static {

	}

	public static void registerBlocks() {
		// Dummy method to make sure the static initialiser runs
	}

	/**
	 * Register a Block with the default ItemBlock class.
	 *
	 * @param block   The Block instance
	 * @param <BLOCK> The Block type
	 * @return The Block instance
	 */
	protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block) {
		return registerBlock(block, ItemBlock::new);
	}

	/**
	 * Register a Block with a custom ItemBlock class.
	 *
	 * @param <BLOCK>     The Block type
	 * @param block       The Block instance
	 * @param itemFactory A function that creates the ItemBlock instance, or null if no ItemBlock should be created
	 * @return The Block instance
	 */
	protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block, @Nullable Function<BLOCK, ItemBlock> itemFactory) {
		GameRegistry.register(block);

		if (itemFactory != null) {
			final ItemBlock itemBlock = itemFactory.apply(block);

			GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
		}

		BLOCKS.add(block);
		return block;
	}

	/**
	 * Register a group of slab blocks
	 *
	 * @param slabGroup The slab group
	 */
	@SuppressWarnings("unchecked")
	private static <
			VARIANT extends Enum<VARIANT> & IStringSerializable,
			VARIANTS extends Iterable<VARIANT> & IStringSerializable,
			SLAB extends BlockSlabAquaRegia<VARIANT, VARIANTS, SLAB>
			> void registerSlabGroup(BlockSlabAquaRegia.SlabGroup<VARIANT, VARIANTS, SLAB> slabGroup) {
		registerBlock(slabGroup.singleSlab, slab -> new ItemSlab(slab, slabGroup.singleSlab, slabGroup.doubleSlab));
		registerBlock(slabGroup.doubleSlab, null); // No item form for the double slab
		slabGroup.setItem((ItemSlab) Item.getItemFromBlock(slabGroup.singleSlab));
	}

	public static void registerTileEntities() {
//		registerTileEntity(TileEntityColoredRotatable.class);
//		registerTileEntity(TileEntityColoredMultiRotatable.class);
//		registerTileEntity(TileEntityPotionEffect.class);
//		registerTileEntity(TileEntityModChest.class);
	}

	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
//		GameRegistry.registerTileEntity(tileEntityClass, Constants.RESOURCE_PREFIX + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));
	}
}
