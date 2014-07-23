package com.asteria.world.entity.player.skill.crafting;


import java.util.HashMap;

import com.asteria.util.Misc;
import com.asteria.world.entity.Animation;
import com.asteria.world.entity.player.Player;
import com.asteria.world.entity.player.skill.SkillManager;
import com.asteria.world.entity.player.skill.SkillManager.SkillConstant;
import com.asteria.world.item.Item;
import com.asteria.world.item.ItemDefinition;

/**
 * 
 * @author Vili
 *
 */

public class GemCrafting {
	
	private enum Gem {
		
		OPAL(1625, 1609, 1, 12.0, new Animation(890)), 
		JADE(1627, 1611, 13, 20.0, new Animation(891)), 
		RED_TOPAZ(1629, 1613, 16, 25.0, new Animation(892)), 
		SAPPHIRE(1623, 1607, 20, 50.0, new Animation(888)), 
		EMERALD(1621, 1605, 27, 67.0, new Animation(889)), 
		RUBY(1619, 1603, 34, 85.0, new Animation(887)), 
		DIAMOND(1617, 1601, 43, 107.5, new Animation(886)), 
		DRAGONSTONE(1631, 1615, 55, 137.5, new Animation(885)), 
		ONYX(6571, 6573, 67, 168.0, new Animation(885));
		
		/** Field Variables **/
		private int uncutIdentifier, cutIdentifier, levelRequired;
		private double experienceGained;
		private Animation animationIdentifier;
		
		/**
		 * Constructs the HashMap.
		 */
		public static HashMap <Integer, Gem> gem = new HashMap <Integer, Gem>();
		
		/**
		 *  Creates a instance of the gem.
		 * @param id
		 * 		The id being instanced.
		 * @return
		 * 		The instance value.
		 */
		public static Gem forId(int id) {
			return gem.get(id);
		}
		
		/**
		 *  Populates the HashMap.
		 */
		static {
			for (Gem g : Gem.values()) {
				gem.put(g.getUncut(), g);
			}
		}
		
		/**
		 *  Constructs the enumeration.
		 * @param uncutIdentifier
		 * 		The value for the gem being cut.
		 * @param cutIdentifier
		 * 		The value for the cut gem product.
		 * @param levelRequired
		 * 		The level required to cut the gem.
		 * @param experienceGained
		 * 		The experience gained for cutting the gem.
		 * @param animationIdentifier
		 * 		The animation for played while cutting the gem.
		 */
		private Gem(final int uncutIdentifier, final int cutIdentifier, final int levelRequired, 
				final double experienceGained, final Animation animationIdentifier) {
			this.uncutIdentifier = uncutIdentifier;
			this.cutIdentifier = cutIdentifier;
			this.levelRequired = levelRequired;
			this.experienceGained = experienceGained;
			this.animationIdentifier = animationIdentifier;
		}
		
		/**
		 *  Privately encapsulates the uncutIdentifier.
		 * @return
		 * 		The value for the uncutIdentifier.
		 */
		private int getUncut() {
			return uncutIdentifier;
		}
		
		/**
		 *  Privately encapsulates the cutIdentifier.
		 * @return
		 * 		The value for the cutIdentifier.
		 */
		private int getCut() {
			return cutIdentifier;
		}
		
		/**
		 *  Privately encapsulates the levelRequired.
		 * @return
		 * 		The value for the levelRequired.
		 */
		public int getLevel() {
			return levelRequired;
		}
		
		/**
		 *  Privately encapsulates the experienceGained.
		 * @return
		 * 		The value for the experienceGained.
		 */
		private double getExperience() {
			return experienceGained;
		}
		
		/**
		 *  Privately encapsulates the animationIdentifier.
		 * @return
		 * 		The value for the animationIdentifier.
		 */
		private Animation getAnimation() {
			return animationIdentifier;
		}
		
	}
	
	/**
	 *  Handles the action of cutting a gem.
	 * @param player
	 * 		The player cutting the gem.
	 * @param itemUsed
	 * 		The item being used to the cut gem.
	 * @param itemOn
	 * 		The value for the item being used on.
	 * @return
	 * 		The cutting action.
	 */
	public static boolean cut(final Player player, final int itemUsed, final int itemOn) {
		Gem gem = Gem.forId(itemUsed);
		
		/** Checks if the gem is valid **/
		if (gem == null)
			return false;

		/** Checks if the player has the required items **/
		if (!player.getInventory().getContainer().contains(gem.getUncut()) && 
				!player.getInventory().getContainer().contains(1755))
			return false;

		/** Starts the action of cutting the gem. **/
		if (itemUsed == gem.getUncut() && itemOn == 1755 || itemUsed == 1755
				&& itemOn == gem.getUncut())
			if (player.getSkills()[Misc.CRAFTING].getLevel() >= gem.getLevel()) {
				player.animation(gem.getAnimation());
			player.getInventory().replaceItem(new Item(gem.getUncut()), new Item(gem.getCut()));
			SkillManager.addExperience(player, (int) gem.getExperience(), SkillConstant.getSkill(12));
			player.getPacketBuilder().sendMessage("You manage to cut the " + ItemDefinition.getDefinitions()[gem.getUncut()].getItemName().toLowerCase() + ".");
		}
		return true;

	}

}
