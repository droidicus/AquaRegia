package droidicus.aquaregia.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
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
}
