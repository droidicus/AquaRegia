package droidicus.aquaregia.block.fluid;

import droidicus.aquaregia.config.Config;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.Random;

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

    // Dissolve blocks that are in contact with the acid
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        boolean validBlock = true;
        if (Config.acidDissolvesBlocks) {
            //randomly dissolve a block and reduce fluid stack size by 1
            BlockPos dissolvePos = pos.offset(EnumFacing.random(rand));
            if ((worldIn.getBlockState(dissolvePos).getMaterial() == Material.ROCK) &&
                    (worldIn.getBlockState(dissolvePos).getBlock().getExplosionResistance(null) < Config.acidDissolvesResistance)) {
                worldIn.setBlockToAir(dissolvePos);
                this.triggerDissolveEffects(worldIn, dissolvePos);
                validBlock = this.changeQuantaValue(worldIn, pos, state, -1);
            }
        }

        if (validBlock) { //if the block is still acid and not air
            super.updateTick(worldIn, pos, state, rand);
        }
    }

    protected boolean changeQuantaValue(World worldIn, BlockPos pos, IBlockState state, int change) {
        int beforeQuant = state.getValue(LEVEL);
        int afterQuant = beforeQuant + change;

        if (afterQuant < 0) {
            worldIn.setBlockToAir(pos);
            return false;
        } else {
            worldIn.setBlockState(pos, state.withProperty(LEVEL, afterQuant), 2);
            return true;
        }
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
