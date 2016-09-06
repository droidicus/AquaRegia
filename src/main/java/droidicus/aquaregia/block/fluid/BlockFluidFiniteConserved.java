package droidicus.aquaregia.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by droidicus.
 */

public class BlockFluidFiniteConserved extends BlockFluidFinite {
    public BlockFluidFiniteConserved(Fluid fluid, Material material) {
        super(fluid, material);
    }

    @Override
    public FluidStack drain(World world, BlockPos pos, boolean doDrain) {
        int quantaValue = this.getQuantaValue(world, pos);
        FluidStack fluidStack = new FluidStack(this.getFluid(), 1000*(quantaValue>0?1:0));
        if(doDrain) {
            if (quantaValue == 1) {
                world.setBlockToAir(pos);
            } else {
                world.setBlockState(pos, world.getBlockState(pos).withProperty(LEVEL, quantaValue-2), 2);
            }
        }

        return fluidStack;
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean fullHit) {
        return fullHit;
    }
}
