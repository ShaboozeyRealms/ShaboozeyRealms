package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.util.Messaging;

public class WhoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[13])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, Constants.errorMessages[31]);
			return true;
		}
		
		if(!ShaboozeyRealms.getPlugin().getServer().getOfflinePlayer(args[0]).isOnline())
		{
			Messaging.error(sender, String.format("Player %s is not online!", args[0]));
			return true;
		}
		
		Player player = ShaboozeyRealms.getPlugin().getServer().getPlayer(args[0]);
		Messaging.message(sender, String.format("Player '%s' is in world '%s'", player.getName(), player.getWorld().getName()));
		return true;
	}
}
