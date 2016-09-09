package droidicus.aquaregia.init;

import droidicus.aquaregia.item.ItemAquaRegia;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess,unused")
public class ModItems {
    public static final Set<Item> ITEMS = new HashSet<>();

    public static final ItemAquaRegia SULFUR;
    public static final ItemAquaRegia NITER;
    public static final ItemAquaRegia SALT;
    public static final ItemAquaRegia GOLDPRECIP;

    static {
        SULFUR = registerItemOreDict(new ItemAquaRegia("sulfur"), "dustSulfur");
        NITER = registerItemOreDict(new ItemAquaRegia("niter"), "dustSaltpeter");
        SALT = registerItemOreDict(new ItemAquaRegia("salt"), "dustSalt");
        GOLDPRECIP = registerItemOreDict(new ItemAquaRegia("goldprecip"), "dustGold");
    }

    public static void registerItems() {
        // Dummy method to make sure the static initialiser runs
    }

    /**
     * Register an Item
     *
     * @param item The Item instance
     * @param <T>  The Item type
     * @return The Item instance
     */
    private static <T extends Item> T registerItem(T item) {
        GameRegistry.register(item);
        ITEMS.add(item);

        return item;
    }

    /**
     * Register an Item
     *
     * @param item     The Item instance
     * @param dictName The Item Dictionary Name
     * @param <T>      The Item type
     * @return The Item instance
     */
    private static <T extends Item> T registerItemOreDict(T item, String dictName) {
        registerItem(item);
        OreDictionary.registerOre(dictName, item);

        return item;
    }
}
