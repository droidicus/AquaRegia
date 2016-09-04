package droidicus.aquaregia.compat.waila;

import droidicus.aquaregia.block.BlockPlane;
import net.minecraft.block.properties.IProperty;

/**
 * @author Choonster
 */
public class HUDHandlerVerticalRotatable extends HUDHandlerEnumProperty<BlockPlane.EnumVerticalRotation> {
	public HUDHandlerVerticalRotatable(IProperty<BlockPlane.EnumVerticalRotation> property) {
		super(property, "tile.aquaregia:plane.verticalRotation.desc", "aquaregia:verticalRotation");
	}
}
