package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Iterator;
import java.util.Map.Entry;

import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.LoadState;
import com.shaboozey.realms.util.Messaging;

public class ListCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		if(!(sender.hasPermission(Constants.permissions[5])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 0))
		{
			Messaging.error(sender, Constants.errorMessages[13]);
			return true;
		}
		
		Iterator<Entry<String, LoadState>> realms = RealmManager.getRealms().entrySet().iterator();
		do
		{
			Entry<String, LoadState> realm = realms.next();
			
			Messaging.message(sender, realm.getKey() 
					+ " - " + RealmFileManager.getRealmConfig(realm.getKey()).getEnvironment().toString() 
					+ " - " + realm.getValue().toString());
		}
		while(realms.hasNext());
		return true;
	}
}