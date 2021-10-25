package game.environment;

import game.environment.collidable.Collidable;
import game.environment.collidable.CollisionInfo;
import game.geometry.Line;
import game.geometry.Point;

import java.util.ArrayList;

import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidable = new ArrayList<>();

    /**
     * adds collidalbe to colliadalbe array list.
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        listOfCollidable.add(c);
    }

    /**
     * gets closest collision for the requested trajectory.
     * @param trajectory trajectory of ball
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (!listOfCollidable.isEmpty()) {
            Point closestIntersection = null;
            int indicator = -1;
            for (int i = 0; i < listOfCollidable.size(); i++) {
                Point p = trajectory.closestIntersectionToStartOfLine(listOfCollidable.get(i).getCollisionRectangle());
                if (closestIntersection == null && p != null) {
                    closestIntersection = p;
                    indicator = i;
                } else if (closestIntersection != null && p != null) {
                     if (closestIntersection.distance(trajectory.start()) > p.distance(trajectory.start())) {
                         closestIntersection = p;
                         indicator = i;
                     }
                }
            }
            if (indicator == -1) {
                return null;
            }
           return new CollisionInfo(closestIntersection, listOfCollidable.get(indicator));
        }
        return null; //the list is empty
   }

    /**
     * This method delete a collidable.
     * @param c the collidable we delete
     */
   public void deleteCollC(Collidable c) {
        listOfCollidable.remove(c);
   }

}
