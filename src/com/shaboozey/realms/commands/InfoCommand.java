package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.util.Messaging;

public class InfoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[4])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
	
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[11]);
			return true;
		}

		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[12]);
			return true;
		}
		
		Realm realm = RealmFileManager.getRealmConfig(args[0]);
		
		Messaging.message(sender, "Name: " + realm.getName());
		Messaging.message(sender, "Environment: " + realm.getEnvironment());
		Messaging.message(sender, "Players: " + ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]).getPlayers().size() 
				+ "/" + ShaboozeyRealms.getPlugin().getServer().getMaxPlayers());
		
		return true;
		
	}

}
