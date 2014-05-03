package server.world.entity.player.content;

import server.util.Misc;
import server.world.entity.combat.CombatType;
import server.world.entity.player.Player;
import server.world.item.Item;
import server.world.item.ItemDefinition;

/**
 * Changes the interface in the first sidebar whenever a new weapon is equipped
 * or an existing item is unequipped.
 * 
 * @author lare96
 */
public class AssignWeaponInterface {

    // XXX: BARROWS, MAULS

    /**
     * A map of every weapon in the game and the corresponding data for the
     * interface that will be displayed when the weapon is equipped.
     */
    private static WeaponInterface[] weaponInterface = new WeaponInterface[7956];

    /**
     * Whenever the singleton instance is created, every weapon in the game and
     * the corresponding data for the interface will be loaded into a map.
     */
    static {

        /** Loop through all of the item definitions. */
        for (ItemDefinition def : ItemDefinition.getDefinitions()) {

            /** Filter out the weapons from non-weapons. */
            if (def == null || def.isNoted() || def.getEquipmentSlot() != Misc.EQUIPMENT_SLOT_WEAPON) {
                continue;
            }

            /** Add the weapons and the appropriate interfaces into the map. */
            if (def.getItemName().startsWith("Staff") || def.getItemName().endsWith("staff")) {
                weaponInterface[def.getItemId()] = WeaponInterface.STAFF;
            } else if (def.getItemName().startsWith("Scythe")) {
                weaponInterface[def.getItemId()] = WeaponInterface.SCYTHE;
            } else if (def.getItemName().endsWith("warhammer") || def.getItemName().endsWith("maul")) {
                weaponInterface[def.getItemId()] = WeaponInterface.WARHAMMER;
            } else if (def.getItemName().endsWith("battleaxe")) {
                weaponInterface[def.getItemId()] = WeaponInterface.BATTLEAXE;
            } else if (def.getItemName().equals("Crossbow") || def.getItemName().endsWith("crossbow")) {
                weaponInterface[def.getItemId()] = WeaponInterface.CROSSBOW;
            } else if (def.getItemName().endsWith("shortbow")) {
                weaponInterface[def.getItemId()] = WeaponInterface.SHORTBOW;
            } else if (def.getItemName().endsWith("longbow")) {
                weaponInterface[def.getItemId()] = WeaponInterface.LONGBOW;
            } else if (def.getItemName().endsWith("dagger")) {
                weaponInterface[def.getItemId()] = WeaponInterface.DAGGER;
            } else if (def.getItemName().endsWith("longsword")) {
                weaponInterface[def.getItemId()] = WeaponInterface.LONGSWORD;
            } else if (def.getItemName().endsWith(" sword") && !def.getItemName().endsWith("2h sword")) {
                weaponInterface[def.getItemId()] = WeaponInterface.SWORD;
            } else if (def.getItemName().endsWith("scimitar")) {
                weaponInterface[def.getItemId()] = WeaponInterface.SCIMITAR;
            } else if (def.getItemName().endsWith("2h sword")) {
                weaponInterface[def.getItemId()] = WeaponInterface.TWO_HANDED_SWORD;
            } else if (def.getItemName().endsWith("mace")) {
                weaponInterface[def.getItemId()] = WeaponInterface.MACE;
            } else if (def.getItemName().endsWith("knife")) {
                weaponInterface[def.getItemId()] = WeaponInterface.KNIFE;
            } else if (def.getItemName().endsWith("spear")) {
                weaponInterface[def.getItemId()] = WeaponInterface.SPEAR;
            } else if (def.getItemName().endsWith("pickaxe")) {
                weaponInterface[def.getItemId()] = WeaponInterface.PICKAXE;
            } else if (def.getItemName().endsWith("claws")) {
                weaponInterface[def.getItemId()] = WeaponInterface.CLAWS;
            } else if (def.getItemName().endsWith("halberd")) {
                weaponInterface[def.getItemId()] = WeaponInterface.HALBERD;
            } else if (def.getItemName().endsWith("whip")) {
                weaponInterface[def.getItemId()] = WeaponInterface.WHIP;
            } else if (def.getItemName().endsWith("thrownaxe")) {
                weaponInterface[def.getItemId()] = WeaponInterface.THROWNAXE;
            } else if (def.getItemName().endsWith("javelin")) {
                weaponInterface[def.getItemId()] = WeaponInterface.JAVELIN;
            } else if (def.getItemName().endsWith("dart")) {
                weaponInterface[def.getItemId()] = WeaponInterface.DART;
            } else if (def.getItemName().endsWith("axe") && !def.getItemName().endsWith("pickaxe")) {
                weaponInterface[def.getItemId()] = WeaponInterface.BATTLEAXE;
            } else {
                weaponInterface[def.getItemId()] = WeaponInterface.UNARMED;
            }
        }
    }

    /**
     * All of the interfaces for weapons and the data needed to display these
     * interfaces properly.
     * 
     * @author lare96
     */
    public enum WeaponInterface {
        STAFF(328, 331, CombatType.MELEE, 6, new FightType[] { FightType.STAFF_BASH, FightType.STAFF_FOCUS, FightType.STAFF_POUND }),
        WARHAMMER(425, 428, CombatType.MELEE, 6, new FightType[] { FightType.WARHAMMER_BLOCK, FightType.WARHAMMER_POUND, FightType.WARHAMMER_PUMMEL }),
        SCYTHE(776, 779, CombatType.MELEE, 6, new FightType[] { FightType.SCYTHE_BLOCK, FightType.SCYTHE_CHOP, FightType.SCYTHE_JAB, FightType.SCYTHE_REAP }),
        BATTLEAXE(1698, 1701, CombatType.MELEE, 6, new FightType[] { FightType.BATTLEAXE_BLOCK, FightType.BATTLEAXE_CHOP, FightType.BATTLEAXE_HACK, FightType.BATTLEAXE_SMASH }),
        CROSSBOW(1749, 1752, CombatType.RANGE, 5, new FightType[] { FightType.CROSSBOW_RAPID, FightType.CROSSBOW_ACCURATE, FightType.CROSSBOW_LONGRANGE }),
        SHORTBOW(1764, 1767, CombatType.RANGE, 5, new FightType[] { FightType.SHORTBOW_RAPID, FightType.SHORTBOW_ACCURATE, FightType.SHORTBOW_LONGRANGE }),
        LONGBOW(1764, 1767, CombatType.RANGE, 6, new FightType[] { FightType.LONGBOW_RAPID, FightType.LONGBOW_ACCURATE, FightType.LONGBOW_LONGRANGE }),
        DAGGER(2276, 2279, CombatType.MELEE, 4, new FightType[] { FightType.DAGGER_BLOCK, FightType.DAGGER_LUNGE, FightType.DAGGER_SLASH, FightType.DAGGER_STAB }),
        SWORD(2276, 2279, CombatType.MELEE, 5, new FightType[] { FightType.SWORD_BLOCK, FightType.SWORD_LUNGE, FightType.SWORD_SLASH, FightType.SWORD_STAB }),
        SCIMITAR(2423, 2426, CombatType.MELEE, 4, new FightType[] { FightType.SCIMITAR_BLOCK, FightType.SCIMITAR_CHOP, FightType.SCIMITAR_LUNGE, FightType.SCIMITAR_SLASH }),
        LONGSWORD(2423, 2426, CombatType.MELEE, 5, new FightType[] { FightType.LONGSWORD_BLOCK, FightType.LONGSWORD_CHOP, FightType.LONGSWORD_LUNGE, FightType.LONGSWORD_SLASH }),
        MACE(3796, 3799, CombatType.MELEE, 4, new FightType[] { FightType.MACE_BLOCK, FightType.MACE_POUND, FightType.MACE_PUMMEL, FightType.MACE_SPIKE }),
        KNIFE(4446, 4449, CombatType.RANGE, 4, new FightType[] { FightType.KNIFE_RAPID, FightType.KNIFE_ACCURATE, FightType.KNIFE_LONGRANGE }),
        SPEAR(4679, 4682, CombatType.MELEE, 6, new FightType[] { FightType.SPEAR_BLOCK, FightType.SPEAR_LUNGE, FightType.SPEAR_POUND, FightType.SPEAR_SWIPE }),
        TWO_HANDED_SWORD(4705, 4708, CombatType.MELEE, 6, new FightType[] { FightType.TWOHANDEDSWORD_BLOCK, FightType.TWOHANDEDSWORD_CHOP, FightType.TWOHANDEDSWORD_SLASH, FightType.TWOHANDEDSWORD_SMASH }),
        PICKAXE(5570, 5573, CombatType.MELEE, 6, new FightType[] { FightType.PICKAXE_BLOCK, FightType.PICKAXE_IMPALE, FightType.PICKAXE_SMASH, FightType.PICKAXE_SPIKE }),
        CLAWS(7762, 7765, CombatType.MELEE, 4, new FightType[] { FightType.CLAWS_BLOCK, FightType.CLAWS_CHOP, FightType.CLAWS_LUNGE, FightType.CLAWS_SLASH }),
        HALBERD(8460, 8463, CombatType.MELEE, 6, new FightType[] { FightType.HALBERD_FEND, FightType.HALBERD_JAB, FightType.HALBERD_SWIPE }),
        UNARMED(5855, 5857, CombatType.MELEE, 6, new FightType[] { FightType.UNARMED_BLOCK, FightType.UNARMED_KICK, FightType.UNARMED_PUNCH }),
        WHIP(12290, 12293, CombatType.MELEE, 4, new FightType[] { FightType.WHIP_FLICK, FightType.WHIP_LASH, FightType.WHIP_DEFLECT }),
        THROWNAXE(4446, 4449, CombatType.RANGE, 6, new FightType[] { FightType.THROWNAXE_RAPID, FightType.THROWNAXE_ACCURATE, FightType.THROWNAXE_LONGRANGE }),
        DART(4446, 4449, CombatType.RANGE, 3, new FightType[] { FightType.DART_RAPID, FightType.DART_ACCURATE, FightType.DART_LONGRANGE }),
        JAVELIN(4446, 4449, CombatType.RANGE, 6, new FightType[] { FightType.JAVELIN_RAPID, FightType.JAVELIN_ACCURATE, FightType.JAVELIN_LONGRANGE });

        /** The interface that will be displayed on the sidebar. */
        private int interfaceId;

        /** The line that the name of the item will be printed to. */
        private int nameLineId;

        /** The combat type of the interface. */
        private CombatType combatType;

        /** The attack speed of weapons using this interface. */
        private int speed;

        /** The fight types that correspond with this interface. */
        private FightType[] fightType;

        /**
         * Creates a new weapon interface.
         * 
         * @param interfaceId
         *        the interface that will be displayed on the sidebar.
         * @param nameLineId
         *        the line that the name of the item will be printed to.
         * @param combatType
         *        the combat type of the interface.
         * @param speed
         *        the attack speed of weapons using this interface.
         * @param fightType
         *        the fight types that correspond with this interface.
         */
        WeaponInterface(int interfaceId, int nameLineId, CombatType combatType, int speed, FightType[] fightType) {
            this.interfaceId = interfaceId;
            this.nameLineId = nameLineId;
            this.combatType = combatType;
            this.speed = speed;
            this.fightType = fightType;
        }

        /**
         * Gets the interface that will be displayed on the sidebar.
         * 
         * @return the interface id.
         */
        public int getInterfaceId() {
            return interfaceId;
        }

        /**
         * Gets the line that the name of the item will be printed to.
         * 
         * @return the name line id.
         */
        public int getNameLineId() {
            return nameLineId;
        }

        /**
         * Gets the combat type of the interface.
         * 
         * @return the combat type.
         */
        public CombatType getCombatType() {
            return combatType;
        }

        /**
         * Gets the attack speed of weapons using this interface.
         * 
         * @return the attack speed of weapons using this interface.
         */
        public int getSpeed() {
            return speed;
        }

        /**
         * Gets the fight types that correspond with this interface.
         * 
         * @return the fight types that correspond with this interface.
         */
        public FightType[] getFightType() {
            return fightType;
        }
    }

    /**
     * The different train types.
     * 
     * @author lare96
     */
    public enum TrainType {
        ATTACK(Misc.ATTACK),
        STRENGTH(Misc.STRENGTH),
        DEFENCE(Misc.DEFENCE),
        RANGED(Misc.RANGED),
        MAGIC(Misc.MAGIC),
        ATTACK_STRENGTH_DEFENCE(Misc.ATTACK, Misc.STRENGTH, Misc.DEFENCE),
        RANGE_DEFENCE(Misc.RANGED, Misc.DEFENCE),
        MAGIC_DEFENCE(Misc.MAGIC, Misc.DEFENCE);

        /** The skills this train type trains. */
        private int[] trainSkills;

        /**
         * Create a new {@link TrainType}.
         * 
         * @param trainSkills
         *        the skills this train type trains.
         */
        private TrainType(int... trainSkills) {
            this.trainSkills = trainSkills;
        }

        /**
         * Gets the skills this train type trains.
         * 
         * @return the train skills.
         */
        public int[] getTrainSkills() {
            return trainSkills;
        }
    }

    /**
     * The different fight types on the weapon interfaces.
     * 
     * @author lare96
     */
    public enum FightType {
        STAFF_BASH(406, TrainType.ATTACK, 43, 0, Misc.ATTACK_CRUSH),
        STAFF_POUND(406, TrainType.STRENGTH, 43, 1, Misc.ATTACK_CRUSH),
        STAFF_FOCUS(406, TrainType.DEFENCE, 43, 2, Misc.ATTACK_CRUSH),
        WARHAMMER_POUND(401, TrainType.ATTACK, 43, 0, Misc.ATTACK_CRUSH),
        WARHAMMER_PUMMEL(401, TrainType.STRENGTH, 43, 1, Misc.ATTACK_CRUSH),
        WARHAMMER_BLOCK(401, TrainType.DEFENCE, 43, 2, Misc.ATTACK_CRUSH),
        SCYTHE_REAP(408, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        SCYTHE_CHOP(451, TrainType.STRENGTH, 43, 1, Misc.ATTACK_STAB),
        SCYTHE_JAB(412, TrainType.STRENGTH, 43, 2, Misc.ATTACK_CRUSH),
        SCYTHE_BLOCK(408, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        BATTLEAXE_CHOP(1833, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        BATTLEAXE_HACK(1833, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        BATTLEAXE_SMASH(401, TrainType.STRENGTH, 43, 2, Misc.ATTACK_CRUSH),
        BATTLEAXE_BLOCK(1833, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        CROSSBOW_ACCURATE(427, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        CROSSBOW_RAPID(427, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        CROSSBOW_LONGRANGE(427, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        SHORTBOW_ACCURATE(426, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        SHORTBOW_RAPID(426, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        SHORTBOW_LONGRANGE(426, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        LONGBOW_ACCURATE(426, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        LONGBOW_RAPID(426, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        LONGBOW_LONGRANGE(426, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        DAGGER_STAB(402, TrainType.ATTACK, 43, 0, Misc.ATTACK_STAB),
        DAGGER_LUNGE(402, TrainType.STRENGTH, 43, 1, Misc.ATTACK_STAB),
        DAGGER_SLASH(451, TrainType.STRENGTH, 43, 2, Misc.ATTACK_STAB),
        DAGGER_BLOCK(402, TrainType.DEFENCE, 43, 3, Misc.ATTACK_STAB),
        SWORD_STAB(412, TrainType.ATTACK, 43, 0, Misc.ATTACK_STAB),
        SWORD_LUNGE(412, TrainType.STRENGTH, 43, 1, Misc.ATTACK_STAB),
        SWORD_SLASH(451, TrainType.STRENGTH, 43, 2, Misc.ATTACK_SLASH),
        SWORD_BLOCK(412, TrainType.DEFENCE, 43, 3, Misc.ATTACK_STAB),
        SCIMITAR_CHOP(451, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        SCIMITAR_SLASH(451, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        SCIMITAR_LUNGE(412, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 2, Misc.ATTACK_STAB),
        SCIMITAR_BLOCK(451, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        LONGSWORD_CHOP(451, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        LONGSWORD_SLASH(451, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        LONGSWORD_LUNGE(412, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 2, Misc.ATTACK_STAB),
        LONGSWORD_BLOCK(451, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        MACE_POUND(1833, TrainType.ATTACK, 43, 0, Misc.ATTACK_CRUSH),
        MACE_PUMMEL(401, TrainType.STRENGTH, 43, 1, Misc.ATTACK_CRUSH),
        MACE_SPIKE(412, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 2, Misc.ATTACK_STAB),
        MACE_BLOCK(401, TrainType.DEFENCE, 43, 3, Misc.ATTACK_CRUSH),
        KNIFE_ACCURATE(806, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        KNIFE_RAPID(806, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        KNIFE_LONGRANGE(806, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        SPEAR_LUNGE(2080, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 0, Misc.ATTACK_STAB),
        SPEAR_SWIPE(2081, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 1, Misc.ATTACK_SLASH),
        SPEAR_POUND(2082, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 2, Misc.ATTACK_CRUSH),
        SPEAR_BLOCK(2080, TrainType.DEFENCE, 43, 3, Misc.ATTACK_STAB),
        TWOHANDEDSWORD_CHOP(407, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        TWOHANDEDSWORD_SLASH(407, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        TWOHANDEDSWORD_SMASH(406, TrainType.STRENGTH, 43, 2, Misc.ATTACK_CRUSH),
        TWOHANDEDSWORD_BLOCK(407, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        PICKAXE_SPIKE(412, TrainType.ATTACK, 43, 0, Misc.ATTACK_STAB),
        PICKAXE_IMPALE(412, TrainType.STRENGTH, 43, 1, Misc.ATTACK_STAB),
        PICKAXE_SMASH(401, TrainType.STRENGTH, 43, 2, Misc.ATTACK_CRUSH),
        PICKAXE_BLOCK(412, TrainType.DEFENCE, 43, 3, Misc.ATTACK_STAB),
        CLAWS_CHOP(451, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        CLAWS_SLASH(451, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        CLAWS_LUNGE(412, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 2, Misc.ATTACK_STAB),
        CLAWS_BLOCK(451, TrainType.DEFENCE, 43, 3, Misc.ATTACK_SLASH),
        HALBERD_JAB(412, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 0, Misc.ATTACK_STAB),
        HALBERD_SWIPE(440, TrainType.STRENGTH, 43, 1, Misc.ATTACK_SLASH),
        HALBERD_FEND(412, TrainType.DEFENCE, 43, 2, Misc.ATTACK_STAB),
        UNARMED_PUNCH(422, TrainType.ATTACK, 43, 0, Misc.ATTACK_CRUSH),
        UNARMED_KICK(423, TrainType.STRENGTH, 43, 1, Misc.ATTACK_CRUSH),
        UNARMED_BLOCK(422, TrainType.DEFENCE, 43, 2, Misc.ATTACK_CRUSH),
        WHIP_FLICK(1658, TrainType.ATTACK, 43, 0, Misc.ATTACK_SLASH),
        WHIP_LASH(1658, TrainType.ATTACK_STRENGTH_DEFENCE, 43, 1, Misc.ATTACK_SLASH),
        WHIP_DEFLECT(1658, TrainType.DEFENCE, 43, 2, Misc.ATTACK_SLASH),
        THROWNAXE_ACCURATE(806, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        THROWNAXE_RAPID(806, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        THROWNAXE_LONGRANGE(806, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        DART_ACCURATE(806, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        DART_RAPID(806, TrainType.RANGED, 43, 1, Misc.ATTACK_RANGE),
        DART_LONGRANGE(806, TrainType.RANGE_DEFENCE, 43, 2, Misc.ATTACK_RANGE),
        JAVELIN_ACCURATE(806, TrainType.RANGED, 43, 0, Misc.ATTACK_RANGE),
        JAVELIN_RAPID(806, TrainType.RANGED, 43, 2, Misc.ATTACK_RANGE),
        JAVELIN_LONGRANGE(806, TrainType.RANGE_DEFENCE, 43, 3, Misc.ATTACK_RANGE);

        /** The animation this fight type holds. */
        private int animation;

        /** The train type this fight type holds. */
        private TrainType trainType;

        /** The parent config id. */
        private int parentId;

        /** The child config id. */
        private int childId;

        /** The bonus type. */
        private int bonusType;

        /**
         * Create a new {@link FightType}.
         * 
         * @param animation
         *        the animation this fight type holds.
         * @param trainType
         *        the train type this fight type holds.
         * @param parentId
         *        the parent config id.
         * @param childId
         *        the child config id.
         * @param bonusType
         *        the bonus type.
         */
        private FightType(int animation, TrainType trainType, int parentId, int childId, int bonusType) {
            this.animation = animation;
            this.trainType = trainType;
            this.parentId = parentId;
            this.childId = childId;
            this.bonusType = bonusType;
        }

        /**
         * Gets the animation this fight type holds.
         * 
         * @return the animation.
         */
        public int getAnimation() {
            return animation;
        }

        /**
         * Gets the train type this fight type holds.
         * 
         * @return the train type.
         */
        public TrainType getTrainType() {
            return trainType;
        }

        /**
         * Gets the parent config id.
         * 
         * @return the parent id.
         */
        public int getParentId() {
            return parentId;
        }

        /**
         * Gets the child config id.
         * 
         * @return the child id.
         */
        public int getChildId() {
            return childId;
        }

        /**
         * Gets the bonus type.
         * 
         * @return the bonus type.
         */
        public int getBonusType() {
            return bonusType;
        }
    }

    /**
     * Assigns the correct interface for the player based on the item.
     * 
     * @param player
     *        the player to assign the interface for.
     * @param item
     *        the item to base the interface on.
     */
    public static void assignInterface(Player player, Item item) {

        /** Block if invalid item. */
        if (item == null) {
            return;
        }

        /** Retrieve the interface for the weapon from the map. */
        WeaponInterface weapon = weaponInterface[item.getId()];

        /** Write the interface to the sidebar. */
        if (weapon == WeaponInterface.UNARMED) {
            player.getPacketBuilder().sendSidebarInterface(0, weapon.getInterfaceId());
            player.getPacketBuilder().sendString("Unarmed", weapon.getNameLineId());
            player.setWeapon(WeaponInterface.UNARMED);
            return;
        } else if (weapon == WeaponInterface.CROSSBOW) {
            player.getPacketBuilder().sendString("Weapon: ", weapon.getNameLineId() - 1);
        } else if (weapon == WeaponInterface.WHIP) {
            player.getPacketBuilder().sendString("Weapon: ", weapon.getNameLineId() - 1);
        }

        player.getPacketBuilder().sendItemOnInterface(weapon.getInterfaceId() + 1, 200, item.getId());
        player.getPacketBuilder().sendSidebarInterface(0, weapon.getInterfaceId());
        player.getPacketBuilder().sendString("" + item.getDefinition().getItemName() + "", weapon.getNameLineId());
        player.setWeapon(weapon);
    }

    /**
     * Resets the sidebar when an item is unequipped from the weapon slot.
     * 
     * @param player
     *        the player to reset the sidebar for.
     */
    public static void reset(Player player) {

        /** Reset the sidebar back to "unarmed". */
        player.getPacketBuilder().sendSidebarInterface(0, WeaponInterface.UNARMED.getInterfaceId());
        player.getPacketBuilder().sendString("Unarmed", WeaponInterface.UNARMED.getNameLineId());
        player.setWeapon(WeaponInterface.UNARMED);
    }

    /**
     * Changes the fight type when a weapon is equipped or unequipped.
     * 
     * @param item
     *        the weapon being equipped.
     * @param player
     *        the player changing their weapon.
     */
    public static void changeFightType(Item item, Player player) {

        /** Set the new fight type based on the current skill being trained. */
        for (FightType fightType : player.getWeapon().getFightType()) {
            if (fightType.getTrainType() == player.getFightType().getTrainType()) {
                player.setFightType(fightType);
                player.getPacketBuilder().sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId());
                return;
            }
        }

        /** Or set the default fight type for that weapon. */
        player.setFightType(player.getWeapon().getFightType()[0]);
        player.getPacketBuilder().sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId());
    }
}
