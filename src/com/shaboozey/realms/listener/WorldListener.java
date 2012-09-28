package com.shaboozey.realms.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.shaboozey.realms.Realm;
import com.shaboozey.realms.manager.RealmFileManager;
import com.shaboozey.realms.manager.RealmManager;
import com.shaboozey.realms.util.Util;

public class WorldListener implements Listener {

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e)
	{
		World w = e.getFrom();
		
		if(Util.isDefaultMap(w.getName()))
			return;
		
		if(w.getPlayers().size() == 0)
		{
			RealmManager.unloadRealm(
					new Realm(w.getName(), w.getEnvironment()),
					Bukkit.getConsoleSender());
		}
	}

	@EventHandler
	public void onWorldLoad(WorldLoadEvent e)
	{
		Util.removeEntities();
	}
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e)
	{
		LivingEntity le = e.getEntity();
		String s = e.getEntity().getWorld().getName();
		
		if(le instanceof Chicken && RealmFileManager.isDisabledMob(s, "chicken"))
			le.remove();
		else if(le instanceof Cow && RealmFileManager.isDisabledMob(s, "cow"))
			le.remove();
		else if(le instanceof Ocelot && RealmFileManager.isDisabledMob(s, "ocelot"))
			le.remove();
		else if(le instanceof Pig && RealmFileManager.isDisabledMob(s, "pig"))
			le.remove();
		else if(le instanceof Sheep && RealmFileManager.isDisabledMob(s, "sheep"))
			le.remove();
		else if(le instanceof Wolf && RealmFileManager.isDisabledMob(s, "wolf"))
			le.remove();
		else if(le instanceof Villager && RealmFileManager.isDisabledMob(s, "villager"))
			le.remove();
		else if(le instanceof IronGolem && RealmFileManager.isDisabledMob(s, "irongolem"))
			le.remove();
		else if(le instanceof Snowman && RealmFileManager.isDisabledMob(s, "snowman"))
			le.remove();
		else if(le instanceof Blaze && RealmFileManager.isDisabledMob(s, "blaze"))
			le.remove();
		else if(le instanceof Creeper && RealmFileManager.isDisabledMob(s, "creeper"))
			le.remove();
		else if(le instanceof Giant && RealmFileManager.isDisabledMob(s, "giant"))
			le.remove();
		else if(le instanceof Silverfish && RealmFileManager.isDisabledMob(s, "silverfish"))
			le.remove();
		else if(le instanceof Skeleton && RealmFileManager.isDisabledMob(s, "skeleton"))
			le.remove();
		else if(le instanceof Spider && RealmFileManager.isDisabledMob(s, "spider"))
			le.remove();
		else if(le instanceof Zombie && RealmFileManager.isDisabledMob(s, "zombie"))
			le.remove();
		else if(le instanceof Squid && RealmFileManager.isDisabledMob(s, "squid"))
			le.remove();
		else if(le instanceof Ghast && RealmFileManager.isDisabledMob(s, "ghast"))
			le.remove();
		else if(le instanceof Enderman && RealmFileManager.isDisabledMob(s, "enderman"))
			le.remove();
		else if(le instanceof Slime && RealmFileManager.isDisabledMob(s, "slime"))
			le.remove();
		else if(le instanceof MagmaCube && RealmFileManager.isDisabledMob(s, "magmacube"))
			le.remove();
	}
}
