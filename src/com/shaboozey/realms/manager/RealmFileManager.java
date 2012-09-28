package com.shaboozey.realms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class RealmFileManager {

	private final static FileConfiguration worlds = new YamlConfiguration();
	private final static String worldsDirectory = ShaboozeyRealms.getPlugin().getDataFolder().getAbsolutePath() + "/worlds.yml";
	
	public static void loadDefaultWorlds() {
		
		try {
			
			Set<String> configWorlds = worlds.getConfigurationSection("worlds").getKeys(false);
			
			for(String world : configWorlds)
			{
				if(Util.isDefaultMap(world))
				{
					RealmManager.registerDefaultWorld(
							new Realm(world, 
									Util.getEnvironment(worlds.getString("worlds." + world + ".environment"))),
							Bukkit.getConsoleSender());
					continue;
				}
				
				if(worlds.getBoolean("worlds." + world + ".autoload") == true)
				{
					RealmManager.loadRealm(
							new Realm(world, 
									Util.getEnvironment(worlds.getString("worlds." + world + ".environment"))),
							Bukkit.getConsoleSender());
					continue;
				}
				else
				{
					RealmManager.registerUnloadedWorld(
							new Realm(world, 
									Util.getEnvironment(worlds.getString("worlds." + world + ".environment"))),
							Bukkit.getConsoleSender());
					continue;
							
				}
			}
		}
		catch (Exception e)
		{
			return;
		}
		
	}
	
	public static void createWorldConfig(Realm realm) {
		worlds.set("worlds." + realm.getName() + ".environment", realm.getEnvironment().toString());
		worlds.set("worlds." + realm.getName() + ".autoload", true);
	}

	public static void removeWorldConfig(Realm realm) {
		worlds.set("worlds." + realm.getName(), null);
	}
	
	public static Realm getRealmConfig(String realmName) {
		
		if(hasConfig(realmName))
		{
			return new Realm(realmName, 
					Util.getEnvironment(worlds.getString("worlds." + realmName + ".environment")));
		}
		
		return null;
		
	}
	
	public static boolean hasConfig(String realmName) {
		return worlds.contains("worlds." + realmName);
	}

	public static void setDisabledMob(String realmName, String mob, boolean allowed)
	{
		List<String> mobs = worlds.getStringList("worlds." + realmName + ".disabled-mobs");
		
		if(allowed)
			mobs.remove(mob);
		else
			if(!isDisabledMob(realmName, mob))
				mobs.add(mob);
		
		worlds.set("worlds." + realmName + ".disabled-mobs", mobs);
	}
	
	public static boolean isDisabledMob(String realmName, String mob)
	{
		return worlds.getStringList("worlds." + realmName + ".disabled-mobs").contains(mob);
	}

	// Save/Load API
	public static void save() {
		try {
			worlds.save(worldsDirectory);
			Messaging.message(Bukkit.getConsoleSender(), "Save successful!");
		} catch (IOException e) {
			Messaging.error(Bukkit.getConsoleSender(), "IOException, ;-; ...");
		}
	}
	
	public static void load() {
		try {
			worlds.load(worldsDirectory);
			Messaging.message(Bukkit.getConsoleSender(), "Load successful!");
		} catch (FileNotFoundException e) {
			try {
				File file = new File(worldsDirectory);
				file.getParentFile().mkdir();
				file.createNewFile();
				Messaging.message(Bukkit.getConsoleSender(), "No file exists, creating new!");
			} catch (IOException e1) {
				Messaging.error(Bukkit.getConsoleSender(), "IOException, oh no...");
			}
		} catch (IOException e) {
			Messaging.error(Bukkit.getConsoleSender(), "IOException, oh god...");
		} catch (InvalidConfigurationException e) {
			Messaging.error(Bukkit.getConsoleSender(), "InvalidConfigurationException, christ on a bike...");
		}
	}
	
}
