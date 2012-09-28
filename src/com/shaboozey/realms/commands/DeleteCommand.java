package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;

public class DeleteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission("srealms.delete")))
		{
			Messaging.error(sender, "Insufficent permissions!");
			return true;
		}
		
		if(!(args.length == 1))
		{
			Messaging.error(sender, "Invalid arguments: /srdelete <worldname>");
			return true;
		}
		
		if(!(Util.mapExists(args[0])))
		{
			Messaging.error(sender, "Cannot delete this world as it doesn't exist");
			return true;
		}
		
		if(Util.isDefaultMap(args[0]))
		{
			Messaging.error(sender, "Cannot delete this world as it the default world!");
			return true;
		}
		
		RealmManager.deleteRealm(new Realm(args[0], null), sender);
		return true;
	}
}