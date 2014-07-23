package com.asteria.world.entity.player.content;

import java.util.HashMap;

import com.asteria.world.entity.Animation;
import com.asteria.world.entity.Graphic;
import com.asteria.world.entity.player.Player;

/**
 * 
 * @author Vili
 *
 */

public class EmoteAction {
	
	public enum Emote {
		

		YES(168, new Animation(855)),
		NO(169, new Animation(856)),
		THINK(162, new Animation(857)),
		BOW(164, new Animation(858)),
		ANGRY(165, new Animation(864)),
		CRY(161, new Animation(860)),
		LAUGH(170, new Animation(861)),
		CHEER(171, new Animation(862)),
		WAVE(163, new Animation(863)),
		BECKON(167, new Animation(859)),
		CLAP(172, new Animation(865)),
		DANCE(166, new Animation(866)),
		PANIC(52050, new Animation(2110)),
		JIG(52051, new Animation(2106)),
		SPIN(52052, new Animation(2107)),
		HEAD_BANG(52053, new Animation(2108)),
		JOY_JUMP(52054, new Animation(2109)),
		RASP_BERRY(52055, new Animation(2110)),
		YAWN(52056, new Animation(2111)),
		SALUTE(52057, new Animation(2112)),
		SHRUG(52058, new Animation(2113)),
		BLOW_KISS(43092, new Animation(1368), new Graphic(574)),
		GLASS_BOX(2155, new Animation(1368)),
		CLIMB_ROPE(25103, new Animation(1130)),
		LEAN(25106, new Animation(1129)),
		GLASS_WALL(2154, new Animation(1128)),
		GOBLIN_BOW(52071, new Animation(2127)),
		GOBLIN_DANCE(52072, new Animation(2128)),
		SCARED(59062, new Animation(2836)),
		ZOMBIE_WALK(72032, new Animation(3544)),
		ZOMBIE_DANCE(72033,new Animation(3543)),
		RABBIT_HOP(72254, new Animation(6111));
		
		/**
		 *  Constructs the HashMap.
		 */
		public static HashMap <Integer, Emote> emote = new HashMap <Integer, Emote>();
		
		/**
		 *  Grabs the instance of the emote.
		 * @param id
		 * 		The id getting instance.
		 * @return
		 * 		The instance.
		 */
		public static Emote forId(int id) {
			return emote.get(id);
		}
		
		/**
		 *  Populates the HashMap.
		 */
		static {
			for (Emote e : Emote.values()) {
				emote.put(e.getButton(), e);
			}
		}
		
		/** Field Variables **/
		private int buttonIdentifier;
		private Animation animation;
		private Graphic graphic;
		
		/**
		 *  Constructs the enumeration when its using graphics.
		 * @param buttonIdentifier
		 * 		The button being clicked.
		 * @param animation
		 * 		The animation value.
		 * @param 
		 * 		The graphic value.
		 */
		private Emote(final int buttonIdentifier, final Animation animation, final Graphic graphic) {
			this.buttonIdentifier = buttonIdentifier;
			this.animation = animation;
			this.graphic = graphic;
		}
		
		/**
		 *  Constructs the enumeration when graphics are not in use.
		 * @param buttonIdentifier
		 * 		The button being clicked.
		 * @param animation
		 * 		The animation value.
		 */
		private Emote(final int buttonIdentifier, final Animation animation) {
			this(buttonIdentifier, animation, null);
		}
		
		/**
		 *  Publicly encapsulates the button.
		 * @return
		 * 		The button integer.
		 */
		public int getButton() { 
			return buttonIdentifier;
		}
		
		/**
		 *  Publicly encapsulates the animation.
		 * @return
		 * 		The animation value.
		 */
		public Animation getAnimation() {
			return animation;
		}
		
		/**
		 *  Publicly encapsulates the graphic.
		 * @return
		 * 		The graphic value.
		 */
		public Graphic getGraphic() {
			return graphic;
		}
		
	}
	
	/**
	 *  Handles the player animation and graphics for emotes.
	 * @param player
	 * 		The player starting the animation and invoking the graphic.
	 * @param buttonIdentifier
	 * 		The button being pressed.
	 */
	public static boolean emote(final Player player, final int buttonIdentifier) {
		Emote emote = Emote.forId(buttonIdentifier);

		/** Checks if the emote is valid **/
		if (emote == null)
			return false;

		/** Starts the graphic **/
		if (emote.getGraphic() != null) {
			player.graphic(emote.getGraphic());
		}

		/** Starts the emote **/
		player.animation(emote.getAnimation());
		return true;
	}

}