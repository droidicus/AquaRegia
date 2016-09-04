package droidicus.aquaregia.client.model;

import droidicus.aquaregia.block.BlockColouredSlab;
import droidicus.aquaregia.block.BlockVariants;
import droidicus.aquaregia.init.ModBlocks;
import droidicus.aquaregia.init.ModFluids;
import droidicus.aquaregia.init.ModItems;
import droidicus.aquaregia.item.ItemVariants;
import droidicus.aquaregia.util.Constants;
import droidicus.aquaregia.util.IVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

@SideOnly(Side.CLIENT)
public class ModModelManager {
	public static final ModModelManager INSTANCE = new ModModelManager();

	private static final String FLUID_MODEL_PATH = Constants.RESOURCE_PREFIX + "fluid";

	private ModModelManager() {
	}

	public void registerAllModels() {
		registerFluidModels();
		registerBlockModels();
		registerItemModels();
	}

	private void registerFluidModels() {
		ModFluids.MOD_FLUID_BLOCKS.forEach(this::registerFluidModel);
	}

	private void registerFluidModel(IFluidBlock fluidBlock) {
		final Item item = Item.getItemFromBlock((Block) fluidBlock);
		assert item != null;

		ModelBakery.registerItemVariants(item);

		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));

		ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});

		itemsRegistered.add(item);
	}

	private void registerBlockModels() {
//		ModelLoader.setCustomStateMapper(ModBlocks.WATER_GRASS, new StateMap.Builder().ignore(BlockLiquid.LEVEL).build());
//
//		registerBlockItemModel(ModBlocks.RIGHT_CLICK_TEST, new ModelResourceLocation(ModBlocks.RIGHT_CLICK_TEST.getRegistryName(), "has_ender_eye=false"));
//
//		for (final EnumDyeColor color : EnumDyeColor.values()) {
//			registerBlockItemModelForMeta(ModBlocks.COLORED_ROTATABLE, color.getMetadata(), String.format("color=%s,facing=north", color.getName()));
//			registerBlockItemModelForMeta(ModBlocks.COLORED_MULTI_ROTATABLE, color.getMetadata(), String.format("color=%s,face_rotation=up,facing=north", color.getName()));
//
//			final BlockColouredSlab.EnumColourGroup colourGroup = BlockColouredSlab.EnumColourGroup.getGroupForColour(color);
//			if (colourGroup != null) {
//				registerBlockItemModelForMeta(ModBlocks.STAINED_CLAY_SLABS.getSlabGroupByColourGroup(colourGroup).singleSlab, colourGroup.getOffsetMetadata(color), String.format("colour=%s,half=bottom", color.getName()));
//			}
//		}
//
//		registerVariantBlockItemModels(ModBlocks.VARIANTS, "variant", BlockVariants.EnumType.values());
//
//		registerBlockItemModel(ModBlocks.MIRROR_PLANE, new ModelResourceLocation(ModBlocks.MIRROR_PLANE.getRegistryName(), "horizontal_rotation=north,vertical_rotation=up"));
//		registerBlockItemModel(ModBlocks.CHEST, new ModelResourceLocation(ModBlocks.CHEST.getRegistryName(), "facing=north"));
//
//		ModBlocks.BLOCKS.stream().filter(block -> !itemsRegistered.contains(Item.getItemFromBlock(block))).forEach(this::registerBlockItemModel);
	}

	private void registerBlockItemModel(Block block) {
		final Item item = Item.getItemFromBlock(block);
		if (item != null) {
			registerItemModel(item);
		}
	}

	private void registerBlockItemModel(Block block, String modelLocation) {
		final Item item = Item.getItemFromBlock(block);
		if (item != null) {
			registerItemModel(item, modelLocation);
		}
	}

	private <T extends IVariant> void registerVariantBlockItemModels(Block block, String variantName, T[] variants) {
		final Item item = Item.getItemFromBlock(block);
		if (item != null) {
			registerVariantItemModels(item, variantName, variants);
		}
	}

	private void registerBlockItemModel(Block block, ModelResourceLocation fullModelLocation) {
		final Item item = Item.getItemFromBlock(block);
		if (item != null) {
			registerItemModel(item, fullModelLocation);
		}
	}

	private void registerBlockItemModelForMeta(Block block, int metadata, String variant) {
		final Item item = Item.getItemFromBlock(block);
		if (item != null) {
			registerItemModelForMeta(item, metadata, variant);
		}
	}

	private final Set<Item> itemsRegistered = new HashSet<>();

	private void registerItemModels() {
//		// Register items with custom model names first
//		registerItemModel(ModItems.SNOWBALL_LAUNCHER, "minecraft:fishing_rod");
//		registerItemModel(ModItems.UNICODE_TOOLTIPS, "minecraft:rabbit");
//		registerItemModel(ModItems.SWAP_TEST_A, "minecraft:brick");
//		registerItemModel(ModItems.SWAP_TEST_B, "minecraft:netherbrick");
//		registerItemModel(ModItems.BLOCK_DEBUGGER, "minecraft:nether_star");
//		registerItemModel(ModItems.WOODEN_HARVEST_SWORD, "minecraft:wooden_sword");
//		registerItemModel(ModItems.DIAMOND_HARVEST_SWORD, "minecraft:diamond_sword");
//		registerItemModel(ModItems.CLEARER, "minecraft:nether_star");
//		registerItemModel(ModItems.HEIGHT_TESTER, "minecraft:compass");
//		registerItemModel(ModItems.HEAVY, "minecraft:brick");
//		registerItemModel(ModItems.ENTITY_TEST, "minecraft:porkchop");
//		registerItemModel(ModItems.BLOCK_DESTROYER, "minecraft:tnt_minecart");
//		registerItemModel(ModItems.REPLACEMENT_HELMET, "minecraft:chainmail_helmet");
//		registerItemModel(ModItems.REPLACEMENT_CHESTPLATE, "minecraft:chainmail_chestplate");
//		registerItemModel(ModItems.REPACEMENT_LEGGINGS, "minecraft:chainmail_leggings");
//		registerItemModel(ModItems.REPLACEMENT_BOOTS, "minecraft:chainmail_boots");
//		registerItemModel(ModItems.PIG_SPAWNER_FINITE, "minecraft:porkchop");
//		registerItemModel(ModItems.PIG_SPAWNER_INFINITE, "minecraft:porkchop");
//		registerItemModel(ModItems.RESPAWNER, "minecraft:clock");
//		registerItemModel(ModItems.LOOT_TABLE_TEST, "minecraft:gold_ingot");
//		registerItemModel(ModItems.SADDLE, "minecraft:saddle");
//		registerItemModel(ModItems.WOODEN_SLOW_SWORD, "minecraft:wooden_sword");
//		registerItemModel(ModItems.DIAMOND_SLOW_SWORD, "minecraft:diamond_sword");
//
//		registerVariantItemModels(ModItems.VARIANTS, "variant", ItemVariants.EnumType.values());

		// Then register items with default model names
		ModItems.ITEMS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::registerItemModel);
	}

	private void registerItemModel(Item item) {
		registerItemModel(item, item.getRegistryName().toString());
	}

	private void registerItemModel(Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		registerItemModel(item, fullModelLocation);
	}

	private void registerItemModel(Item item, ModelResourceLocation fullModelLocation) {
		ModelBakery.registerItemVariants(item, fullModelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
		registerItemModel(item, MeshDefinitionFix.create(stack -> fullModelLocation));
	}

	private void registerItemModel(Item item, ItemMeshDefinition meshDefinition) {
		itemsRegistered.add(item);
		ModelLoader.setCustomMeshDefinition(item, meshDefinition);
	}

	private <T extends IVariant> void registerVariantItemModels(Item item, String variantName, T[] variants) {
		for (T variant : variants) {
			registerItemModelForMeta(item, variant.getMeta(), variantName + "=" + variant.getName());
		}
	}

	private void registerItemModelForMeta(Item item, int metadata, String variant) {
		registerItemModelForMeta(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private void registerItemModelForMeta(Item item, int metadata, ModelResourceLocation modelResourceLocation) {
		itemsRegistered.add(item);
		ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
	}
}
