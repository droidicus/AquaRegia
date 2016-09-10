package droidicus.aquaregia.block.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by droidicus.
 */

//TODO: Replace base class with BlockFluidFinite when Forge issue is resolved https://github.com/MinecraftForge/MinecraftForge/issues/3231
public class BlockFluidFiniteAcid extends BlockFluidFiniteFull {
    public DamageSource acidDamageSource = new DamageSource("aquaregia.acid");
    private float damageFromAcid = 0;
    private int witherDuration;
    private int witherLevel;

    public BlockFluidFiniteAcid(Fluid fluid, Material material, float damageIn, int duration, int level) {
        super(fluid, material);
        damageFromAcid = damageIn;
        witherDuration = duration;
        witherLevel = level;
    }

    // Burn any entity silly enough to go for a swim
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

        if (entityIn instanceof EntityLivingBase) {
            if (damageFromAcid > 0.0F) {
                entityIn.attackEntityFrom(acidDamageSource, damageFromAcid);
            } else if (damageFromAcid == -1.0F) {
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, witherDuration, witherLevel));
            }
        }
    }

    // Disolve blocks around the acid if they are Dissolvable
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        this.checkForDissolve(worldIn, pos, state);
    }
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        super.neighborChanged(state, worldIn, pos, blockIn);
        this.checkForDissolve(worldIn, pos, state);
    }

    protected boolean checkForDissolve(World worldIn, BlockPos pos, IBlockState state) {
        for (EnumFacing face : EnumFacing.VALUES) {

        }

        if(this.blockMaterial == Material.LAVA) {
            boolean flag = false;
            EnumFacing[] integer = EnumFacing.values();
            int var6 = integer.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                EnumFacing enumfacing = integer[var7];
                if(enumfacing != EnumFacing.DOWN && worldIn.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.WATER) {
                    flag = true;
                    break;
                }
            }

            if(flag) {
                Integer var9 = (Integer)state.getValue(LEVEL);
                if(var9.intValue() == 0) {
                    worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
                    this.triggerDissolveEffects(worldIn, pos);
                    return true;
                }

                if(var9.intValue() <= 4) {
                    worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
                    this.triggerDissolveEffects(worldIn, pos);
                    return true;
                }
            }
        }

        return false;
    }

    protected void triggerDissolveEffects(World worldIn, BlockPos pos) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for(int i = 0; i < 8; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
        }

    }
}
