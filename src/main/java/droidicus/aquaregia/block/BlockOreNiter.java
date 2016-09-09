package droidicus.aquaregia.block;

import droidicus.aquaregia.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by droidicus.
 */
public class BlockOreNiter extends BlockAquaRegia {
    public BlockOreNiter() {
        super(Material.ROCK, "oreniter");
        this.setHardness(3F);
        this.setResistance(5F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return ModItems.NITER;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3 + random.nextInt(3); //between 3 and 5
    }
}
