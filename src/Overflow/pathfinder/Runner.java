package Overflow.pathfinder;

import Overflow.pathfinder.core.util.Structure;
import Overflow.pathfinder.core.wrapper.TilePath;
import Overflow.pathfinder.impl.Pathfinder;

import java.text.DecimalFormat;

/**
 * Author: Tom
 * Date: 03/09/13
 * Time: 01:37
 */
public class Runner {
    public static void main(final String... args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Pathfinder pathfinder = new Pathfinder();
                int iterations = 100;
                int pathLength = Integer.MAX_VALUE;
                long best = Long.MAX_VALUE;
                long worst = Long.MIN_VALUE;
                long l = 0;
                for (int i = 0; i < iterations; i++) {
                    TilePath path = pathfinder.findPath(Structure.TILE.getHash(3186, 3437, 0), Structure.TILE.getHash(3237, 3160, 0), 500, false);
                    if (path == null) {
                        i--;
                        continue;
                    }
                    pathLength = Math.min(path.size(), pathLength);
                    best = Math.min(path.getTimeTook(), best);
                    worst = Math.max(path.getTimeTook(), worst);
                    l += path.getTimeTook();
                }
                DecimalFormat format = new DecimalFormat("#.##");
                System.out.println("Finding a path of length " + pathLength + "\nIterations: " + iterations + "\n - Best: " + best + " ms\n - Worst: " + worst + " ms\n - Average: " + (format.format(l / iterations) + " ms"));
            }
        }).start();
    }
}
