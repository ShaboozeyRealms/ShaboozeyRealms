package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;
import com.shaboozey.realms.util.Constants;

public class DeleteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[1])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[4]);
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[5]);
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[6]);
			return true;
		}
		
		
		Messaging.message(sender, "Deleting world...");
		
		RealmManager.deleteRealm(new Realm(args[0], null), sender);
		return true;
	}
}