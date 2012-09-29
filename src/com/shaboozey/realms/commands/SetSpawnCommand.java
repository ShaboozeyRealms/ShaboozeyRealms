package com.shaboozey.realms.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shaboozey.realms.util.Messaging;

public class SetSpawnCommand  implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[8])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(sender instanceof Player))
		{
			Messaging.error(sender, Constants.errorMessages[22]);
			return true;
		}
		
		if(!(args.length == 0))
		{
			Messaging.error(sender, Constants.errorMessages[23]);
			return true;
		}
		
		Player player = (Player) sender;
		World world = player.getWorld();
		
		world.setSpawnLocation(
				player.getLocation().getBlockX(), 
				player.getLocation().getBlockY(), 
				player.getLocation().getBlockZ());
		
		Messaging.message(player, String.format("Spawn has been set for world '%s'", world.getName()));
		
		return true;
	}

}
