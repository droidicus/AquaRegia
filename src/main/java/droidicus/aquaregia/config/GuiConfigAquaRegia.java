package droidicus.aquaregia.config;


import droidicus.aquaregia.AquaRegia;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.List;
import java.util.stream.Collectors;

public class GuiConfigAquaRegia extends GuiConfig {
	public GuiConfigAquaRegia(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(), AquaRegia.MODID, false, false, I18n.format(Config.LANG_PREFIX + "title"));
	}

	private static List<IConfigElement> getConfigElements() {
		return Config.config.getCategoryNames().stream()
				.map(categoryName -> new ConfigElement(Config.config.getCategory(categoryName).setLanguageKey(Config.LANG_PREFIX + categoryName)))
				.collect(Collectors.toList());
	}
}
