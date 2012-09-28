package com.shaboozey.realms.manager;

import java.io.File;
import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.util.LoadState;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class RealmManager {

	private static HashMap<String, LoadState> realms = new HashMap<String, LoadState>();
	
	public static void loadRealm(Realm realm, CommandSender sender) {
		
		if(!RealmFileManager.hasConfig(realm.getName()))
		{
			RealmFileManager.createWorldConfig(realm);
		}

		WorldCreator wc = new WorldCreator(realm.getName());
		wc.environment(realm.getEnvironment());
		World w = wc.createWorld();
		
		realms.remove(realm.getName());
		realms.put(realm.getName(), LoadState.LOADED);
		Util.removeEntities(w);
		
		Messaging.message(sender, String.format("World '%s' loaded successfully.", realm.getName()));
	}
	
	public static void unloadRealm(Realm realm, CommandSender sender) {
		
		if(!RealmFileManager.hasConfig(realm.getName()))
		{
			RealmFileManager.createWorldConfig(realm);
		}
		
		boolean success = true;
		if(Util.isBukkitLoaded(realm.getName()))
		{
			World w = ShaboozeyRealms.getPlugin().getServer().getWorld(realm.getName());
			Util.movePlayersToSpawn(w);
			Util.unloadChunks(w);
			success = ShaboozeyRealms.getPlugin().getServer().unloadWorld(realm.getName(), true);
		}
		
		if(success)
		{
			realms.remove(realm);
			realms.put(realm.getName(), LoadState.UNLOADED);
			
			Messaging.message(sender, String.format("World '%s' unloaded successfully.", realm.getName()));
		}
		else
		{
			Messaging.error(sender, String.format("Could not unload world '%s'... are players still in the world?", realm.getName()));
		}
		
	}

	public static void createRealm(Realm realm, CommandSender sender) {
		
		RealmFileManager.createWorldConfig(realm);

		WorldCreator wc = new WorldCreator(realm.getName());
		wc.environment(realm.getEnvironment());
		wc.createWorld();
		
		realms.remove(realm.getName());
		realms.put(realm.getName(), LoadState.LOADED);
		
		Messaging.message(sender, String.format("World '%s' created successfully.", realm.getName()));
	}

	public static void importRealm(Realm realm, CommandSender sender) {
		
		RealmFileManager.createWorldConfig(realm);

		WorldCreator wc = new WorldCreator(realm.getName());
		wc.environment(realm.getEnvironment());
		wc.createWorld();
		
		realms.remove(realm.getName());
		realms.put(realm.getName(), LoadState.LOADED);
		
		Messaging.message(sender, String.format("World '%s' imported successfully.", realm.getName()));
	}
	
	public static void deleteRealm(Realm realm, CommandSender sender) {
		
		if(RealmFileManager.hasConfig(realm.getName()))
		{
			RealmFileManager.removeWorldConfig(realm);
		}
		
		if(Util.isBukkitLoaded(realm.getName()))
		{
			World w = ShaboozeyRealms.getPlugin().getServer().getWorld(realm.getName());
			
			Util.movePlayersToSpawn(w);
			Util.unloadChunks(w);
			ShaboozeyRealms.getPlugin().getServer().unloadWorld(realm.getName(), true);
		}
		
		realms.remove(realm.getName());
		if(Util.deleteDirectory(new File("." + File.separator + realm.getName())))
		{
			Messaging.message(sender, String.format("World '%s' deleted successfully!", realm.getName()));
		}
		else
		{
			Messaging.message(sender, String.format("World '%s' could not be deleted... are players still in the world?", realm.getName()));
		}

	}
	
	public static void registerDefaultWorld(Realm realm, CommandSender sender)
	{
		realms.put(realm.getName(), LoadState.DEFAULT);
		RealmFileManager.createWorldConfig(realm);
		Messaging.message(sender, String.format("Default world '%s' registered successfully.", realm.getName()));
	}
	
	public static void registerUnloadedWorld(Realm realm, CommandSender sender)
	{
		realms.remove(realm.getName());
		realms.put(realm.getName(), LoadState.UNLOADED);
		Messaging.message(sender, String.format("Unloaded world '%s' registered successfully.", realm.getName()));
		
	}
	
	public static void registerWorld(Realm realm)
	{
		realms.remove(realm.getName());
		realms.put(realm.getName(), LoadState.LOADED);
	}
	
	public static HashMap<String, LoadState> getRealms() {
		return realms;
	}

	public static LoadState getRealmState(String realm) {
		return realms.get(realm);
	}
	
	public static boolean isLoaded(String realm) {
		if(realms.containsKey(realm))
		{
			if(realms.get(realm) == LoadState.LOADED)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public static boolean contains(Realm realm) {
		return realms.containsKey(realm.getName());
	}

	public static boolean contains(String realm) {
		return realms.containsKey(realm);
	}
}
