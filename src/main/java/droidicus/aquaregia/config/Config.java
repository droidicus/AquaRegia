package droidicus.aquaregia.config;

import droidicus.aquaregia.AquaRegia;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
    static final String LANG_PREFIX = AquaRegia.MODID + ".config.";
    public static boolean enableNeutralWater;
    public static boolean enableOreGen;
    public static boolean enableGunpowderSulfur;
    public static int niterPerChunk;
    public static int saltPerChunk;
    public static int sulfurPerChunk;
    public static int goldPrecPerOre;
    public static float h2so4AcidDamage;
    public static float hclAcidDamage;
    public static float hno3AcidDamage;
    public static float hno3hclAcidDamage;
    public static float haucl4AcidDamage;
    public static float hfAcidDamage;
    public static int hfWitherLevel;
    public static int hfWitherDuration;
    public static float hso3fAcidDamage;
    public static int hso3fWitherLevel;
    public static int hso3fWitherDuration;
    public static float h2fsbf6AcidDamage;
    public static int h2fsbf6WitherLevel;
    public static int h2fsbf6WitherDuration;
    public static boolean enableSuperAcid;
    public static boolean acidDissolvesBlocks;
    public static float acidDissolvesResistance;
    public static float superAcidDissolvesResistance;
    public static int superAcidSpreadFactor;

    static Configuration config;

    public static void load(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        reloadConfig();

        MinecraftForge.EVENT_BUS.register(Config.class);
    }

    private static void reloadConfig() {
        enableNeutralWater = config.getBoolean("enableNeutralWater", Configuration.CATEGORY_GENERAL, false, "Enable Neutral Water.", LANG_PREFIX + "enableNeutralWater");
        enableOreGen = config.getBoolean("enableOreGen", Configuration.CATEGORY_GENERAL, true, "Enable Ore Generation for this mod.", LANG_PREFIX + "enableOreGen");
        enableGunpowderSulfur = config.getBoolean("enableGunpowderSulfur", Configuration.CATEGORY_GENERAL, true, "Enable using gunpowder in place of sulfur (3 for 1).", LANG_PREFIX + "enableGunpowderSulfur");
        niterPerChunk = config.getInt("niterPerChunk", Configuration.CATEGORY_GENERAL, 20, 0, Integer.MAX_VALUE, "Number of Niter Ore veins per chunk", LANG_PREFIX + "niterPerChunk");
        saltPerChunk = config.getInt("saltPerChunk", Configuration.CATEGORY_GENERAL, 32, 0, Integer.MAX_VALUE, "Number of Salt Ore veins per chunk", LANG_PREFIX + "saltPerChunk");
        sulfurPerChunk = config.getInt("sulfurPerChunk", Configuration.CATEGORY_GENERAL, 10, 0, Integer.MAX_VALUE, "Number of Sulfur Ore veins per chunk", LANG_PREFIX + "sulfurPerChunk");
        goldPrecPerOre = config.getInt("goldPrecPerOre", Configuration.CATEGORY_GENERAL, 3, 0, 64, "Number of gold precipitate generated per ore processed", LANG_PREFIX + "goldPrecPerOre");

        h2so4AcidDamage = config.getFloat("h2so4AcidDamage", Configuration.CATEGORY_GENERAL, 2.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Sulfuric Acid", LANG_PREFIX + "h2so4AcidDamage");
        hclAcidDamage = config.getFloat("hclAcidDamage", Configuration.CATEGORY_GENERAL, 1.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Hydrochloric Acid", LANG_PREFIX + "hclAcidDamage");
        hno3AcidDamage = config.getFloat("hno3AcidDamage", Configuration.CATEGORY_GENERAL, 1.5F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Nitric Acid", LANG_PREFIX + "hno3AcidDamage");
        hno3hclAcidDamage = config.getFloat("hno3hclAcidDamage", Configuration.CATEGORY_GENERAL, 1.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Aqua Regia", LANG_PREFIX + "hno3hclAcidDamage");
        haucl4AcidDamage = config.getFloat("haucl4AcidDamage", Configuration.CATEGORY_GENERAL, 0.5F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Chloroauric Acid", LANG_PREFIX + "haucl4AcidDamage");
        hfAcidDamage = config.getFloat("hfAcidDamage", Configuration.CATEGORY_GENERAL, 0.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Hydroflouric Acid", LANG_PREFIX + "hfAcidDamage");
        hfWitherLevel = config.getInt("hfWitherLevel", Configuration.CATEGORY_GENERAL, 1, 1, 10, "Level of Wither debuff placed by Hydrofluoric Acid", LANG_PREFIX + "hfWitherLevel");
        hfWitherDuration = config.getInt("hfWitherDuration", Configuration.CATEGORY_GENERAL, 600, 1, Integer.MAX_VALUE, "Number of ticks that wither effect lasts for by Hydrofluoric Acid", LANG_PREFIX + "hfWitherDuration");
        hso3fAcidDamage = config.getFloat("hso3fAcidDamage", Configuration.CATEGORY_GENERAL, 2.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Fluorosulfuric Acid", LANG_PREFIX + "hso3fAcidDamage");
        hso3fWitherLevel = config.getInt("hso3fWitherLevel", Configuration.CATEGORY_GENERAL, 1, 1, 10, "Level of Wither debuff placed by Fluorosulfuric Acid", LANG_PREFIX + "hso3fWitherLevel");
        hso3fWitherDuration = config.getInt("hso3fWitherDuration", Configuration.CATEGORY_GENERAL, 600, 1, Integer.MAX_VALUE, "Number of ticks that wither effect lasts for by Fluorosulfuric Acid", LANG_PREFIX + "hso3fWitherDuration");
        h2fsbf6AcidDamage = config.getFloat("h2fsbf6AcidDamage", Configuration.CATEGORY_GENERAL, 20.0F, 0.0F, Float.MAX_VALUE, "Damage done per hit by Fluoroantimonic Acid" , LANG_PREFIX + "h2fsbf6AcidDamage");
        h2fsbf6WitherLevel = config.getInt("h2fsbf6WitherLevel", Configuration.CATEGORY_GENERAL, 5, 1, 10, "Level of Wither debuff placed by Fluoroantimonic Acid", LANG_PREFIX + "h2fsbf6WitherLevel");
        h2fsbf6WitherDuration = config.getInt("h2fsbf6WitherDuration", Configuration.CATEGORY_GENERAL, 1200, 1, Integer.MAX_VALUE, "Number of ticks that wither effect lasts for by Fluoroantimonic Acid", LANG_PREFIX + "h2fsbf6WitherDuration");

        enableSuperAcid = config.getBoolean("enableSuperAcid", Configuration.CATEGORY_GENERAL, true, "Enable the VERY DANGEROUS Super Acids!", LANG_PREFIX + "enableSuperAcid");
        acidDissolvesBlocks = config.getBoolean("acidDissolvesBlocks", Configuration.CATEGORY_GENERAL, true, "Enable acids dissolving blocks of incompatible types.", LANG_PREFIX + "acidDissolvesBlocks");
        acidDissolvesResistance = config.getFloat("acidDissolvesResistance", Configuration.CATEGORY_GENERAL, 8.0F, 0.0F, Float.MAX_VALUE, "Explosion Resistance of block that will prevent acid from dissolving it", LANG_PREFIX + "acidDissolvesResistance");
        superAcidDissolvesResistance = config.getFloat("superAcidDissolvesResistance", Configuration.CATEGORY_GENERAL, 2000.0F, 0.0F, Float.MAX_VALUE, "Explosion Resistance of block that will prevent Super Acid from dissolving it", LANG_PREFIX + "superAcidDissolvesResistance");
        superAcidSpreadFactor = config.getInt("superAcidSpreadFactor", Configuration.CATEGORY_GENERAL, 1, -1, Integer.MAX_VALUE, "How much super acid spreads, -1 is like normal acid, 0 is no reduction when dissolving, 1 is some spread, 2 or more is potentially WORLD ENDING!", LANG_PREFIX + "superAcidSpreadFactor");;

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
