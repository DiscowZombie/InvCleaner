package fr.discowzombie.invcleaner.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Utils {

	/*
	 * Created by Mathéo | DiscowZombie on 20/08/2017.
	 */

	public static void sendMessage(CommandSender cs, String message){
		if(cs instanceof Player){
			Player p = (Player)cs;
			p.sendMessage(message);
		}else{
			System.out.println(message);
		}
	}

}
