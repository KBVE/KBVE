package com.asteria.world.entity.player.reputation;

import java.util.HashMap;

import com.asteria.world.entity.player.Player;

/**
 * 
 * 
 * @author h0ly
 *
 * ReputationHandler 
 *
 *
 */

public class ReputationHandler {
	
	/**
	public static HashMap<String, Reputation> ReputationPlayerMap = new HashMap<String, Reputation>();

	
	public static HashMap<String, Reputation> GetReputationPlayerMap()
	{
		return ReputationPlayerMap;
	}
	
	public static void InitReputation(Player player, String City, Integer Amount)
	{
//	    GetReputationMap().replace(City, Amount);
	}	
	**/
	
	public static void NewReputation(Player player, String city, Integer amount)
	{
		player.getReputation().put("Draynor", 100);
		
		
	}
	
	public static void UpdateReputation(Player player, String city, Integer amount)
	{
		if(GetReputationPlayerMap().containsKey(player.getUsername()))
				{
			GetReputationPlayerMap().get(player.getUsername()).GetReputation().clear();
			GetReputationPlayerMap().remove(player.getUsername());
				}
		Reputation playerrep = new Reputation(player.getUsername());
		GetReputationPlayerMap().put(player.getUsername(),playerrep);
		int amount = 100;
		//InitReputation(player,"Daynor",amount);
		//InitReputation(player,"Varrock", amount);
	}
	
}
