package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class LoadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission("srealms.load")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, "Invalid arguments: /srload <worldname>");
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, "Cannot load this world as it does not exist!");
			return true;
		}

		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, "No config exists for this world... try /srcreate <name> <environment>");
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, "Cannot load this world as it the default world!");
			return true;
		}

		if(RealmManager.isLoaded(args[0]))
		{
			Messaging.error(sender, "This world is already loaded!");
			return true;
		}
		
		Realm realm = RealmFileManager.getRealmConfig(args[0]);
		RealmManager.loadRealm(realm, sender);
		return true;
		
	}
}