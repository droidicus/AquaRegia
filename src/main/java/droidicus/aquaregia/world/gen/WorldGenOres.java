package droidicus.aquaregia.world.gen;

import droidicus.aquaregia.config.Config;
import droidicus.aquaregia.init.ModBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {
	private final WorldGenMinable oreGenNiter;
	private final WorldGenMinable oreGenSalt;
	private final WorldGenMinable oreGenSulfur;

	public WorldGenOres() {
		oreGenNiter = new WorldGenMinable(ModBlocks.BLOCK_ORE_NITER.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		oreGenSalt = new WorldGenMinable(ModBlocks.BLOCK_ORE_SALT.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		oreGenSulfur = new WorldGenMinable(ModBlocks.BLOCK_ORE_SULFUR.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (Config.enableOreGen) {
			final BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

			switch (world.provider.getDimensionType()) {
				case OVERWORLD:
					for (int i = 0; i < Config.niterPerChunk; i++) {
						oreGenNiter.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(32) + 16, random.nextInt(16)));
					}
					for (int i = 0; i < Config.saltPerChunk; i++) {
						oreGenSalt.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(64) + 32, random.nextInt(16)));
					}
					for (int i = 0; i < Config.sulfurPerChunk; i++) {
						oreGenSulfur.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(24), random.nextInt(16)));
					}
					break;
				case NETHER:
					break;
				case THE_END:
					break;
			}
		}
	}
}
