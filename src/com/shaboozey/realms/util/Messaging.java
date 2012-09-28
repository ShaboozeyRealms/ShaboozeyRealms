package com.shaboozey.realms.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messaging {

	private static String prefix = ChatColor.BLUE + "[SRealms] ";

	public static void message(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + ChatColor.WHITE + message);
	}
	
	public static void error(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + ChatColor.RED + "Error! " + message);
	}
}
