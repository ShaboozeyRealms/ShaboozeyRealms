package com.shaboozey.realms.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;

public class WorldListener implements Listener {

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e)
	{
		World w = e.getFrom();
		
		if(w.getPlayers().size() == 0)
		{
			if(RealmManager.contains(w.getName()))
			{
				Realm r = RealmFileManager.getRealmConfig(w.getName());
				RealmManager.unloadRealm(
						new Realm(r.getName(), r.getEnvironment(), r.getMobSpawn(), r.getAnimalSpawn()),
						Bukkit.getConsoleSender());
				return;
			}
			
			RealmManager.unloadRealm(
					new Realm(w.getName(), w.getEnvironment(), w.getAllowMonsters(), w.getAllowAnimals()),
					Bukkit.getConsoleSender());
		}
	}
}
