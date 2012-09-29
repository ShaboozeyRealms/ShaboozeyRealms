package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;
import com.shaboozey.realms.util.Constants;

public class ImportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[3])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 2))
		{
			Messaging.error(sender, Constants.errorMessages[8]);
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[9]);
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[10]);
			return true;
		}
		
		
		RealmManager.importRealm(new Realm(args[0], Util.getEnvironment(args[1])), sender);
		return true;
	}
}
