package fr.discowzombie.invcleaner.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class UtilsInv {

	/*
	 * Created by Mathéo | DiscowZombie on 20/08/2017.
	 */

	private static int compteur = 0;
	
	public static void clearInv(Player cible){
		compteur = 0;
		for(ItemStack i : cible.getInventory()){
			if((i != null) && (i.getType() != Material.AIR)){
				cible.getInventory().remove(i);
				compteur++;
			}
		}
	}
	
	//check bypass + mat != null
	public static void clearInv(Player cible, String mat) throws Exception {
		compteur = 0;
		if((mat == null) || (mat.equalsIgnoreCase("-i"))){
			//il veut bypass
			clearInv(cible);
		}else{
			int id = Integer.parseInt(mat);
			@SuppressWarnings("deprecation")
			Material mate = Material.getMaterial(id);
			if(mate != null){
				for(ItemStack is : cible.getInventory()){
					if((is != null) && (is.getType() != Material.AIR)){
						if(is.getType().equals(mate)){
							cible.getInventory().remove(is);
							compteur++;
						}
					}
				}
			}else{
				throw new Exception("Impossible de convertir le materiel.");
			}
		}
	}
	
	public static void clearInv(Player cible, String mat, String name) throws Exception{
		compteur = 0;
		if((mat == null) || (mat.equalsIgnoreCase("-i")) || (name == null) || (name.equalsIgnoreCase("-i"))){
			//Il a bypass qqch
			if((mat == null) || (mat.equalsIgnoreCase("-i"))){
				//Il a bypass le material et peut-etre le name
				if((name == null) || (name.equalsIgnoreCase("-i"))){
					clearInv(cible);
				}else{
					for(ItemStack is : cible.getInventory()){
						if((is != null) && (is.getType() != Material.AIR)){
							if((is.getItemMeta().getDisplayName() != null) && (is.getItemMeta().getDisplayName().equalsIgnoreCase(name))){
								cible.getInventory().remove(is);
								compteur++;
							}
						}
					}
				}
			}else{
				//le name est bypass
				if((mat == null) || (mat.equalsIgnoreCase("-i"))){
					clearInv(cible);
				}else{
					//Clear ses items sans nom
					clearInv(cible, mat);
				}
			}
		}else{
			//il a rien bypass
			int id = Integer.parseInt(mat);
			@SuppressWarnings("deprecation")
			Material mate = Material.getMaterial(id);
			if(mate != null){
				for(ItemStack is : cible.getInventory()){
					if((is != null) && (is.getType() != Material.AIR)){
						if(is.getType().equals(mate)){
							if((is.getItemMeta().getDisplayName() != null) && (is.getItemMeta().getDisplayName().equalsIgnoreCase(name))){
								cible.getInventory().remove(is);
								compteur++;
							}
						}
					}
				}
			}else{
				throw new Exception("Impossible de convertir le materiel.");
			}
		}
	}
	
	public static void clearInv(Player cible, String mat, String name, String loreFilter) throws Exception{
		compteur = 0;
		
		if((mat == null) || (mat.equalsIgnoreCase("-i")) || (name == null) || (name.equalsIgnoreCase("-i")) || (loreFilter == null) || (loreFilter.equalsIgnoreCase("-i"))){
			//il a bypass qqch
			if((mat == null) || (mat.equalsIgnoreCase("-i"))){
				//il a bypass le material et peut-etre autre chose
				if(((name == null) || (name.equalsIgnoreCase("-i"))) && ((loreFilter == null) || (loreFilter.equalsIgnoreCase("-i")))){
					clearInv(cible);
				}else if(((name == null) || (name.equalsIgnoreCase("-i"))) && (loreFilter != null) && (!loreFilter.equalsIgnoreCase("-i"))){
					//il a bypass le name sans le lore
					for(ItemStack is : cible.getInventory()){
						if((is != null) && (is.getType() != Material.AIR)){
							if(is.getItemMeta().getLore() != null && is.getItemMeta().getLore().contains(loreFilter)){
								cible.getInventory().remove(is);
								compteur++;
							}
						}		
					}
				}else{
					//il a bypass le lore sans le nom
					clearInv(cible, mat, name);
				}
			}else if((name == null) || (name.equalsIgnoreCase("-i"))){
				//il a bypass le name MAIS PAS LE MATERIAL
				if((loreFilter == null) || (loreFilter.equalsIgnoreCase("-i"))){
					//il a bypass le lore
					clearInv(cible, mat);
				}else{
					//il a pas bypass le lore ni le mat MAIS JUSTE LE NAME
					for(ItemStack is : cible.getInventory()){
						if((is != null) && (is.getType() != Material.AIR)){
							if(is.getType().equals(mat)){
								if((is.getItemMeta().getLore() != null) && (is.getItemMeta().getLore().contains(loreFilter))){
									cible.getInventory().remove(is);
									compteur++;
								}
							}
						}
					}
				}
			}else{
				//il a juste bypass le lore
				clearInv(cible, mat, name);
			}
		}else{
			//il a rien bypass
			int id = Integer.parseInt(mat);
			@SuppressWarnings("deprecation")
			Material mate = Material.getMaterial(id);
			if(mate != null){
				for(ItemStack is : cible.getInventory()){
					if((is != null) && (is.getType() != Material.AIR)){
						if(is.getType().equals(mate)){
							if(is.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
								if((is.getItemMeta().getLore() != null) && (is.getItemMeta().getLore().contains(loreFilter))){
									cible.getInventory().remove(is);
									compteur++;
								}
							}
						}
					}
				}
			}else{
				throw new Exception("Impossible de convertir le materiel.");
			}
		}
	}
	
	public static int count(){
		return compteur;
	}

}
