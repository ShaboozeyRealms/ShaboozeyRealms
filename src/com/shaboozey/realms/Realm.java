package com.shaboozey.realms;

import org.bukkit.World.Environment;


public class Realm {

	private String realmName;
	private Environment realmEnvironment;
	private boolean mobSpawning;
	private boolean animalSpawning;
	
	public Realm(String realmName, Environment realmEnvironment, boolean mobSpawning, boolean animalSpawning)
	{
		this.realmName = realmName;
		this.realmEnvironment = realmEnvironment;
		this.mobSpawning = mobSpawning;
		this.animalSpawning = animalSpawning;
	}
	
	public String getName() {
		return realmName;
	}
	
	public Environment getEnvironment() {
		return realmEnvironment;
	}
	
	public boolean getMobSpawn() {
		return mobSpawning;
	}

	public boolean getAnimalSpawn() {
		return animalSpawning;
	}
		
}
