package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class ImportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission("srealms.import")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 2))
		{
			Messaging.error(sender, "Invalid arguments: /srimport <worldname> <worldtype>");
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, "Cannot import this world as there is no map data.");
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, "Cannot import this world as it the default world!");
			return true;
		}
		
		RealmManager.importRealm(new Realm(args[0], Util.getEnvironment(args[1])), sender);
		return true;
	}
}
