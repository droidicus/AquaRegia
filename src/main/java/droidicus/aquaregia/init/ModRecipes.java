package droidicus.aquaregia.init;

import droidicus.aquaregia.Logger;
import droidicus.aquaregia.config.Config;
import droidicus.aquaregia.recipe.ShapelessNBTRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPELESS;

//import droidicus.aquaregia.recipe.ShapedArmourUpgradeRecipe;
//import droidicus.aquaregia.recipe.ShapelessCuttingRecipe;
//import net.minecraft.block.BlockPlanks;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.PotionTypes;
//import net.minecraft.item.crafting.RecipeFireworks;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.potion.PotionHelper;
//import net.minecraftforge.oredict.OreDictionary;
//import net.minecraftforge.oredict.ShapelessOreRecipe;
//import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPED;

/**
 * Adds and removes recipes.
 */
public class ModRecipes {
    /**
     * Add this mod's recipes.
     */
    public static void registerRecipes() {
        registerRecipeClasses();
        addCraftingRecipes();
//		addBrewingRecipes();
    }

    /**
     * Register this mod's recipe classes.
     */
    private static void registerRecipeClasses() {
//		RecipeSorter.register("aquaregia:shapelesscutting", ShapelessCuttingRecipe.class, SHAPELESS, "after:minecraft:shapeless");
//		RecipeSorter.register("aquaregia:shapedarmourupgrade", ShapedArmourUpgradeRecipe.class, SHAPED, "after:forge:shapedore before:minecraft:shapeless");
        RecipeSorter.register("aquaregia:shapelessnbt", ShapelessNBTRecipe.class, SHAPELESS, "after:forge:shapelessore");
    }

    /**
     * Add this mod's crafting recipes.
     */
    private static void addCraftingRecipes() {
        // TODO: Re-evaluate when Forge buckets correctly return an empty bucket from recipies https://github.com/MinecraftForge/MinecraftForge/pull/3234
        // TODO: Should we just use normal water from the beginning?
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.NEUTRAL),
                //Inputs
                Items.WATER_BUCKET
        ));

        // Water + the sulfur from gunpowder = Sulfuric Acid
        if (Config.enableGunpowderSulfur) {
            GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                    UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.H2SO4),
                    //Inputs
                    Items.GUNPOWDER,
                    Items.GUNPOWDER,
                    Items.GUNPOWDER,
                    UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.NEUTRAL)
            ));
        }

        // Water + sulfur = Sulfuric Acid
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.H2SO4),
                //Inputs
                "dustSulfur",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.NEUTRAL)
        ));

        // Sulfuric Acid + Salt = Hydrochloric Acid
        // Johann Rudolph Glauber process
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HCL),
                //Inputs
                "dustSalt",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.H2SO4)
        ));

        // Sulfuric Acid + Niter = Nitric Acid
        // Laboratory synthesis process
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HNO3),
                //Inputs
                "dustSaltpeter",
                "dustSaltpeter",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.H2SO4)
        ));

        // 3x Hydrochloric Acid + 1x Nitric Acid = Aqua Regia!
        // In Molar quantities
        //TODO: This loses 3 buckets, do we want it that way, or did the acid eat them??? https://github.com/MinecraftForge/MinecraftForge/pull/3234
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HNO3HCL),
                //Inputs
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HCL),
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HCL),
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HCL),
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HNO3)
        ));

        // Aqua Regia + Gold Ingots (configurable amount) = Chloroauric Acid
        // TODO: can this be less hacky?
        Object[] recipeIns = new Object[1 + Math.min(8, Config.goldPrecPerOre)];
        recipeIns[0] = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HNO3HCL);
        IntStream.rangeClosed(1, Math.min(8, Config.goldPrecPerOre)).forEach(i -> recipeIns[i] = "ingotGold");
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HAUCL4),
                //Inputs
                recipeIns
        ));

        // Aqua Regia + Gold Ore = Chloroauric Acid
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HAUCL4),
                //Inputs
                "oreGold",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HNO3HCL)
        ));

        // Chloroauric Acid + the sulfur from Gunpowder = Precipitated Gold (configurable amount)
        //TODO: This loses a bucket, did the acid eat it? https://github.com/MinecraftForge/MinecraftForge/pull/3234
        if (Config.enableGunpowderSulfur) {
            GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                    new ItemStack(ModItems.GOLDPRECIP, Config.goldPrecPerOre),
                    //Inputs
                    Items.GUNPOWDER,
                    Items.GUNPOWDER,
                    Items.GUNPOWDER,
                    UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HAUCL4)
            ));
        }

        // Chloroauric Acid + Sulfur = Precipitated Gold (configurable amount)
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                new ItemStack(ModItems.GOLDPRECIP, Config.goldPrecPerOre),
                //Inputs
                "dustSulfur",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HAUCL4)
        ));

        // Smelting Gold Precipitate = Gold Ingot!
        GameRegistry.addSmelting(ModItems.GOLDPRECIP, new ItemStack(Items.GOLD_INGOT), 1.0f);

        // Aqua Regia + Lapis Lazuli = Hydroflouric Acid
        // This is "Minecraft chemistry", not based on the real world!
        GameRegistry.addRecipe(new ShapelessNBTRecipe(false,
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.HF),
                //Inputs
                "gemLapis",
                "gemLapis",
                "gemLapis",
                "gemLapis",
                UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.H2SO4),
                "gemLapis",
                "gemLapis",
                "gemLapis",
                "gemLapis"
        ));

//		//TODO: simple test for bucket being returned
//		GameRegistry.addShapelessRecipe(
//				new ItemStack(Items.GUNPOWDER),
//				//Inputs
//				Items.ARROW,
//				Items.ARROW,
//				Items.ARROW,
//				UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.NEUTRAL)
//		);
//
//		GameRegistry.addRecipe(new ShapelessNBTRecipe( true,//addShapelessRecipe(
//				new ItemStack(Items.GUNPOWDER),
//				//Inputs
//				Items.ARROW,
//				Items.ARROW,
//				Items.ARROW//,
//		));

    }

    /**
     * Add this mod's brewing recipes.
     */
    private static void addBrewingRecipes() {
//		addStandardConversionRecipes(ModPotionTypes.TEST, ModPotionTypes.LONG_TEST, ModPotionTypes.STRONG_TEST, ModItems.ARROW);
    }

    /**
     * Add the standard conversion recipes for the specified {@link PotionType}s:
     * <ul>
     * <li>Awkward + Ingredient = Standard</li>
     * <li>Standard + Redstone = Long</li>
     * <li>Standard + Glowstone = Strong</li>
     * </ul>
     *
     * @param standardPotionType The standard PotionType
     * @param longPotionType     The long PotionType
     * @param strongPotionType   The strong PotionType
     * @param ingredient         The ingredient
     */
    private static void addStandardConversionRecipes(PotionType standardPotionType, PotionType longPotionType, PotionType strongPotionType, Item ingredient) {
//		PotionHelper.registerPotionTypeConversion(PotionTypes.AWKWARD, new PotionHelper.ItemPredicateInstance(ingredient), standardPotionType);
//		PotionHelper.registerPotionTypeConversion(standardPotionType, new PotionHelper.ItemPredicateInstance(Items.REDSTONE), longPotionType);
//		PotionHelper.registerPotionTypeConversion(standardPotionType, new PotionHelper.ItemPredicateInstance(Items.GLOWSTONE_DUST), strongPotionType);
    }

    /**
     * Remove crafting recipes.
     */
    public static void removeCraftingRecipes() {

    }

    /**
     * Remove all crafting recipes with the specified {@link Block} as their output.
     *
     * @param output The output Block
     */
    private static void removeRecipe(Block output) {
        final Item item = Item.getItemFromBlock(output);
        assert item != null;

        removeRecipe(item);
    }

    /**
     * Remove all crafting recipes with the specified {@link Item} as their output.
     * <p>
     * Adapted from Rohzek's code in this post:
     * http://www.minecraftforge.net/forum/index.php/topic,33631.0.html
     *
     * @param output The output Item
     */
    private static void removeRecipe(Item output) {
        int recipesRemoved = 0;

        final List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        final Iterator<IRecipe> remover = recipes.iterator();

        while (remover.hasNext()) {
            final ItemStack itemstack = remover.next().getRecipeOutput();

            // If the recipe's output Item is the specified Item,
            if (itemstack != null && itemstack.getItem() == output) {
                // Remove the recipe
                remover.remove();
                recipesRemoved++;
            }
        }

        Logger.info("Removed %d recipes for %s", recipesRemoved, output.getRegistryName());
    }

    /**
     * Remove all crafting recipes that are instances of the specified class.
     * <p>
     * Test for this thread:
     * http://www.minecraftforge.net/forum/index.php/topic,33631.0.html
     *
     * @param recipeClass The recipe class
     */
    private static void removeRecipeClass(Class<? extends IRecipe> recipeClass) {
        int recipesRemoved = 0;

        final List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        final Iterator<IRecipe> remover = recipes.iterator();

        while (remover.hasNext()) {
            // If the recipe is an instance of the specified class,
            if (recipeClass.isInstance(remover.next())) {
                // Remove the recipe
                remover.remove();
                recipesRemoved++;
            }
        }

        Logger.info("Removed %d recipes for %s", recipesRemoved, recipeClass);
    }
}
