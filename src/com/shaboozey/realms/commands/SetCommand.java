package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class SetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission("srealms.set")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 3))
		{
			Messaging.error(sender, "Invalid arguments: /srset <worldname> <node> <value>");
			return true;
		}
		
		if(!(RealmFileManager.hasConfig(args[0])))
		{
			Messaging.error(sender, "No configuration avaliable for this world");
			return true;
		}
		
		if(args[1].equalsIgnoreCase("mobspawn"))
		{
			if(args[2].equalsIgnoreCase("true"))
			{
				RealmFileManager.setMobSpawn(args[0], true);
				Messaging.message(sender, "Monster spawning has been set to enabled");
			}
			else 
			{
				RealmFileManager.setMobSpawn(args[0], false);
				Messaging.message(sender, "Monster spawning has been set to disabled");
			}
		}
		else if(args[1].equalsIgnoreCase("animalspawn"))
		{
			if(args[2].equalsIgnoreCase("true"))
			{
				RealmFileManager.setAnimalSpawn(args[0], true);
				Messaging.message(sender, "Animal spawning has been set to enabled");
			}
			else 
			{
				RealmFileManager.setAnimalSpawn(args[0], false);
				Messaging.message(sender, "Animal spawning has been set to disabled");
			}
		}
		else 
		{
			Messaging.error(sender, String.format("Unrecognized node '%s'", args[1]));
			return true;
		}
		
		Util.setLimits(ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]));
		return true;
	}

}
