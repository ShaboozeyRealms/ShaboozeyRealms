package com.shaboozey.realms.util;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shaboozey.realms.ShaboozeyRealms;

public class Messaging {

	private static String prefix = ChatColor.BLUE + "[SRealms] ";

	public static void message(CommandSender sender, String message)
	{
		if(sender instanceof Player)
		{
			sender.sendMessage(prefix + ChatColor.WHITE + message);
		}
		ShaboozeyRealms.getPlugin().getLogger().log(Level.INFO, message + " by " + sender.getName());
		
	}
	
	public static void error(CommandSender sender, String message)
	{
		if(sender instanceof Player)
		{
			sender.sendMessage(prefix + ChatColor.RED + "Error! " + message);
		}
		ShaboozeyRealms.getPlugin().getLogger().log(Level.INFO, message + " by " + sender.getName());
	}
}
