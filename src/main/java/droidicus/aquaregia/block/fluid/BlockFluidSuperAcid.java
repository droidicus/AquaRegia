package droidicus.aquaregia.block.fluid;

import droidicus.aquaregia.config.Config;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

/**
 * Created by droidicus.
 */
public class BlockFluidSuperAcid extends BlockFluidFiniteAcid {
    public BlockFluidSuperAcid(Fluid fluid, Material material, float damageIn, int withDuration, int withLevel, boolean attackSil, boolean attackRoc) {
        super(fluid, material, damageIn, withDuration, withLevel, attackSil, attackRoc);
        super.acidResistance = Config.superAcidDissolvesResistance;
    }

    // Let the WORLD BURN!
    @Override
    protected boolean changeQuantaValue(World worldIn, BlockPos pos, IBlockState state, int change) {
        return super.changeQuantaValue(worldIn, pos, state, Config.superAcidSpreadFactor);
    }
}
