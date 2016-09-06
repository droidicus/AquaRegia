package droidicus.aquaregia.block.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by droidicus.
 */
public class FluidAcid extends Fluid {
    public final int color;

    public FluidAcid(String fluidName, int color, ResourceLocation still, ResourceLocation flowing) {
        super(fluidName, still, flowing);

        // make opaque if no alpha is set
        if(((color >> 24) & 0xFF) == 0) {
            color |= 0xFF << 24;
        }
        this.color = color;
    }


    @Override
    public int getColor() {
        return color;
    }
}
