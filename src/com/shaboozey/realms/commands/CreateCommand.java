package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Constants;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class CreateCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[0])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 2))
		{
			Messaging.error(sender,Constants.errorMessages[1]);
			return true;
		}
		
		if(Util.mapExists(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[2]);
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[3]);
			return true;
		}
		
		
		Messaging.message(sender, "Creating world...");
		Messaging.log(String.format("Delete command on world '%s' run by '%s'", args[0], sender.getName()));
		
		RealmManager.createRealm(new Realm(args[0], Util.getEnvironment(args[1])), sender);
		return true;
	}

}
