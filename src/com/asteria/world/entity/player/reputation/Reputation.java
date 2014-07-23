package com.asteria.world.entity.player.reputation;

import java.util.HashMap;

public class Reputation {
	/**
	 * 
	 * @author h0lybyte
	 * 
	 * Removed until Ruther notice. 
	 * Such Dead Code.
	 * Very Not wow?. 
	 * 
	 * TODO: Re-organize the reputation object. 
	 * 
	 * 
	 */
	

	
	public String ReputationCity;
	public int ReputationAmount;
	
	public Reputation(String City, int Amount)
	{
		this.ReputationCity = City;
		this.ReputationAmount = Amount;
	}
	public int GetCityReputation()
	{
		return this.ReputationAmount;
	}
	public String GetCityName()
	{
		return this.ReputationCity;
	}
	

}
