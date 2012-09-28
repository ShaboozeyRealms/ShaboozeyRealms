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
			"/srunload <name>",
			"/srsetspawn",
			"/srlist",
			"/srinfo"
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
			"Unloads a world",
			"Sets the world spawn",
			"Lists all the worlds",
			"Provides info about the world"
	};
	
	public final static String[] errorMessages =
	{
		"Example error",
		"Second example error"
	};
	
	public final static String[] successMessages = 
	{
		"Example success!",
		"Second example success!"
	};
	
	public final static String[] permissions =
	{
		"permissions.example",
		"permissions.example.two"
	};
}
