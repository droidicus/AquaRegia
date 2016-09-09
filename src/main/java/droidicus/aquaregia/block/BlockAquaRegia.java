package droidicus.aquaregia.block;

import droidicus.aquaregia.AquaRegia;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * A base class for this mod's blocks.
 *
 * @author Choonster
 */
public class BlockAquaRegia extends Block {
    public BlockAquaRegia(Material material, MapColor mapColor, String blockName) {
        super(material, mapColor);
        setBlockName(this, blockName);
        setCreativeTab(AquaRegia.creativeTab);
    }

    public BlockAquaRegia(Material materialIn, String blockName) {
        this(materialIn, materialIn.getMaterialMapColor(), blockName);
    }

    /**
     * Set the registry name of {@code block} to {@code blockName} and the unlocalised name to the full registry name.
     *
     * @param block     The block
     * @param blockName The block's name
     */
    public static void setBlockName(Block block, String blockName) {
        block.setRegistryName(blockName);
        block.setUnlocalizedName(block.getRegistryName().toString());
    }
}
