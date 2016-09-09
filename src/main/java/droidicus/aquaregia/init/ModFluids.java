package droidicus.aquaregia.init;

import droidicus.aquaregia.AquaRegia;
import droidicus.aquaregia.block.fluid.BlockFluidFiniteAcid;
import droidicus.aquaregia.block.fluid.FluidAcid;
import droidicus.aquaregia.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class ModFluids {
    public static final Fluid NEUTRAL;
    public static final Fluid H2SO4;
    public static final Fluid HAUCL4;
    public static final Fluid HCL;
    public static final Fluid HF;
    public static final Fluid HNO3;
    public static final Fluid HNO3HCL;

    /**
     * The fluids registered by this mod. Includes fluids that were already registered by another mod.
     */
    public static final Set<Fluid> FLUIDS = new HashSet<>();

    /**
     * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
     */
    public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

    static {
        NEUTRAL = createFluidAcid("neutral", true, 0xc0ffffff,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        H2SO4 = createFluidAcid("h2so4", true, 0xc0a5ffff,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        HAUCL4 = createFluidAcid("haucl4", true, 0xc0ffffa5,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        HCL = createFluidAcid("hcl", true, 0xc0ffa5ff,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        HF = createFluidAcid("hf", true, 0xc03a4bc8,//0xc04040ff,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        HNO3 = createFluidAcid("hno3", true, 0xc0a5a5ff,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));

        HNO3HCL = createFluidAcid("hno3hcl", true, 0xc0ffa5a5,
                fluid -> fluid.setLuminosity(0).setDensity(1000).setViscosity(1000),
                fluid -> new BlockFluidFiniteAcid(fluid, new MaterialLiquid(MapColor.WATER)));
    }

    public static void registerFluids() {
        // Dummy method to make sure the static initialiser runs
    }

    public static void registerFluidContainers() {
        registerTank(FluidRegistry.WATER);
        registerTank(FluidRegistry.LAVA);

        for (final Fluid fluid : FLUIDS) {
            registerBucket(fluid);
            registerTank(fluid);
        }
    }

    /**
     * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
     *
     * @param name                 The name of the fluid
     * @param hasFlowIcon          Does the fluid have a flow icon?
     * @param fluidPropertyApplier A function that sets the properties of the {@link Fluid}
     * @param blockFactory         A function that creates the {@link IFluidBlock}
     * @return The fluid and block
     */
    private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
        final String texturePrefix = Constants.RESOURCE_PREFIX + "blocks/fluids/";

        final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
        final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

        Fluid fluid = new Fluid(name, still, flowing);
        final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid) {
            fluidPropertyApplier.accept(fluid);
            registerFluidBlock(blockFactory.apply(fluid));
        } else {
            fluid = FluidRegistry.getFluid(name);
        }

        FLUIDS.add(fluid);

        return fluid;
    }

    private static <T extends Block & IFluidBlock> Fluid createFluidAcid(String name, boolean hasFlowIcon, int color, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
        final String texturePrefix = Constants.RESOURCE_PREFIX + "blocks/fluids/";

        final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
        final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

        FluidAcid fluid = new FluidAcid(name, color, still, flowing);
        final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid) {
            fluidPropertyApplier.accept(fluid);
            registerFluidBlock(blockFactory.apply(fluid));
        } else {
//			fluid = FluidRegistry.getFluid(name);
            //TODO: deal with this case
        }

        FLUIDS.add(fluid);

        return fluid;
    }

    private static <T extends Block & IFluidBlock> T registerFluidBlock(T block) {
        block.setRegistryName("fluid." + block.getFluid().getName());
        block.setUnlocalizedName(Constants.RESOURCE_PREFIX + block.getFluid().getUnlocalizedName());
        block.setCreativeTab(AquaRegia.creativeTab);

        ModBlocks.registerBlock(block);

        MOD_FLUID_BLOCKS.add(block);

        return block;
    }

    private static void registerBucket(Fluid fluid) {
        FluidRegistry.addBucketForFluid(fluid);
    }

    private static void registerTank(Fluid fluid) {
//		final FluidStack fluidStack = new FluidStack(fluid, TileEntityFluidTank.CAPACITY);
//		((ItemFluidTank) Item.getItemFromBlock(ModBlocks.FLUID_TANK)).addFluid(fluidStack);
    }
}
