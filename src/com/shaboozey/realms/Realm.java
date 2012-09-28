package com.shaboozey.realms;

import org.bukkit.World.Environment;


public class Realm {

	private String realmName;
	private Environment realmEnvironment;
	
	public Realm(String realmName, Environment realmEnvironment)
	{
		this.realmName = realmName;
		this.realmEnvironment = realmEnvironment;
	}
	
	public String getName() {
		return realmName;
	}
	
	public Environment getEnvironment() {
		return realmEnvironment;
	}
}
