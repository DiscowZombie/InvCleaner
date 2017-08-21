package fr.discowzombie.invcleaner.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.discowzombie.invcleaner.utils.Utils.*;
import static fr.discowzombie.invcleaner.utils.UtilsInv.*;

public class InvRemoveCmd implements CommandExecutor {

	/*
	 * Created by Mathéo | DiscowZombie on 20/08/2017.
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		Player p = null;
		
		if(sender instanceof Player){
			p = (Player) sender;
			if(!p.hasPermission("invremove.use")){
				p.sendMessage("§cVous n'avez pas la permission d'éxecuter cette commande.");
				return true;
			}
		}
		
		if((args.length >= 1) && (args.length <= 4)){
			Player cible = Bukkit.getPlayer(args[0]);
			String mat = null, itemName = null, lore = null;
			
			if((cible == null) || (!cible.isOnline())){
				sendMessage(sender, "§c"+cible+" §cest introuvable ou hors ligne !");
				return true;
			}
			
			if(args.length == 1){
				//Vide -> clear full inv
				
				clearInv(cible);
				sendMessage(sender, "§aVous avez supprimer §6"+count()+" item de l'inventaire de §6"+cible+"§a.");
				
			}else if(args.length == 2){
				//Avec id
				mat = args[1].toString();
				
				try{
					clearInv(cible, mat);
				}catch (Exception e){
					sendMessage(sender, e.getMessage());
					return true;
				}
				sendMessage(sender, "§aVous avez supprimer avec succès §6"+count()+" §aitem qui avait comme id '"+mat+"'.");
				
			}else if(args.length == 3){
				//Avec nom
				mat = args[1].toString();
				itemName = args[2].toString();
				
				try{
					clearInv(cible, mat, itemName);
				}catch (Exception e){
					sendMessage(sender, e.getMessage());
					return true;
				}
				sendMessage(sender, "§aVous avez supprimer avec succès §6"+count()+" §aitem dont le nom était '"+itemName+"'");
				
			}else if(args.length == 4){
				//Avec nom + lore
				mat = args[1].toString();
				itemName = args[2].toString();
				lore = args[3].toString();
				
				try{
					clearInv(cible, mat, itemName, lore);
				}catch (Exception e){
					sendMessage(sender, e.getMessage());
					return true;
				}
				sendMessage(sender, "§aVous avez supprimer avec succès §6"+count()+" §aitem dont le nom était '"+itemName+"' et qui comportait '"+lore+"' dans leur description.");
			}
			
		}else{
			sendMessage(sender, "§cUsage: /invremove [joueur] [id] [nom] [lore]");
			sendMessage(sender, "§c(Utiliser '-i' pour ignorer un paramètre.)");
		}
		return true;
	}

}
