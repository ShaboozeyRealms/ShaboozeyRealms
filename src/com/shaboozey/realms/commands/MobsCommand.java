package com.shaboozey.realms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.shaboozey.realms.ShaboozeyRealms;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.util.Messaging;
import com.shaboozey.realms.util.Util;
import com.shaboozey.realms.util.Constants;

public class MobsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if(!(sender.hasPermission(Constants.permissions[7])))
		{
			Messaging.error(sender, Constants.errorMessages[0]);
			return true;
		}
		
		if(!(args.length == 3))
		{
			Messaging.error(sender, Constants.errorMessages[19]);
			return true;
		}
		
		if(!RealmFileManager.hasConfig(args[0]))
		{
			Messaging.error(sender, Constants.errorMessages[20]);
			return true;
		}
		
		if(!isValidMob(args[1]))
		{
			Messaging.error(sender, Constants.errorMessages[21]);
			return true;
		}
		
		RealmFileManager.setDisabledMob(args[0], args[1], (args[2].equals("true") ? true : false));
		Util.removeEntities(ShaboozeyRealms.getPlugin().getServer().getWorld(args[0]));
		
		Messaging.message(sender, String.format("%s have been %s on world '%s'", getMobName(args[1]), getAllowed(args[2]), args[0]));
		
		return true;
		
	}

	private String getMobName(String mob) {
		if(mob.equals("creeper"))
			return "Creepers";
		if(mob.equals("cow"))
			return "Cows";
		if(mob.equals("chicken"))
			return "Chickens";
		if(mob.equals("pig"))
			return "Pigs";
		if(mob.equals("sheep"))
			return "Sheep";
		if(mob.equals("wolf"))
			return "Wolves";
		if(mob.equals("villager"))
			return "Villagers";
		if(mob.equals("irongolem"))
			return "Creepers";
		if(mob.equals("snowman"))
			return "Creepers";
		if(mob.equals("blaze"))
			return "Blazes";
		if(mob.equals("giant"))
			return "Giants";
		if(mob.equals("silverfish"))
			return "Silverfish";
		if(mob.equals("skeleton"))
			return "Skeletons";
		if(mob.equals("spider"))
			return "Spiders";
		if(mob.equals("zombie"))
			return "Zombies";
		if(mob.equals("squid"))
			return "Squids";
		if(mob.equals("ghast"))
			return "Ghasts";
		if(mob.equals("enderman"))
			return "Endermen";
		if(mob.equals("slime"))
			return "Slimes";
		if(mob.equals("magmacube"))
			return "Magma Cubes";
		
		return "Missing name?";
			
	}

	private String getAllowed(String string) {
		if(string.equals("true"))
			return "enabled";
		return "disabled";
	}

	private boolean isValidMob(String mob) {
		
		if(mob.equals("creeper")
		|| mob.equals("cow")
		|| mob.equals("chicken")
		|| mob.equals("pig")
		|| mob.equals("sheep")
		|| mob.equals("wolf")
		|| mob.equals("villager")
		|| mob.equals("irongolem")
		|| mob.equals("snowman")
		|| mob.equals("blaze")
		|| mob.equals("giant")
		|| mob.equals("silverfish")
		|| mob.equals("skeleton")
		|| mob.equals("spider")
		|| mob.equals("zombie")
		|| mob.equals("squid")
		|| mob.equals("ghast")
		|| mob.equals("enderman")
		|| mob.equals("slime")
		|| mob.equals("magmacube"))
		{
			return true;
		}
		return false;
	}
}
