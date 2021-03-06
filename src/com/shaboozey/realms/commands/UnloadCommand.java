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
import com.shaboozey.realms.util.Constants;

public class UnloadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission(Constants.permissions[12])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[26]);
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[27]);
			return true;
		}

		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[16]);
			return true;
		}
		
		if(!(RealmManager.isLoaded(args[0])))
		{
			Messaging.error(sender, Constants.errorMessages[28]);
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[29]);
			return true;
		}
		
		
		Messaging.message(sender, "Unloading world...");
		
		World w = ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]);
		RealmManager.unloadRealm(new Realm(w.getName(), w.getEnvironment()),
				sender);
		
		Messaging.log(String.format("Unload command on world '%s' run by '%s'", args[0], sender.getName()));
		
		return true;
	}
}