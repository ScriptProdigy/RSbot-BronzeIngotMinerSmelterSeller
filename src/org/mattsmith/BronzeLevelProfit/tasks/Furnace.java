package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.methods.MethodContext;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Furnace extends Task {

    public int FURNACE = 45310;

    public Furnace(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        boolean CopperCount = ctx.backpack.select().id(Mine.COPPER_ORE).size() > 0;
        boolean TinCount = ctx.backpack.select().id(Mine.TIN_ORE).size() > 0;

        return CopperCount && TinCount;
    }

    @Override
    public void execute()
    {
        if(!ctx.widgets.get(1371).isValid())
        {
            GameObject furnace = ctx.objects.select().id(FURNACE).nearest().first().poll();
            furnace.interact("Smelt");
            sleep(200, 1200);
        } else {
            Component Smelt = ctx.widgets.get(1371, 5);
            Smelt.click();
            sleep(Random.nextInt(34000, 37000));
        }
    }
}
