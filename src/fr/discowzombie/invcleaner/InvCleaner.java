package fr.discowzombie.invcleaner;

import org.bukkit.plugin.java.JavaPlugin;

import fr.discowzombie.invcleaner.commands.InvRemoveCmd;

public class InvCleaner extends JavaPlugin {

	/*
	 * Created by Mathéo | DiscowZombie on 20/08/2017.
	 */

	@Override
	public void onEnable() {
		
		getCommand("invremove").setExecutor(new InvRemoveCmd());
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

}
