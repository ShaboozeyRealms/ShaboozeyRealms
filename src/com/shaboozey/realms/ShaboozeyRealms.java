package com.shaboozey.realms;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;

import com.shaboozey.realms.commands.CreateCommand;
import com.shaboozey.realms.commands.HelpCommand;
import com.shaboozey.realms.commands.ImportCommand;
import com.shaboozey.realms.commands.InfoCommand;
import com.shaboozey.realms.commands.LoadCommand;
import com.shaboozey.realms.commands.SetCommand;
import com.shaboozey.realms.commands.SetSpawnCommand;
import com.shaboozey.realms.commands.TeleportCommand;
import com.shaboozey.realms.commands.UnloadCommand;
import com.shaboozey.realms.commands.DeleteCommand;
import com.shaboozey.realms.commands.ListCommand;
import com.shaboozey.realms.commands.ShartCommand;
import com.shaboozey.realms.listener.WorldListener;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class ShaboozeyRealms extends JavaPlugin {

	private static ShaboozeyRealms plugin;
	public static String defaultWorld;
	
	@Override
	public void onEnable() {
		
		ShaboozeyRealms.plugin = this;
		ShaboozeyRealms.defaultWorld  = Util.getDefaultMap();
		
		this.getServer().getPluginManager().registerEvents(new WorldListener(), this);
		
		Messaging.message(Bukkit.getConsoleSender(), "Loading worlds.yml...");
		RealmFileManager.load();
		RealmFileManager.loadDefaultWorlds();
		
		if(!(RealmFileManager.hasConfig(defaultWorld)))
		{
			RealmManager.registerDefaultWorld(
					new Realm(defaultWorld, Environment.NORMAL, false, false),
					Bukkit.getConsoleSender());
		}

		Messaging.message(Bukkit.getConsoleSender(), "Setting world limits...");
		Util.setLimits();
		
		registerCommands();
	}
	
	@Override
	public void onDisable() {

		Messaging.message(Bukkit.getConsoleSender(), "Saving worlds.yml...");
		RealmFileManager.save();
	}
	
	private void registerCommands() {

		this.getCommand("sr").setExecutor(new HelpCommand());
		this.getCommand("srcreate").setExecutor(new CreateCommand());
		this.getCommand("srunload").setExecutor(new UnloadCommand());
		this.getCommand("srdelete").setExecutor(new DeleteCommand());
		this.getCommand("srimport").setExecutor(new ImportCommand());
		this.getCommand("srtp").setExecutor(new TeleportCommand());
		this.getCommand("srsetspawn").setExecutor(new SetSpawnCommand());
		this.getCommand("srload").setExecutor(new LoadCommand());
		this.getCommand("srlist").setExecutor(new ListCommand());
		this.getCommand("shart").setExecutor(new ShartCommand());
		this.getCommand("srinfo").setExecutor(new InfoCommand());
		this.getCommand("srset").setExecutor(new SetCommand());
	}

	public static ShaboozeyRealms getPlugin() {
		return plugin;
	}
}
