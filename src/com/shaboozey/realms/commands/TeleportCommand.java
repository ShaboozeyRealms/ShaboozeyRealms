package com.shaboozey.realms.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.LoadState;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Constants;

public class TeleportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(args.length > 2 || args.length < 1)
		{
			Messaging.error(sender, Constants.errorMessages[25]);
			return true;
		}
		
		if(args.length == 2)
		{
			if(!(sender.hasPermission(Constants.permissions[10])))
			{
				Messaging.error(sender, Constants.errorMessages[0]);
				return true;
			}
			
			if(!ShaboozeyRealms.getPlugin().getServer().getOfflinePlayer(args[1]).isOnline())
			{
				Messaging.error(sender, String.format("Player '%s' is not online", args[1]));
				return true;
			}
			
			if(!RealmManager.contains(args[0]))
			{
				Messaging.error(sender, String.format("World '%s' doesn't exist", args[0]));
				return true;
			}
			
			if(RealmManager.getRealmState(args[0]) == LoadState.UNLOADED)
			{
				RealmManager.loadRealm(RealmFileManager.getRealmConfig(args[0]), sender);
			}
			
			World w = ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]);
			Player p = ShaboozeyRealms.getPlugin().getServer().getPlayer(args[1]);
			p.teleport(new Location(
							w, 
							w.getSpawnLocation().getX(), 
							w.getSpawnLocation().getY(),  
							w.getSpawnLocation().getZ()));
			
			Messaging.message(p, String.format("You were teleported to world '%s' by '%s'", args[0], sender.getName()));
			Messaging.message(sender, String.format("You have teleported '%s' to world '%s'", p.getName(), args[0]));
			
			Messaging.log(String.format("Teleport command on player '%s' to world '%s' run by '%s'", args[1], args[0], sender.getName()));
			
		}
		else {

			if(!(sender.hasPermission(Constants.permissions[11])))
			{
				Messaging.error(sender, Constants.errorMessages[0]);
				return true;
			}
			
			if(!(sender instanceof Player))
			{
				Messaging.error(sender, Constants.errorMessages[26]);
				return true;
			}
			
			if(!RealmManager.contains(args[0]))
			{
				Messaging.error(sender, String.format("World '%s' doesn't exist", args[0]));
				return true;
			}
			
			if(RealmManager.getRealmState(args[0]) == LoadState.UNLOADED)
			{
				RealmManager.loadRealm(RealmFileManager.getRealmConfig(args[0]), sender);
			}
			
			World w = ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]);
			Player p = (Player) sender;
			p.teleport(
					new Location(
							w, 
							w.getSpawnLocation().getX(), 
							w.getSpawnLocation().getY(),  
							w.getSpawnLocation().getZ()));
			
			Messaging.message(p, String.format("You have teleported to world '%s'", args[0]));
			
			Messaging.log(String.format("Teleport command on player '%s' to world '%s' run by '%s'", sender.getName(), args[0], sender.getName()));
		}
		
		return true;
	}
}
