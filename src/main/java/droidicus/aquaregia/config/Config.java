package droidicus.aquaregia.config;

import droidicus.aquaregia.AquaRegia;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
	static final String LANG_PREFIX = AquaRegia.MODID + ".config.";

	static Configuration config;

//	public static boolean fooBar;
//	public static int baz;

	public static boolean enableOreGen;
	public static boolean enableGunpowerSulfur;

	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		reloadConfig();

		MinecraftForge.EVENT_BUS.register(Config.class);
	}

	private static void reloadConfig() {
//		fooBar = config.getBoolean("fooBar", Configuration.CATEGORY_GENERAL, false, "This is an example boolean property.", LANG_PREFIX + "fooBar");
//		baz = config.getInt("baz", Configuration.CATEGORY_CLIENT, -100, -Integer.MAX_VALUE, Integer.MAX_VALUE, "This is an example int property.", LANG_PREFIX + "baz");

		enableOreGen = config.getBoolean("enableOreGen", Configuration.CATEGORY_GENERAL, true, "Enable Ore Generation for this mod.", LANG_PREFIX + "enableOreGen");
		enableGunpowerSulfur = config.getBoolean("enableGunpowerSulfur", Configuration.CATEGORY_GENERAL, true, "Enable using gunpowder in place of sulfur (3 for 1).", LANG_PREFIX + "enableGunpowerSulfur");

		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(AquaRegia.MODID)) {
			reloadConfig();
		}
	}
}
