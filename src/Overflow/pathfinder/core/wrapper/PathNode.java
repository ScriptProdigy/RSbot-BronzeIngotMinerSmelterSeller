package Overflow.pathfinder.core.wrapper;

import Overflow.pathfinder.core.util.Structure;

import java.lang.ref.SoftReference;

/**
 * Author: Tom
 * Date: 03/09/13
 * Time: 00:18
 */
public class PathNode implements Comparable<PathNode> {

    private final int hash;
    private final double heuristicCost;

    private SoftReference<String> name;

    private boolean expanded;

    private PathNode parent;
    private boolean object = false;
    private double currentCost;
    private double totalCost;

    public PathNode(final int hash, final double heuristicCost) {
        this.hash = hash;
        this.heuristicCost = heuristicCost;
    }

    public void examineNode(final PathNode pathNode, final boolean object) {
        if (parent == null || currentCost > pathNode.currentCost) {
            this.parent = pathNode;
            this.object = object;
            this.currentCost = parent.currentCost + 1;
            this.totalCost = heuristicCost + currentCost;
        }
    }

    public PathNode getParent() {
        return parent;
    }

    public int getHash() {
        return hash;
    }

    public boolean isObject() {
        return object;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void expand() {
        this.expanded = true;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    @Override
    public int compareTo(PathNode o) {
        return Double.compare(totalCost, o.totalCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        if (hash != pathNode.hash) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public String toString() {
        return name == null ? (name = new SoftReference("PathNode(" + Structure.TILE.getX(hash) + "," + Structure.TILE.getY(hash) + "," + Structure.TILE.getZ(hash) + "," + object+")")).get() : name.get();
    }
}
