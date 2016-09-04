package droidicus.aquaregia.init;

import droidicus.aquaregia.event.MapGenHandler;
import droidicus.aquaregia.world.gen.WorldGenBanner;
import droidicus.aquaregia.world.gen.WorldGenOres;
import droidicus.aquaregia.world.gen.structure.MapGenScatteredFeatureModBiomes;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModMapGen {
	public static void registerMapGen() {
//		MapGenStructureIO.registerStructure(MapGenScatteredFeatureModBiomes.Start.class, "aquaregia:MapGenScatteredFeatureModBiomes");
//
//		MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenHandler());
	}

	public static void registerWorldGenerators() {
//		GameRegistry.registerWorldGenerator(new WorldGenBanner(), 100);
//		GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
	}
}
