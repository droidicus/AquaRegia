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

	public static boolean enableOreGen;
	public static boolean enableGunpowerSulfur;
	public static int niterPerChunk;
	public static int saltPerChunk;
	public static int sulfurPerChunk;

	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		reloadConfig();

		MinecraftForge.EVENT_BUS.register(Config.class);
	}

	private static void reloadConfig() {
		enableOreGen = config.getBoolean("enableOreGen", Configuration.CATEGORY_GENERAL, true, "Enable Ore Generation for this mod.", LANG_PREFIX + "enableOreGen");
		enableGunpowerSulfur = config.getBoolean("enableGunpowerSulfur", Configuration.CATEGORY_GENERAL, true, "Enable using gunpowder in place of sulfur (3 for 1).", LANG_PREFIX + "enableGunpowerSulfur");
		niterPerChunk = config.getInt("niterPerChunk", Configuration.CATEGORY_GENERAL, 20, 0, Integer.MAX_VALUE, "Number of Niter Oreveins per chunk", LANG_PREFIX + "niterPerChunk");
		saltPerChunk = config.getInt("saltPerChunk", Configuration.CATEGORY_GENERAL, 32, 0, Integer.MAX_VALUE, "Number of Salt Ore veins per chunk", LANG_PREFIX + "saltPerChunk");
		sulfurPerChunk = config.getInt("sulfurPerChunk", Configuration.CATEGORY_GENERAL, 10, 0, Integer.MAX_VALUE, "Number of Sulfur Ore veins per chunk", LANG_PREFIX + "sulfurPerChunk");

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