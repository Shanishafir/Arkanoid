package game.environment.collidable;

/**
 * This interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the object that will be a listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener we remove
     */
    void removeHitListener(HitListener hl);
}
