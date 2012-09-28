package com.shaboozey.realms.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;

public class Util {
	
	public static Environment getEnvironment(String environmentName) {
		
		if(environmentName.equalsIgnoreCase("normal"))
		{
			return Environment.NORMAL;
		}
		else if(environmentName.equalsIgnoreCase("nether"))
		{
			return Environment.NETHER;
		}
		else if(environmentName.equalsIgnoreCase("the_end"))
		{
			return Environment.THE_END;
		}
		else
		{
			return Environment.NORMAL;
		}
	}
	
	public static boolean mapExists(String mapName)
	{
		return new File("." + File.separator + mapName).exists();
	}
	
	public static String getDefaultMap()
	{
		Properties pro = new Properties();
		
		try{
			FileInputStream in = new FileInputStream("server.properties");
			pro.load(in);
			return (String) pro.get("level-name");
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static boolean isDefaultMap(String mapName)
	{
		return mapName.equalsIgnoreCase(ShaboozeyRealms.defaultWorld);
	}
	
	public static boolean isBukkitLoaded(String worldName)
	{
		for(World w : ShaboozeyRealms.getPlugin().getServer().getWorlds())
		{
			if(w.getName().equalsIgnoreCase(worldName))
				return true;
		}
		return false;
	}
	
	public static boolean deleteDirectory(File path) 
	{
		if(path.exists()) {
			
			File[] files = path.listFiles();
			
			for(int i=0; i<files.length; i++) {
				
				if(files[i].isDirectory()) {
					deleteDirectory(files[i]);
				}
				else {
					files[i].delete();
				}
			}
		}
		
		return path.delete();
	}
	
	public static void unloadChunks(World w)
	{
		for(Chunk c : w.getLoadedChunks())
		{
			w.unloadChunk(c);
		}
	}

	public static void movePlayersToSpawn(World w) {
		
		World dw = ShaboozeyRealms.getPlugin().getServer().getWorld(ShaboozeyRealms.defaultWorld);
		
		for(Player p : w.getPlayers())
		{
			p.teleport(new Location(dw, 
					dw.getSpawnLocation().getX(), 
					dw.getSpawnLocation().getY(), 
					dw.getSpawnLocation().getZ()));
		}
	}
	
	public static void removeEntities()
	{
		for(String s : RealmManager.getRealms().keySet())
		{
			
			if(!RealmFileManager.hasConfig(s))
			{
				continue;
			}
			
			if(RealmManager.getRealmState(s) == LoadState.UNLOADED)
			{
				continue;
			}
			
			World w = ShaboozeyRealms.getPlugin().getServer().getWorld(s);
			
			for(LivingEntity le : w.getLivingEntities())
			{	
				if(le instanceof Chicken && RealmFileManager.isDisabledMob(s, "chicken"))
					le.remove();
				else if(le instanceof Cow && RealmFileManager.isDisabledMob(s, "cow"))
					le.remove();
				else if(le instanceof Ocelot && RealmFileManager.isDisabledMob(s, "ocelot"))
					le.remove();
				else if(le instanceof Pig && RealmFileManager.isDisabledMob(s, "pig"))
					le.remove();
				else if(le instanceof Sheep && RealmFileManager.isDisabledMob(s, "sheep"))
					le.remove();
				else if(le instanceof Wolf && RealmFileManager.isDisabledMob(s, "wolf"))
					le.remove();
				else if(le instanceof Villager && RealmFileManager.isDisabledMob(s, "villager"))
					le.remove();
				else if(le instanceof IronGolem && RealmFileManager.isDisabledMob(s, "irongolem"))
					le.remove();
				else if(le instanceof Snowman && RealmFileManager.isDisabledMob(s, "snowman"))
					le.remove();
				else if(le instanceof Blaze && RealmFileManager.isDisabledMob(s, "blaze"))
					le.remove();
				else if(le instanceof Creeper && RealmFileManager.isDisabledMob(s, "creeper"))
					le.remove();
				else if(le instanceof Giant && RealmFileManager.isDisabledMob(s, "giant"))
					le.remove();
				else if(le instanceof Silverfish && RealmFileManager.isDisabledMob(s, "silverfish"))
					le.remove();
				else if(le instanceof Skeleton && RealmFileManager.isDisabledMob(s, "skeleton"))
					le.remove();
				else if(le instanceof Spider && RealmFileManager.isDisabledMob(s, "spider"))
					le.remove();
				else if(le instanceof Zombie && RealmFileManager.isDisabledMob(s, "zombie"))
					le.remove();
				else if(le instanceof Squid && RealmFileManager.isDisabledMob(s, "squid"))
					le.remove();
				else if(le instanceof Ghast && RealmFileManager.isDisabledMob(s, "ghast"))
					le.remove();
				else if(le instanceof Enderman && RealmFileManager.isDisabledMob(s, "enderman"))
					le.remove();
				else if(le instanceof Slime && RealmFileManager.isDisabledMob(s, "slime"))
					le.remove();
				else if(le instanceof MagmaCube && RealmFileManager.isDisabledMob(s, "magmacube"))
					le.remove();
			}
		}
	}
	
	public static void removeEntities(World w)
	{
		String s = w.getName();
			
		for(LivingEntity le : w.getLivingEntities())
		{	
			if(le instanceof Chicken && RealmFileManager.isDisabledMob(s, "chicken"))
				le.remove();
			else if(le instanceof Cow && RealmFileManager.isDisabledMob(s, "cow"))
				le.remove();
			else if(le instanceof Ocelot && RealmFileManager.isDisabledMob(s, "ocelot"))
				le.remove();
			else if(le instanceof Pig && RealmFileManager.isDisabledMob(s, "pig"))
				le.remove();
			else if(le instanceof Sheep && RealmFileManager.isDisabledMob(s, "sheep"))
				le.remove();
			else if(le instanceof Wolf && RealmFileManager.isDisabledMob(s, "wolf"))
				le.remove();
			else if(le instanceof Villager && RealmFileManager.isDisabledMob(s, "villager"))
				le.remove();
			else if(le instanceof IronGolem && RealmFileManager.isDisabledMob(s, "irongolem"))
				le.remove();
			else if(le instanceof Snowman && RealmFileManager.isDisabledMob(s, "snowman"))
				le.remove();
			else if(le instanceof Blaze && RealmFileManager.isDisabledMob(s, "blaze"))
				le.remove();
			else if(le instanceof Creeper && RealmFileManager.isDisabledMob(s, "creeper"))
				le.remove();
			else if(le instanceof Giant && RealmFileManager.isDisabledMob(s, "giant"))
				le.remove();
			else if(le instanceof Silverfish && RealmFileManager.isDisabledMob(s, "silverfish"))
				le.remove();
			else if(le instanceof Skeleton && RealmFileManager.isDisabledMob(s, "skeleton"))
				le.remove();
			else if(le instanceof Spider && RealmFileManager.isDisabledMob(s, "spider"))
				le.remove();
			else if(le instanceof Zombie && RealmFileManager.isDisabledMob(s, "zombie"))
				le.remove();
			else if(le instanceof Squid && RealmFileManager.isDisabledMob(s, "squid"))
				le.remove();
			else if(le instanceof Ghast && RealmFileManager.isDisabledMob(s, "ghast"))
				le.remove();
			else if(le instanceof Enderman && RealmFileManager.isDisabledMob(s, "enderman"))
				le.remove();
			else if(le instanceof Slime && RealmFileManager.isDisabledMob(s, "slime"))
				le.remove();
			else if(le instanceof MagmaCube && RealmFileManager.isDisabledMob(s, "magmacube"))
				le.remove();
		}
	}
}
