package com.asteria.world.entity.player.reputation;

import java.util.HashMap;

import com.asteria.world.entity.player.Player;



public class ReputationHandler {
	
	public static HashMap<String, Reputation> ReputationPlayerMap;

	public static Reputation GetReputationMap(Player player)
	{
		return ReputationPlayerMap.get(player.getUsername());
	}
	
	public static void InitReputation(Player player, String City, Integer Amount)
	{
	    GetReputationMap(player).GetReputation().replace(City, Amount);
	}	

	public static void OnLoad(Player player)
	{
		int amount = 100;
		InitReputation(player,"Daynor",amount);
		InitReputation(player,"Varrock", amount);
	}
	
}
