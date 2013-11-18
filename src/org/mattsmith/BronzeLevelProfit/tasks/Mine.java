package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Mine extends Task {
    public static int[] TIN_ORE = {438, 439};
    public static int[] TIN_ROCK = {3245, 3038};
    public static int[] COPPER_ORE = {436, 437};
    public static int[] COPPER_ROCK = {3229, 3027};
    public static int BRONZE_BAR = 2349;

    public Mine(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        ctx.backpack.select();
        return ctx.backpack.size() < 28
            && Walk.MovementArea.MINE_AREA.getArea().contains(ctx.players.local())
            && (!ctx.objects.select().id(COPPER_ROCK).nearest().first().isEmpty()
                || !ctx.objects.select().id(TIN_ROCK).nearest().first().isEmpty());
    }

    private GameObject getRockToMine()
    {
        int Copper_Ore_Count = ctx.backpack.select().id(COPPER_ORE).size();
        int Tin_Ore_Count    = ctx.backpack.select().id(TIN_ORE).size();

        /*System.out.print(Copper_Ore_Count);
        System.out.print(" Copper ore and ");
        System.out.print(Tin_Ore_Count);
        System.out.println(" Tin ore ");*/

        GameObject rock;
        if(Tin_Ore_Count == Copper_Ore_Count)
        {
            int[] array1and2 = new int[COPPER_ROCK.length + TIN_ROCK.length];
            System.arraycopy(COPPER_ROCK, 0, array1and2, 0, COPPER_ROCK.length);
            System.arraycopy(TIN_ROCK, 0, array1and2, COPPER_ROCK.length, TIN_ROCK.length);

            rock = ctx.objects.select().id(array1and2).nearest().poll();

        } else {
            rock = ctx.objects.select().id(COPPER_ROCK).nearest().poll();

            if(Copper_Ore_Count > Tin_Ore_Count) {
                rock = ctx.objects.select().id(TIN_ROCK).nearest().poll();
            }
        }

        if(rock.isValid()) {
            return rock;
        } else {
            return getRockToMine();
        }
    }

    @Override
    public void execute()
    {
        GameObject rock = getRockToMine();

        if (rock.isOnScreen())
        {
            if (rock.interact("Mine"))
            {
                System.out.println("Mining...");
                Timer t = new Timer(7000);
                while (t.isRunning() && rock.isValid()) {
                    sleep(300, 490);
                }
            }
        } else {
            ctx.camera.turnTo(rock);
            System.out.println("Looking for rock...");
            if (!rock.isOnScreen() && rock.getLocation().distanceTo(ctx.players.local()) > 5) {
                ctx.movement.stepTowards(rock);
                sleep(1500, 2100);
            }
        }
    }
}
