package droidicus.aquaregia;

//import droidicus.aquaregia.client.gui.GuiHandler;
import droidicus.aquaregia.config.Config;
//import droidicus.aquaregia.event.BlockEventHandler;
//import droidicus.aquaregia.event.ItemCombinationHandler;
//import droidicus.aquaregia.event.NetworkEventHandler;
//import droidicus.aquaregia.event.PlayerEventHandler;
import droidicus.aquaregia.init.*;
import droidicus.aquaregia.proxy.IProxy;
import droidicus.aquaregia.remap.Remapper;
import droidicus.aquaregia.tests.Tests;
import droidicus.aquaregia.util.BlockDumper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.util.UUID;

@Mod(modid = AquaRegia.MODID, acceptedMinecraftVersions = "[1.10.2]", guiFactory = "droidicus.aquaregia.config.GuiConfigFactoryAquaRegia")
public class AquaRegia {
	public static final String MODID = "aquaregia";

	public static CreativeTabAquaRegia creativeTab;

	@SidedProxy(clientSide = "droidicus.aquaregia.proxy.CombinedClientProxy", serverSide = "droidicus.aquaregia.proxy.DedicatedServerProxy")
	public static IProxy proxy;

	@Instance(MODID)
	public static AquaRegia instance;

	public static SimpleNetworkWrapper network;

	static {
		FluidRegistry.enableUniversalBucket(); // Must be called before preInit
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Logger.setLogger(event.getModLog());

//		FMLLog.bigWarning("Random UUID: %s", UUID.randomUUID().toString());

		creativeTab = new CreativeTabAquaRegia();
		Config.load(event);

//		ModCapabilities.registerCapabilities();

//		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
//		MinecraftForge.EVENT_BUS.register(new NetworkEventHandler());
//		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
//		MinecraftForge.EVENT_BUS.register(ItemCombinationHandler.class);

		network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

//		ModSoundEvents.registerSounds();
//		ModMessages.registerMessages();
		ModFluids.registerFluids();
		ModBlocks.registerBlocks();
//		ModBlocks.registerTileEntities();
		ModItems.registerItems();
		ModFluids.registerFluidContainers();
		ModMapGen.registerMapGen();
//		ModEntities.registerEntities();
//		ModPotions.registerPotions();
//		ModPotionTypes.registerPotionTypes();

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.registerRecipes();
//		ModRecipes.removeCraftingRecipes();
		ModMapGen.registerWorldGenerators();
//		ModEntities.addSpawns();

//		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		FMLInterModComms.sendMessage("Waila", "register", "droidicus.aquaregia.compat.waila.WailaCompat.register");

		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		BlockDumper.dump();

		proxy.postInit();

		Tests.runTests();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		ModCommands.registerCommands(event);
	}

	@EventHandler
	public void missingMapings(FMLMissingMappingsEvent event) {
		Remapper.remap(event.get());
	}
}
