package droidicus.aquaregia.block;

import java.util.Random;

import droidicus.aquaregia.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

/**
 * Created by droidicus.
 */
public class BlockOreSulfur extends BlockAquaRegia {
    public BlockOreSulfur() {
        super(Material.ROCK, "oresulfur");
        this.setHardness(3F);
        this.setResistance(5F);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
        return ModItems.SULFUR;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 3 + random.nextInt(3); //between 3 and 5
    }
}
