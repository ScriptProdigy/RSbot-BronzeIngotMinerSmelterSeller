package Overflow.pathfinder.core.wrapper;

import java.util.LinkedList;

/**
 * Author: Tom
 * Date: 03/09/13
 * Time: 00:23
 */
public class TilePath extends LinkedList<PathNode> {

    private final int targetTile;
    private final int timeTook;

    public TilePath(final int targetTile, final int timeTook) {
        this.targetTile = targetTile;
        this.timeTook = timeTook;
    }

    public int getTimeTook() {
        return timeTook;
    }

    public int getTargetTile() {
        return targetTile;
    }

    public int getNextStep(final int currentLocation) {
        return -1;
    }
}
