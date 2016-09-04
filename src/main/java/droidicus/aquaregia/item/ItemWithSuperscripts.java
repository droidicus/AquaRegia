package droidicus.aquaregia.item;

import droidicus.aquaregia.util.StringUtils;

/**
 * An item that displays a number stored in NBT as a superscript in its display name.
 *
 * @author Choonster
 */
public class ItemWithSuperscripts extends ItemWithScripts {
	public ItemWithSuperscripts() {
		super(StringUtils::superscript, "superscripts");
	}
}
