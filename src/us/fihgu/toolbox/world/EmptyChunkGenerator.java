package us.fihgu.toolbox.world;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class EmptyChunkGenerator extends ChunkGenerator
{
	@Override
	public Location getFixedSpawnLocation(World world, Random random)
	{
		return new Location(world, 0.0D, 60.0D, 0.0D);
	}

	@Override
	public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome)
	{
		ChunkData data = this.createChunkData(world);
		return data;
	}
}
