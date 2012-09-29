package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.util.Constants;
import com.shaboozey.realms.util.Messaging;

public class HelpCommand  implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
	
		if(!(sender.hasPermission(Constants.permissions[2])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 0))
		{
			Messaging.error(sender, Constants.errorMessages[7]);
			return true;
		}

		for(int i = 0; i < Constants.commands.length; i++)
		{
			Messaging.message(sender, "> " + Constants.commands[i]);
			Messaging.message(sender, "Usage: " + Constants.descriptions[i]);
		}
		
		return true;
		
	}
	
}
