package com.shaboozey.realms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import com.shaboozey.realms.Realm;
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
	
	public static void setLimits()
	{
		for(String s : RealmManager.getRealms().keySet())
		{
			Realm realm = RealmFileManager.getRealmConfig(s);
			
			if(RealmManager.isLoaded(realm.getName()))
			{
				World w = ShaboozeyRealms.getPlugin().getServer().getWorld(s);
				if(!realm.getAnimalSpawn())
				{
					w.setAnimalSpawnLimit(0);
					w.setWaterAnimalSpawnLimit(0);
				}
	
				if(!realm.getMobSpawn())
				{
					w.setMonsterSpawnLimit(0);
				}
			}
		}
		
		Messaging.message(Bukkit.getConsoleSender(), "World limits set");
	}
	
	public static void setLimits(World w)
	{
		Realm realm = RealmFileManager.getRealmConfig(w.getName());
		
		if(RealmManager.isLoaded(realm.getName()))
		{
			if(!realm.getAnimalSpawn())
			{
				w.setAnimalSpawnLimit(0);
				w.setWaterAnimalSpawnLimit(0);
			}

			if(!realm.getMobSpawn())
			{
				w.setMonsterSpawnLimit(0);
			}
		}
	}
}
