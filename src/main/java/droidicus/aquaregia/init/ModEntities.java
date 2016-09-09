package droidicus.aquaregia.init;

import droidicus.aquaregia.AquaRegia;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

//import droidicus.aquaregia.entity.EntityModArrow;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.entity.monster.EntityGuardian;
//import net.minecraftforge.common.BiomeDictionary;

public class ModEntities {
    private static int entityID = 0;

    public static void registerEntities() {
//		registerEntity(EntityModArrow.class, "mod_arrow", 64, 20, false);
    }

    public static void addSpawns() {
//		EntityRegistry.addSpawn(EntityGuardian.class, 100, 5, 20, EnumCreatureType.WATER_CREATURE, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN));
    }

    /**
     * Register an entity with the specified tracking values.
     *
     * @param entityClass          The entity's class
     * @param entityName           The entity's unique name
     * @param trackingRange        The range at which MC will send tracking updates
     * @param updateFrequency      The frequency of tracking updates
     * @param sendsVelocityUpdates Whether to send velocity information packets as well
     */
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID++, AquaRegia.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }
}
