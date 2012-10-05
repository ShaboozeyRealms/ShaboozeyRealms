package com.shaboozey.realms.util;

public class Constants {

	public final static String[] commands =
	{
			"/sr",
			"/srcreate <name> <environment>",
			"/srimport <name> <environment>",
			"/srtp <dest> (name)",
			"/srdelete <name>",
			"/shart <name>",
			"/srload <name>",
			"/srmobs <worldname> <mob> <value>", 
			"/srunload <name>",
			"/srsetspawn",
			"/srlist",
			"/srinfo",
			"/srwho <player>",
	};
	
	public final static String[] descriptions =
	{
			"Lists all commands",
			"Creates a new world",
			"Imports a world",
			"Teleports to a world",
			"Deletes a world",
			"Shart a player",
			"Loads a world",
			"Turn mobs on/off in a world",
			"Unloads a world",
			"Sets the world spawn",
			"Lists all the worlds",
			"Provides info about the world",
			"Shows world name of a player"
	};
	
	public final static String[] errorMessages =
	{
			// 0
			"Insufficent permissions!",
			
			// 1
			"Invalid arguments: /srcreate <worldname> <worldtype>",
			// 2
			"Cannot create this world as it already exists.",
			// 3
			"Cannot create this world as it the default world!",
			
			// 4
			"Invalid arguments: /srdelete <worldname>",
			// 5
			"Cannot delete this world as it doesn't exist",
			// 6
			"Cannot delete this world as it the default world!",
			
			// 7
			"Invalid arguments: /sr",
			
			// 8
			"Invalid arguments: /srimport <worldname> <worldtype>",
			// 9
			"Cannot import this world as there is no map data.",
			// 10
			"Cannot import this world as it the default world!",
			
			// 11
			"Invalid arguments: /srinfo <worldname>",
			// 12
			"No world configuration exists!",
			
			// 13
			"Invalid arguments: /srlist <worldname>",
			
			// 14
			"Invalid arguments: /srload <worldname>",
			// 15
			"Cannot load this world as it does not exist!",
			// 16
			"No config exists for this world... try /srcreate <name> <environment>",
			// 17
			"Cannot load this world as it the default world!",
			// 18
			"This world is already loaded!",
			
			// 19
			"Invalid arguments: /srmobs <worldname> <mob> <value>",
			// 20
			"That is not a valid mob type",
			
			// 21
			"Consoles cannot set the spawn for worlds!",
			// 22
			"Invalid arguments: /srsetspawn",
			
			// 23
			"Invalid arguments: /shart <name>... someone needs to shart themselves.",
			
			// 24
			"Invalid arguments: /srtp <dest> (name)",
			// 25
			"The console cannot teleport itself on the server.",
			
			// 26
			"Invalid arguments: /srunload <worldname>",
			//27
			"Cannot unload this world as it doesn't exist",
			// 28
			"Cannot unload this world as it is already unloaded!",
			// 29
			"Cannot unload this world as it the default world!",
			
			// 30
			"Invalid arguments: /srwho <player>",
	};
	
	
	public final static String[] permissions =
	{
			"srealms.create",
			"srealms.delete",
			"srealms.help",
			"srealms.import",
			"srealms.info",
			"srealms.list",
			"srealms.load",
			"srealms.mobs",
			"srealms.setspawn",
			"srealms.shart",
			"srealms.teleport.others",
			"srealms.teleport",
			"srealms.unload",
			"srealms.who",
	};
}
