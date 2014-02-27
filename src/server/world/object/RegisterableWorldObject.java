package server.world.object;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import server.core.Rs2Engine;
import server.world.RegisterableContainer;
import server.world.entity.player.Player;

/**
 * Manages every single {@link WorldObject} registered to the
 * <code>objectSet</code> database.
 * 
 * @author lare96
 */
public class RegisterableWorldObject implements RegisterableContainer<WorldObject> {

    /**
     * A {@link HashSet} to keep track of all of the {@link WorldObject}s in
     * the game.
     */
    private static Set<WorldObject> objectSet = new HashSet<WorldObject>();

    /**
     * Removes any {@link WorldObject}s for a {@link Player} that aren't on the
     * same height level as they are.
     * 
     * @param player
     *        the player to remove the objects for.
     */
    public void removeOnHeight(Player player) {
        for (final WorldObject object : objectSet) {
            if (player.getPosition().getZ() != object.getPosition().getZ()) {
                player.getPacketBuilder().removeObject(object);
            }
        }
    }

    @Override
    public void register(WorldObject registerable) {

        /**
         * Check if an object is already on this position and if so it removes
         * the object from the database before spawning the new one over it.
         */
        for (Iterator<WorldObject> iter = objectSet.iterator(); iter.hasNext();) {
            WorldObject object = iter.next();

            if (object.getPosition().equals(registerable.getPosition())) {
                iter.remove();
            }
        }

        /** Register object for future players. */
        objectSet.add(registerable);

        /** Add object for existing players in the region. */
        for (Player player : Rs2Engine.getWorld().getPlayers()) {
            if (player == null) {
                continue;
            }

            if (player.getPosition().withinDistance(registerable.getPosition(), 60)) {
                player.getPacketBuilder().sendObject(registerable);
            }
        }
    }

    @Override
    public void unregister(WorldObject registerable) {

        /** Can't remove an object that isn't there. */
        if (!objectSet.contains(registerable)) {
            return;
        }

        /** Unregister object for future players. */
        for (Iterator<WorldObject> iter = objectSet.iterator(); iter.hasNext();) {
            WorldObject object = iter.next();

            if (object.equals(registerable)) {
                iter.remove();
            }
        }

        /** Remove object for all existing players. */
        for (Player player : Rs2Engine.getWorld().getPlayers()) {
            if (player == null) {
                continue;
            }

            player.getPacketBuilder().removeObject(registerable);
        }
    }

    @Override
    public void loadNewRegion(Player player) {

        /** Update existing objects for player in region. */
        for (WorldObject object : objectSet) {
            if (object == null) {
                continue;
            }

            if (object.getPosition().withinDistance(player.getPosition(), 60)) {
                player.getPacketBuilder().sendObject(object);
            }
        }
    }

    /**
     * Gets the set of {@link WorldObject}s.
     * 
     * @return the set of objects.
     */
    public Set<WorldObject> getObjects() {
        return objectSet;
    }
}
