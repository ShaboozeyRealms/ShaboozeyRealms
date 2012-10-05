package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Constants;

public class ShartCommand  implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission(Constants.permissions[9])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[23]);
			return true;
		}
		
		ShaboozeyRealms.getPlugin().getServer().broadcastMessage(args[0] + " has just sharted!");
		Messaging.log(String.format("Shart command on world '%s' run by '%s'", args[0], sender.getName()));
		return true;
		
	}
}