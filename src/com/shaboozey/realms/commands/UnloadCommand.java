package com.shaboozey.realms.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class UnloadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission("srealms.unload")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, "Invalid arguments: /srunload <worldname>");
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, "Cannot unload this world as it doesn't exist");
			return true;
		}

		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, "No config exists for this world... try /srcreate <name> <environment>");
			return true;
		}
		
		if(!(RealmManager.isLoaded(args[0])))
		{
			Messaging.error(sender, "Cannot unload this world as it is already unloaded!");
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, "Cannot unload this world as it the default world!");
			return true;
		}
		
		World w = ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]);
		RealmManager.unloadRealm(new Realm(w.getName(), w.getEnvironment()),
				sender);
		return true;
	}
}