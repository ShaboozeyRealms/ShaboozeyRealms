package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;
import com.shaboozey.realms.util.Constants;

public class LoadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission(Constants.permissions[6])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[14]);
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[15]);
			return true;
		}

		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[16]);
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[17]);
			return true;
		}

		if(RealmManager.isLoaded(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[18]);
			return true;
		}
		
		
		Messaging.message(sender, "Loading world...");
		Messaging.log(String.format("Load command on world '%s' run by '%s'", args[0], sender.getName()));
		
		Realm realm = RealmFileManager.getRealmConfig(args[0]);
		RealmManager.loadRealm(realm, sender);
		return true;
		
	}
}