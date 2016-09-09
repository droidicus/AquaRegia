package droidicus.aquaregia.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

/**
 * @author droidicus
 */

public class BlockFluidFiniteFull extends BlockFluidFinite {
    public BlockFluidFiniteFull(Fluid fluid, Material material) {
        super(fluid, material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, quantaPerBlock - 1));
    }
}
