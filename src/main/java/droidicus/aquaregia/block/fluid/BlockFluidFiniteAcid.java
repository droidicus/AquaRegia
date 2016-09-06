package droidicus.aquaregia.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by droidicus.
 */

//TODO: Replace base class with BlockFluidFinite when Forge issue#3233 is resolved
public class BlockFluidFiniteAcid extends BlockFluidFiniteFull {
    public BlockFluidFiniteAcid(Fluid fluid, Material material) {
        super(fluid, material);
    }
}