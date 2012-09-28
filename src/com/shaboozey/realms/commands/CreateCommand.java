package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class CreateCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission("srealms.create")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 2))
		{
			Messaging.error(sender, "Invalid arguments: /srcreate <worldname> <worldtype>");
			return true;
		}
		
		if(Util.mapExists(args[0]))
		{
			Messaging.error(sender, "Cannot create this world as it already exists.");
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, "Cannot create this world as it the default world!");
			return true;
		}
		
		RealmManager.createRealm(new Realm(args[0], Util.getEnvironment(args[1]), true, true), sender);
		return true;
	}

}
