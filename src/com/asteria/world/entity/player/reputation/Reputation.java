package com.asteria.world.entity.player.reputation;

import java.util.HashMap;

public class Reputation {
	
	public HashMap<String, Integer> ReputationMap;
	public String ReputationUsername; 
	public Reputation(String username)
	{
		this.ReputationUsername = username;
		this.ReputationMap = new HashMap<String, Integer>();
		this.ReputationMap.put("Civil", 100);
	}
	public HashMap<String, Integer> GetReputation()
	{
		return this.ReputationMap;
	}
	

}
