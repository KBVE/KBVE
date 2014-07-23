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
		
	/**
	 * NewReputation
	 * @author h0ly
	 * 
	 * We are working
	 * 
	 * @param player
	 */

	
	
	public static void NewReputation(Player player)
	{
		player.getReputation().add(new Reputation("Civil", 100));
		
		
	}
	
	/**
	 * UpdateReputation
	 * @author h0ly
	 * 
	 * We are working
	 * 
	 * @param player
	 * @param city
	 * @param amount
	 */

	/**
	
	public static void UpdateReputation(Player player, String city, Integer amount)
	{
			if(player.getReputation().containsKey(city))
				{
				player.getReputation().put(city, player.getReputation().get(city)+amount);
				}
				else
				{
				player.getReputation().put(city, amount);
				}	
	
	}
	**/
	
}
